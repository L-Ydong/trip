package cn.wolfcode.trip.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@TableName("strategy_content")
// 攻略内容表
// 当需要网络传输时就要继承Serializable来序列化
public class StrategyContent implements Serializable {

    // 通过攻略内容ID来关联因为攻略的ID与内容的ID是相对应的
    private Long id;            // 攻略内容ID
    private String content;     // 攻略内容

}
