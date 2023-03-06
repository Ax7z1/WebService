package cn.items.ssm.controller;

import cn.items.ssm.po.Items;
import cn.items.ssm.po.RespBean;
import cn.items.ssm.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/findAll")
    public RespBean findAll() {
        List<Items> list = itemService.findAll();
        return RespBean.ok("所有产品列表", list);
    }

    @GetMapping("/findOne/{id}")
    public RespBean findOne(@PathVariable Integer id) {
        Items items = itemService.findItemById(id);
        return RespBean.ok("单间商品", items);
    }
    @PostMapping("/addItems")
    public RespBean addItems(@RequestBody Items items){ //id?
        itemService.addItems(items);
        return RespBean.ok("修改成功",items);
    }

    @PutMapping("/updateItems")
    public RespBean updateItems(@RequestBody Items items){ //id?
        itemService.addItems(items);
        return RespBean.ok("编辑成功");
    }

    @DeleteMapping("/deleteItem/{id}")
    public RespBean deleteItem(Integer id){
        itemService.deleteItems(id);
        return RespBean.ok("删除成功");
    }
}
