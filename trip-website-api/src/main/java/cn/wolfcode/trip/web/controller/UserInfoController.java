package cn.wolfcode.trip.web.controller;

import cn.wolfcode.trip.common.JsonResult;
import cn.wolfcode.trip.domain.UserInfo;
import cn.wolfcode.trip.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserInfoController {

    @Autowired
    private IUserInfoService userInfoService;

    @GetMapping("get")
    public JsonResult get(Long id){
        UserInfo userInfo = userInfoService.getById(id);
        return JsonResult.success(userInfo);
    }

    @GetMapping("checkPhone")
    public JsonResult checkPhone(String phone){
        // 1. 接受请求参数 2. 完成参数的校验 3. 调用业务方法 4.返回数据给前端
        // 定义正则表达式 来判断手机号码是否合法
        String pattern = "^1\\d{10}$";
        if (phone.matches(pattern)) {
            // 如果 为true则表示手机号码合法
            boolean flag = userInfoService.checkPhone(phone);
            return JsonResult.success(flag);
        }else {
            // 否则手机号码不合法返回异常信息
            return JsonResult.error("请输入正确的手机格式");
        }
    }
}