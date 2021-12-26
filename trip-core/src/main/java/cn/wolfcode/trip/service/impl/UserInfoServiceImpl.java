package cn.wolfcode.trip.service.impl;

import cn.wolfcode.trip.domain.UserInfo;
import cn.wolfcode.trip.mapper.UserInfoMapper;
import cn.wolfcode.trip.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {
}
