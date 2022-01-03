package cn.wolfcode.trip.service.impl;

import cn.wolfcode.trip.domain.Strategy;
import cn.wolfcode.trip.mapper.StrategyMapper;
import cn.wolfcode.trip.query.QueryObject;
import cn.wolfcode.trip.service.IStrategyService;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service
@Transactional
public class StrategyServiceImpl extends ServiceImpl<StrategyMapper, Strategy>  implements IStrategyService {

    @Override
    public Page<Strategy> queryForList(QueryObject qo) {
        Page<Strategy> page = new Page<>(qo.getCurrentPage(),qo.getPageSize());
        return super.page(page);
    }
}
