package cn.wolfcode.trip.service.impl;

import cn.wolfcode.trip.domain.UserInfo;
import cn.wolfcode.trip.mapper.UserInfoMapper;
import cn.wolfcode.trip.service.ISmsService;
import cn.wolfcode.trip.service.IUserInfoService;
import cn.wolfcode.trip.service.redis.impl.RedisServiceImpl;
import cn.wolfcode.trip.vo.UserInfoVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Autowired
    private ISmsService smsService;

    @Autowired
    private StringRedisTemplate redisTemplate;

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
        System.out.println(code);
        // 发送验证码
        smsService.sendMsg(phone,code);
        // 把验证码存入到redis中
        redisTemplate.opsForValue().set("users:code:"+phone,code);
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
        super.save(userInfo);
    }

}