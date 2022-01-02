package cn.wolfcode.trip.vo;

import cn.wolfcode.trip.domain.UserInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LoginUserInfoVo {
    private String  token;  // 登录生成的token
    private UserInfo user;  // 用户对象
}
