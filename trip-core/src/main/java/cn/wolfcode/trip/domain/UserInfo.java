package cn.wolfcode.trip.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("userinfo")
public class UserInfo extends BaseDomain {
    // 定义性别的常量
    public static final int GENDER_SECRET = 0;  //保密
    public static final int GENDER_MALE = 1;    //男
    public static final int GENDER_FEMALE = 2;  //女
    // 定义用户的状态的常量
    public static final int STATE_NORMAL = 0;   //正常
    public static final int STATE_DISABLE = 1;  //禁用

    private String nickname;                    // 昵称
    private String phone;                       // 手机号码
    private String email;                       // 邮箱
    private String password;                    // 密码
    private int gender=0;                       // 性别 [0] 保密 [1] 男 [2] 女
    private int level;                          // 用户等级
    private String city;                        // 所在城市
    private String headImgUrl;                  // 头像地址
    private String info;                        // 个性签名
    private int state=STATE_NORMAL;                          // 用户状态 [0] 正常 [1] 禁用
}
