package cn.wolfcode.trip.web.controller;

import cn.wolfcode.trip.common.JsonResult;
import cn.wolfcode.trip.common.LogicExpression;
import cn.wolfcode.trip.domain.UserInfo;
import cn.wolfcode.trip.service.IUserInfoService;
import cn.wolfcode.trip.service.redis.IRedisService;
import cn.wolfcode.trip.vo.LoginUserInfoVo;
import cn.wolfcode.trip.vo.UserInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserInfoController {

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private IRedisService redisService;

    // 根据ID查询用户信息
    @GetMapping("get")
    public JsonResult get(Long id) {
        UserInfo userInfo = userInfoService.getById(id);
        return JsonResult.success(userInfo);
    }

    // 验证手机号码是否合法
    @GetMapping("checkPhone")
    public JsonResult checkPhone(String phone) {
        // 1. 接受请求参数 2. 完成参数的校验 3. 调用业务方法 4.返回数据给前端
        // 定义正则表达式 来判断手机号码是否合法
        String pattern = "^1\\d{10}$";
        if (phone.matches(pattern)) {
            // 如果 为true则表示手机号码合法
            boolean flag = userInfoService.checkPhone(phone);
            return JsonResult.success(flag);
        } else {
            // 否则手机号码不合法返回异常信息
            return JsonResult.error("请输入正确的手机格式");
        }
    }

    // 发送验证码
    @GetMapping("sendVerifyCode")
    public JsonResult sendVerifyCode(String phone) {
        // 1. 接受请求参数 2. 完成参数的校验 3. 调用业务方法 4. 返回数据给前端
        if (StringUtils.isEmpty(phone)) {
            return JsonResult.error("请先输入手机号, 在获取验证码");
        }
        //  给前端传来的手机号发验证码
        userInfoService.sendVerifyCode(phone);
        // 返回成功的JsonResult
        return JsonResult.success();
    }

    // 完成注册
    @PostMapping("regist")
    public JsonResult regist(@Validated UserInfoVo userInfoVo) {
        // 验证参数的合理性 可以通过框架完成
        // 密码和确认密码必须一致
        if (!StringUtils.equals(userInfoVo.getPassword(), userInfoVo.getRpassword())) {
            throw new LogicExpression(400001,"两次密码必须一致");
        }
        // 验证码是否正确
        // 1. 获取存储在redis中的验证码
        String code = redisService.getUserCode(userInfoVo.getPhone());
        // 2. 比较前端的code还有查询到的code是否一致
        if (!userInfoVo.getVerifyCode().equals(code)) {
            throw new LogicExpression(400001,"验证码错误");
        }
        // 注册用户
        userInfoService.regist(userInfoVo);
        return JsonResult.success();
    }

    // 登录
    @PostMapping("login")
    public JsonResult login(String username, String password){
        // 接受前台传入的参数 然后判断是否有这个用户 用户密码是否正确
        // 存在则把用户的token和信息返回给前台
        LoginUserInfoVo loginUserInfoVo = userInfoService.login(username,password);
        return JsonResult.success(loginUserInfoVo);
    }
}