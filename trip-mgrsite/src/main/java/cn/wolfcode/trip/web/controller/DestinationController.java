package cn.wolfcode.trip.web.controller;

import cn.wolfcode.trip.common.JsonResult;
import cn.wolfcode.trip.domain.Destination;
import cn.wolfcode.trip.query.DestinationQuery;
import cn.wolfcode.trip.service.IDestinationService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("destination")
public class DestinationController {

    @Autowired
    private IDestinationService destinationService;

    // 目的地管理列表
    @RequestMapping("list")
    public String list(@ModelAttribute("qo") DestinationQuery qo, Model model){
        Page<Destination> page = destinationService.queryForList(qo);
        model.addAttribute("page",page);
        // 共享所有的当前的上级目录列表
        List<Destination> toasts = destinationService.queryToasts(qo.getParentId());
        model.addAttribute("toasts",toasts);
        return "destination/list";
    }

    // 目的地简介的修改
    @PostMapping("updateInfo")
    @ResponseBody
    public JsonResult updateInfo(Long id, String info){
        destinationService.updateInfo(id,info);
        return JsonResult.success();
    }

    // 目的地的删除
    @GetMapping("delete")
    @ResponseBody
    public JsonResult delete(Long id){
        destinationService.removeById(id);
        return JsonResult.success();
    }

    // 目的地的吐司
    @GetMapping("toasts")
    @ResponseBody
    public JsonResult toasts(Long parentId){
        System.out.println("parentId = " + parentId);
        List<Destination> list = destinationService.queryToasts(parentId);
        return JsonResult.success(list);
    }

}