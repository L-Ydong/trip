package cn.wolfcode.trip.web.controller;

import cn.wolfcode.trip.common.JsonResult;
import cn.wolfcode.trip.domain.Destination;
import cn.wolfcode.trip.domain.Region;
import cn.wolfcode.trip.query.QueryObject;
import cn.wolfcode.trip.service.IDestinationService;
import cn.wolfcode.trip.service.IRegionService;
import cn.wolfcode.trip.service.redis.IRedisService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("region")
public class RegionController {

    @Autowired
    private IRegionService regionService;

    @Autowired
    private IDestinationService destinationService;

    // 目的地列表
    @RequestMapping("list")
    public String list(@ModelAttribute("qo")QueryObject qo, Model model){
        Page<Region> page = regionService.queryForList(qo);
        model.addAttribute("page",page);
        // 共享目的地列表
        List<Destination> dests = destinationService.list();
        model.addAttribute("dests",dests);
        return "region/list";
    }

    // 设置热门 和取消热门
    @GetMapping("changeHotValue")
    @ResponseBody
    public JsonResult changeHotValue(Long id,int hot){
        regionService.changeHotValue(id,hot);
        return JsonResult.success();
    }

    // 新增编辑
    @PostMapping("saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Region region){
        regionService.saveOrUpdate(region);
        return JsonResult.success();
    }

    // 删除
    @RequestMapping("delete")
    @ResponseBody
    public JsonResult delete(Long id){
        regionService.removeById(id);
        return JsonResult.success();
    }

    // 查看目的地
    @GetMapping("getDestByRegionId")
    @ResponseBody
    public JsonResult getDestByRegionId(Long rid){
        // 返回的结果是目的地的数据, 最好调用目的的的业务处理方法
        List<Destination> dests = destinationService.getDestByRegionId(rid);
        return JsonResult.success(dests);
    }
}