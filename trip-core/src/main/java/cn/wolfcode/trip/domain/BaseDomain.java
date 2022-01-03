package cn.wolfcode.trip.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
// 配置公共的参数
public class BaseDomain {
    // 指定主键ID 自动递增
    @TableId(value = "id", type = IdType.AUTO)
    protected Long id;
}
