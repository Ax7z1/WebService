package io.github.ax7z1.web.controller;

import io.github.ax7z1.web.pojo.Items;
import io.github.ax7z1.web.pojo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller //返回视图 所以用Controller好。 用RestController返回的全部都是json
public class ItemsWebController {

    @Autowired
    private RestTemplate restTemplate;  //http的交互工具

    public String baseURL= "http://localhost:8099/";

    @GetMapping("/toAdd")
    public String toAdd(){
        return "addItem";
    }

    @GetMapping("/queryItems")
    public ModelAndView queryItems(){
        RespBean respBean = restTemplate.getForObject(baseURL + "findAll", RespBean.class);
        ModelAndView mv = new ModelAndView();
        mv.addObject("itemList",respBean.getData());
        mv.setViewName("itemlist");
        return mv;
    }

    @PostMapping("/addItemsSubmit")
    public String addItemsSubmit(Items items){
        RespBean respBean = restTemplate.postForObject(baseURL + "addItems", items, RespBean.class);
        System.out.println(respBean.getMessage());
        if (respBean.getCode()==200){
            return "redirect:/queryItems";
        }else{
            return "addItem";
        }
    }
}
