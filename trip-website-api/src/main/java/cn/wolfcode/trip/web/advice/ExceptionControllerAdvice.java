package cn.wolfcode.trip.web.advice;

import ch.qos.logback.core.LogbackException;
import cn.wolfcode.trip.common.JsonResult;
import cn.wolfcode.trip.common.LogicExpression;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

// 全局异常处理
@ControllerAdvice
public class ExceptionControllerAdvice {

    // 自定义异常处理
    @ExceptionHandler(LogicExpression.class)
    @ResponseBody
    public JsonResult handlerLogicExpression(LogicExpression e){
        // 在控制台打印错误信息
        e.printStackTrace();
        return JsonResult.error(e.getCode(),e.getMessage());
    }

    // 参数校验异常处理
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public JsonResult handlerBindException(BindException e){
        // 把错误信息拼接成字符串
        StringBuilder sb = new StringBuilder();
        // 获取所有的异常信息列表
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        // 遍历获取每一个异常信息然后拼接
        for (ObjectError error : allErrors) {
            // 把每一个错误信息拼接到字符串中
            // 因为返回的是在一个html中的所有在每个错误信息后面添加一个<br>标签可以换行
            sb.append(error.getDefaultMessage()).append("<br>");
        }
        return JsonResult.error(sb.toString());
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonResult handlerException(Exception e){
        // 在控制台打印错误信息
        e.printStackTrace();
        return JsonResult.error("内部错误请联系管理员");
    }
}
