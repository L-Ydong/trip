package cn.wolfcode.trip.service.impl;

import cn.wolfcode.trip.domain.StrategyContent;
import cn.wolfcode.trip.mapper.StrategyContentMapper;
import cn.wolfcode.trip.service.IStrategyContentService;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service
@Transactional
public class StrategyContentServiceImpl extends ServiceImpl<StrategyContentMapper, StrategyContent>  implements IStrategyContentService {

}
