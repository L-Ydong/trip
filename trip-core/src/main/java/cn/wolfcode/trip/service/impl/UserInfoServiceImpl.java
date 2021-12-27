package cn.wolfcode.trip.service.impl;

import cn.wolfcode.trip.domain.UserInfo;
import cn.wolfcode.trip.mapper.UserInfoMapper;
import cn.wolfcode.trip.service.IUserInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Override
    public boolean checkPhone(String phone) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", phone);
        // 返回boolean值 如果是true表示已经注册
        return super.getOne(wrapper) != null;
    }

}