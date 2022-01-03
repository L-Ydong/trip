package cn.wolfcode.trip.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@TableName("strategy")
// 攻略表
public class Strategy extends BaseDomain {
    public static final int ABROAD_NO = 0;     //国内
    public static final int ABROAD_YES = 1;    //国外

    public static final int STATE_NORMAL = 0;   //待发布
    public static final int STATE_PUBLISH = 1;  //发布

    private Long destId;                        // 目的地ID

    private String destName;                    // 目的地名称

    private Long themeId;                       // 主题ID

    private String themeName;                   // 主题名称

    private Long catalogId;                     // 类别ID

    private String catalogName;                 // 类别名称

    private String title;                       // 攻略标题

    private String subTitle;                    // 攻略副标题

    private String summary;                     // 攻略概要

    private String coverUrl;                    // 封面图片的地址

    private Date createTime;                    // 创建时间

    private int isabroad;                       // 是否国外 0: 国内 1: 国外

    private int viewnum;                        // 浏览数

    private int replynum;                       // 回复数

    private int favornum;                       // 收藏数

    private int sharenum;                       // 分享数

    private int thumbsupnum;                    // 点赞数

    @TableField(exist = false)
    private StrategyContent content;            // 攻略内容 维护攻略内容

    private int state = STATE_NORMAL;           // 状态: 0: 待发布 1: 已发布

    public String getStateDisplay() {
        return state == STATE_NORMAL ? "待发布" : "发布";
    }

}
