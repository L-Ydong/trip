package cn.wolfcode.trip.service.impl;

import cn.wolfcode.trip.domain.Region;
import cn.wolfcode.trip.mapper.RegionMapper;
import cn.wolfcode.trip.query.QueryObject;
import cn.wolfcode.trip.service.IRegionService;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

@Service
@Transactional
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region>  implements IRegionService {

    @Override
    public Page<Region> queryForList(QueryObject qo) {
        Page<Region> page = new Page<>(qo.getCurrentPage(),qo.getPageSize());
        return super.page(page);
    }

    @Override
    public void changeHotValue(Long id, int hot) {
        UpdateWrapper<Region> updateWrapper = new UpdateWrapper<>();
        // 根据ID修改热门状态 1: 热门 0: 禁用
        updateWrapper.eq("id",id)
                .set("ishot",hot);
        super.update(updateWrapper);
    }

    @Override
    public List<Region> hotRegion() {
        QueryWrapper<Region> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ishot",Region.STATE_HOT)
                .orderByAsc("seq");
        return this.list(queryWrapper);
    }
}
