package cn.wolfcode.trip.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.CONSTRUCTOR)    // 作用域在那个位置: 方法上
@Retention(RetentionPolicy.RUNTIME) // 保证注解运行时可见: 运行时有效
public @interface RequireLogin {
}
