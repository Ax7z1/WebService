package cn.items.ssm.controller;

import cn.items.ssm.po.Items;
import cn.items.ssm.po.RespBean;
import cn.items.ssm.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "ItemsController",description = "商品后台管理服务接口")
@RestController
public class ItemsController {

    @Autowired
    private ItemService itemService;

    @ApiOperation("查询所有的商品信息")
    @GetMapping("/findAll")
    public RespBean findAll() {
        List<Items> list = itemService.findAll();
        return RespBean.ok("所有产品列表", list);
    }

    @ApiOperation("根据id查询单件商品信息")
    @GetMapping("/findOne/{id}")
    public RespBean findOne(@PathVariable Integer id) {
        Items items = itemService.findItemById(id);
        return RespBean.ok("单间商品", items);
    }

    @ApiOperation("添加商品信息")
    @PostMapping("/addItems")
    public RespBean addItems(@RequestBody @ApiParam("商品业务对象") Items items){ //id?
        itemService.addItems(items);
        return RespBean.ok("修改成功",items);
    }
    @ApiOperation("根据id修改单件商品信息")
    @PutMapping("/updateItems")
    public RespBean updateItems(@RequestBody @ApiParam("商品业务对象") Items items){ //id?
        itemService.addItems(items);
        return RespBean.ok("编辑成功");
    }

    @ApiOperation("根据id删除单件商品信息")
    @DeleteMapping("/deleteItem/{id}")
    public RespBean deleteItem(Integer id){
        itemService.deleteItems(id);
        return RespBean.ok("删除成功");
    }
}
