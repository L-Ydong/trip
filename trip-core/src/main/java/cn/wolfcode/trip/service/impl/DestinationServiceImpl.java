package cn.wolfcode.trip.service.impl;

import cn.wolfcode.trip.domain.Destination;
import cn.wolfcode.trip.domain.Region;
import cn.wolfcode.trip.mapper.DestinationMapper;
import cn.wolfcode.trip.query.DestinationQuery;
import cn.wolfcode.trip.service.IDestinationService;
import cn.wolfcode.trip.service.IRegionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class DestinationServiceImpl extends ServiceImpl<DestinationMapper, Destination> implements IDestinationService {

    @Autowired
    private IRegionService regionService;

    @Override
    public Page<Destination> queryForList(DestinationQuery qo) {
        QueryWrapper<Destination> queryWrapper = new QueryWrapper<>();
        // 只要输入关键字就是在全部里面查询, 不在理会parentId
        // 如果不使用else则是查询的时候就是在parentId中的列表查询所含关键字的内容
        // lang3的工具类里面的isNotEmpty判断是否为空
        if (StringUtils.isNotEmpty(qo.getKeyword())) {
            // 模糊查询
            queryWrapper.like("name", qo.getKeyword());
        } else {
            // 如果不为空, 说明根据parentId查
            if (qo.getParentId() != null) {
                // 在判断parentId是否为空如果不为空则按照parentId查询
                queryWrapper.eq("parent_id", qo.getParentId());
            } else {
                // 如果为空则按照null的条件查询 查询出来的就是根目录
                queryWrapper.isNull("parent_id");
            }
        }
        Page<Destination> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        return super.page(page, queryWrapper);
    }

    @Override
    public void updateInfo(Long id, String info) {
        UpdateWrapper<Destination> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",id)
                .set("info",info);
        this.update(updateWrapper);
    }

    @Override
    public List<Destination> queryToasts(Long parentId) {
        List<Destination> toasts = new ArrayList<>();
        // 使用while 循环获取上级目录
        while (parentId!=null){
            // parentId不为空则按照parentId查询目的地
            Destination destination = this.getById(parentId);
            // 查询到的数据添加到列表
            toasts.add(destination);
            // 修改循环条件, 获取当前信息的 parentId 修改下一次循环判断条件
            parentId = destination.getParentId();
        }
        // 倒序List 修改原来的列表
        Collections.reverse(toasts);
        return toasts;
    }

    @Override
    public List<Destination> getDestByRegionId(Long rid) {
        // 1. 查询区域信息
        Region region = regionService.getById(rid);
        // 2. 获取到目的地的ID集合
        if (StringUtils.isNotEmpty(region.getRefIds())) {
            // 切割
            String[] words = StringUtils.split(region.getRefIds(), ", ");
            QueryWrapper<Destination> queryWrapper = new QueryWrapper<>();
            queryWrapper.in("id", Arrays.asList(words));
            return super.list(queryWrapper);
        }
        return null;
    }

    @Override
    public List<Destination> search(Long regionId) {
        List<Destination> dests = new ArrayList<>();
        QueryWrapper<Destination> queryWrapper = new QueryWrapper<>();
        if (regionId==-1) {
            queryWrapper.eq("parent_id",1);
            dests = this.list();
        }else {
        }
        return dests;
    }
}
