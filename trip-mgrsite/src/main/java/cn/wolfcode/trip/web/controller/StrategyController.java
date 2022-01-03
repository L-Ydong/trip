package cn.wolfcode.trip.web.controller;

import cn.wolfcode.trip.domain.Strategy;
import cn.wolfcode.trip.query.QueryObject;
import cn.wolfcode.trip.service.IStrategyService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("strategy")
public class StrategyController {

    @Autowired
    IStrategyService strategyService;

    @RequestMapping("list")
    public String list(@ModelAttribute("qo") QueryObject qo, Model model){
        Page<Strategy> page = strategyService.queryForList(qo);
        model.addAttribute("page",page);
        return "strategy/list";
    }
}
