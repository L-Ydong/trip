package cn.wolfcode.trip.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DestinationQuery extends QueryObject {
    private Long parentId;   // 区域ID
}
