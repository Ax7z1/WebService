package cn.items.ssm.service;

import cn.items.ssm.po.Items;

import java.util.List;

public interface ItemService {
    public List<Items> findAll();
    public Items findItemById(Integer id);

    public void addItems(Items items);
    public void updateItems(Items items);
    public void deleteItems(Integer id);


}
