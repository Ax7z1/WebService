package io.github.ax7z1.ws.service;

import io.github.ax7z1.ws.pojo.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService{
    @Override
    public List<Product> findAllProducts() {
        List<Product> list = new ArrayList<>();
        for (int i = 0; i <= 10; i++){
            list.add(new Product(i,"华为手机" +i,5000D,"中国深圳"));
        }

        return list;
    }
}
