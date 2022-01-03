package cn.wolfcode.trip.web.controller;

import cn.wolfcode.trip.common.JsonResult;
import cn.wolfcode.trip.domain.Destination;
import cn.wolfcode.trip.domain.Region;
import cn.wolfcode.trip.service.IDestinationService;
import cn.wolfcode.trip.service.IRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("destinations")
public class DestinationsController {

    @Autowired
    private IDestinationService destinationService;

    @Autowired
    private IRegionService regionService;

    // 查询是否是热门目的地
    @GetMapping("hotRegion")
    public JsonResult hotRegion(){
        List<Region> list = regionService.hotRegion();
        return JsonResult.success(list);
    }

    // 鼠标移动查询目的地区域
    @GetMapping("search")
    public JsonResult search(Long regionId){
        List<Destination> list =  destinationService.search(regionId);
        return JsonResult.success(list);
    }

}
