package cn.wolfcode.trip.service;

import cn.wolfcode.trip.domain.Destination;
import cn.wolfcode.trip.domain.Region;
import cn.wolfcode.trip.query.DestinationQuery;
import cn.wolfcode.trip.query.QueryObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface IDestinationService extends IService<Destination> {

    /**
     *  分页查询
     * @param qo    分页条件
     * @return      分页结果
     */
    Page<Destination> queryForList(DestinationQuery qo);

    /**
     *  目的地管理的编辑
     * @param id    编辑的ID
     * @param info  编辑的简介内容
     */
    void updateInfo(Long id, String info);

    /**
     * 目的地管理列表的吐司
     * @param parentId  目的地ID
     * @return          吐司列表
     */
    List<Destination> queryToasts(Long parentId);

    /**
     *  根据区域ID查询目的地的列表
     * @param rid   区域ID
     * @return      返回的关联地区的集合
     */
    List<Destination> getDestByRegionId(Long rid);

    /**
     * 根据ID查询目的地列表
     * @param regionId  区域Id
     * @return          返回目的地列表
     */
    List<Destination> search(Long regionId);

}
