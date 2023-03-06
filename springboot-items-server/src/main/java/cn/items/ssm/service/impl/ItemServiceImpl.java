package cn.items.ssm.service.impl;

import cn.items.ssm.mapper.ItemsMapper;
import cn.items.ssm.po.Items;
import cn.items.ssm.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemsMapper itemsMapper;

    @Override
    public List<Items> findAll() {
        return itemsMapper.selectByExample(null);
    }

    @Override
    public Items findItemById(Integer id) {
        return itemsMapper.selectByPrimaryKey(id);
    }

    @Override
    public void addItems(Items items) {
        itemsMapper.insert(items);
    }

    @Override
    public void updateItems(Items items) {
        itemsMapper.updateByPrimaryKey(items);
    }

    @Override
    public void deleteItems(Integer id) {
        itemsMapper.deleteByPrimaryKey(id);
    }
}
