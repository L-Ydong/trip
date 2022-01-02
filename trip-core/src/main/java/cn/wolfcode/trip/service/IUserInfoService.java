package cn.wolfcode.trip.service;
import cn.wolfcode.trip.domain.UserInfo;
import cn.wolfcode.trip.vo.LoginUserInfoVo;
import cn.wolfcode.trip.vo.UserInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IUserInfoService extends IService<UserInfo> {
    /**
     *  校验手机号是否以注册 以注册则返回true 未注册返回false
     * @param phone 手机号
     */
    boolean checkPhone(String phone);

    /**
     * 发送验证码
     * @param phone 手机号
     */
    void sendVerifyCode(String phone);

    /**
     * 用户注册
     * @param userInfoVo 注册的用户信息
     */
    void regist(UserInfoVo userInfoVo);

    /**
     *  用户登录
     * @param username  用户名
     * @param password  用户密码
     * @return          如果用户登录成功则返回token还有用户信息, 如果用户登录失败则抛出异常
     */
    LoginUserInfoVo login(String username, String password);
}
