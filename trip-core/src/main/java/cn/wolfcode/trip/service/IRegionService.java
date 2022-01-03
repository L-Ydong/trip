package cn.wolfcode.trip.service;

import cn.wolfcode.trip.domain.Destination;
import cn.wolfcode.trip.domain.Region;
import cn.wolfcode.trip.query.QueryObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface IRegionService extends IService<Region> {

    /**
     * 分页查询
     * @param qo    分页的条件
     * @return      分页的结果
     */
    Page<Region> queryForList(QueryObject qo);

    /**
     * 根据区域ID设置状态
     * @param id    区域ID
     * @param hot   1: 热门 0: 禁用
     */
    void changeHotValue(Long id, int hot);

    /**
     * 查询热门地区列表
     * @return  返回热门地区列表
     */
    List<Region> hotRegion();
}
