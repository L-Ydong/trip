package cn.wolfcode.trip.service.redis;

import cn.wolfcode.trip.domain.UserInfo;
import com.tencentcloudapi.vod.v20180717.VodErrorCode;

// 操作redis的接口
public interface IRedisService {
    /**
     * 设置对应的验证码
     * @param phone 手机号码
     * @param Code  验证码
     */
    void setUserCode(String phone, String Code);

    /**
     * 获取到对应手机号的验证码
     * @param phone 手机号
     * @return 验证码 如果有返回验证码 , 没有则返回null
     */
    String getUserCode(String phone);

    /**
     * 根据token设置用户信息到redis中
     * @param token    生成唯一的token
     * @param userInfo 用户对象
     */
    void setUserToken(String token, UserInfo userInfo);

    /**
     * 根据用户token获取用户信息
     * @param token
     * @return  如果没有找到则返回null
     */
    UserInfo getUserToken(String token);


}
