package cn.wolfcode.trip.service.impl;

import cn.wolfcode.trip.common.LogicExpression;
import cn.wolfcode.trip.domain.UserInfo;
import cn.wolfcode.trip.mapper.UserInfoMapper;
import cn.wolfcode.trip.service.ISmsService;
import cn.wolfcode.trip.service.IUserInfoService;
import cn.wolfcode.trip.service.redis.IRedisService;
import cn.wolfcode.trip.service.redis.impl.RedisServiceImpl;
import cn.wolfcode.trip.vo.LoginUserInfoVo;
import cn.wolfcode.trip.vo.UserInfoVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Autowired
    private ISmsService smsService;

    @Autowired
    private IRedisService redisService;

    @Override
    public boolean checkPhone(String phone) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", phone);
        // 返回boolean值 如果是true表示已经注册
        return super.getOne(wrapper) != null;
    }

    @Override
    public void sendVerifyCode(String phone) {
        // 生成验证码
        // 使用工具类生成一个由0-9的随机四个字符的字符串
        String code = RandomStringUtils.random(4, "0123456789");
        // 发送验证码
        smsService.sendMsg(phone,code);
        // 把验证码存入到redis中
        // 存储到redis中的key, value, 还有时间, 时间单位
        redisService.setUserCode(phone,code);
    }

    @Override
    public void regist(UserInfoVo userInfoVo) {
        // 创建一个新的对象来接收前台的注册用户的信息
        UserInfo userInfo = new UserInfo();
        // 内置工具类提供了拷贝的操作 第一个参数是拷贝的源, 第二个参数是拷贝的目标
        BeanUtils.copyProperties(userInfoVo,userInfo);
        // 可以给对象设置默认值
        userInfo.setEmail(userInfo.getPhone()+"@wolfcode.cn");
        userInfo.setHeadImgUrl("/images/default.jpg");
        userInfo.setInfo("这个家伙好懒,什么都没有留下");
        userInfo.setCity("这个家伙好像是火星的");
        super.save(userInfo);
    }

    @Override
    public LoginUserInfoVo login(String username, String password) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        // 根据用户名, 密码查询数据库
        queryWrapper.eq("phone",username);
        queryWrapper.eq("password",password);
        UserInfo userInfo = this.getOne(queryWrapper);
        // 判断是否有此用户 然后没有则抛出异常
        if (userInfo == null) {
            throw new LogicExpression(400001,"用户名或者密码错误");
        }
        // 判断当前用户的状态 如果是在禁用状态则抛出异常
        if (UserInfo.STATE_DISABLE==userInfo.getState()) {
            throw new LogicExpression(400001,"此用户被封禁中");
        }
        // 创建token, 保存到redis中
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        redisService.setUserToken(token,userInfo);
        // 把数据封装到vo对象返回
        LoginUserInfoVo loginUserInfoVo = new LoginUserInfoVo();
        loginUserInfoVo.setUser(userInfo);
        loginUserInfoVo.setToken(token);
        return loginUserInfoVo;
    }
}