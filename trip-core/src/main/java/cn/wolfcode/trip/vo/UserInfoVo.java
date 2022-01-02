package cn.wolfcode.trip.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@NoArgsConstructor
public class UserInfoVo {

    @Pattern(regexp = "^1\\d{10}$",message = "手机号格式不合法")
    private String phone;       // 手机号
    @Pattern(regexp = "\\w{4,}",message = "昵称必须要在四个字符以上")
    @NotBlank(message = "昵称不能为空")
    private String nickname;    // 昵称
    @NotBlank(message = "密码不能为空")
    private String password;    // 密码
    @NotBlank(message = "确认密码不能为空")
    private String rpassword;   // 确认密码
    @NotBlank(message = "验证码不能为空")
    private String verifyCode;  // 验证码
}