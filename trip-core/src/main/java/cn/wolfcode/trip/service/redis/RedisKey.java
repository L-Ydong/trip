package cn.wolfcode.trip.service.redis;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.concurrent.TimeUnit;

/**
 * redis中的key的统一配置管理
 * 主要包括key的名字和前缀
 * 过期时间和时间单位
 */
@Setter
@Getter
@NoArgsConstructor
public class RedisKey {
    // 创建验证码的redisKey常量
    public static final RedisKey USER_CODE = new RedisKey("users:code:", 5L, TimeUnit.MINUTES);
    // 创建一个用户登录token的常量
    public static final RedisKey USER_TOKEN = new RedisKey("users:token:", 30L, TimeUnit.MINUTES);
    private String prefix;  // 前缀
    private long timeout;   // 过期时间
    private TimeUnit unit;  // 时间单位

    public RedisKey(String prefix, long timeout, TimeUnit unit) {
        this.prefix = prefix;
        this.timeout = timeout;
        this.unit = unit;
    }

    // 根据传入的手机号获取key
    public String getKey(String phone) {
        return this.prefix + phone;
    }
}
