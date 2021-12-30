package cn.wolfcode.trip.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LogicExpression extends RuntimeException {
    private int code; // 错误状态码

    public LogicExpression(int code, String message) {
        // 调用父类的message来返回信息
        super(message);
        this.code = code;
    }
}
