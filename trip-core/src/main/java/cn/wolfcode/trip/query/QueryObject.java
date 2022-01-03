package cn.wolfcode.trip.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class QueryObject {
    private String keyword;             // 关键字
    private Integer currentPage = 1;    // 当前页
    private Integer pageSize = 10;      // 每页条数
}
