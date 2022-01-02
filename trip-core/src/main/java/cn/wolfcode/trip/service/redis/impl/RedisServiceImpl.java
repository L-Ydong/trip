package cn.wolfcode.trip.service.redis.impl;

import cn.wolfcode.trip.domain.UserInfo;
import cn.wolfcode.trip.service.redis.IRedisService;
import cn.wolfcode.trip.service.redis.RedisKey;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


import static cn.wolfcode.trip.service.redis.RedisKey.USER_CODE;
import static cn.wolfcode.trip.service.redis.RedisKey.USER_TOKEN;

@Service
public class RedisServiceImpl implements IRedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void setUserCode(String phone, String code) {
        String key = USER_CODE.getKey(phone);
        redisTemplate.opsForValue().set(key,code,USER_CODE.getTimeout(),RedisKey.USER_CODE.getUnit());
    }

    @Override
    public String getUserCode(String phone) {
        return redisTemplate.opsForValue().get(USER_CODE.getKey(phone));
    }

    @Override
    public void setUserToken(String token, UserInfo userInfo) {
        // 根据token获取key
        String key = USER_TOKEN.getKey(token);
        // 把userInfo对象通过FastJSON传化为字符串
        String value = JSON.toJSONString(userInfo);
        redisTemplate.opsForValue().set(key,value,USER_TOKEN.getTimeout(),USER_TOKEN.getUnit());
    }

    @Override
    public UserInfo getUserToken(String token) {
        return null;
    }
}
