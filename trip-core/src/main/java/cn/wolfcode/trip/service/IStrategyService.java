package cn.wolfcode.trip.service;

import cn.wolfcode.trip.domain.Strategy;
import cn.wolfcode.trip.query.QueryObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

 
public interface IStrategyService extends IService<Strategy> {

    /**
     * 分页查询
     * @param qo    分页条件
     * @return      分好页的列表
     */
    Page<Strategy> queryForList(QueryObject qo);
}
