package cn.wolfcode.trip.web.interceptor;

import cn.wolfcode.trip.anno.RequireLogin;
import cn.wolfcode.trip.common.JsonResult;
import cn.wolfcode.trip.domain.UserInfo;
import cn.wolfcode.trip.service.IUserInfoService;
import cn.wolfcode.trip.service.redis.IRedisService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 拦截器
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private IRedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 拦截处理的判断
        // 如果不是HandlerMethod的请求,直接放行所有api接口的方法都是HandlerMethod方法
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        // 进行强制转换 因为在转换之后才可以获取到注解
        HandlerMethod hm = (HandlerMethod) handler;
        // 判断是否有RequireLogin注解, 如果没有则直接放行
        if (!hm.hasMethodAnnotation(RequireLogin.class)) {
            return true;
        }
        // 获取请求头的token参数, 并且判断token是否有对应的用户信息
        // 通过请求头获取handler中的token信息
        String token = request.getHeader("token");
        // 判断token是否为空
        // 如果没有对应的用户信息, 拦截请求, 响应用户没有登录
        if (StringUtils.isNotEmpty(token)) {
            // 通过获取到的token来查询数据库中的用户, 是否有对应的用户信息
            UserInfo userToken = redisService.getUserToken(token);
            // 如果有对应的用户信息则放行
            if (userToken != null) {
                return true;
            }
        }
        // 上述条件都不满足则抛出异常告诉用户需要登录才能访问
        // 相应头信息设置utf-8
        response.setContentType("application/json;charset=utf-8");
        // 通过response对象响应数据
        JsonResult jsonResult = JsonResult.error(401001, "请先登录在继续访问");
        response.getWriter().write(JSON.toJSONString(jsonResult));
        // false不放心, true放行
        return false;
    }
}
