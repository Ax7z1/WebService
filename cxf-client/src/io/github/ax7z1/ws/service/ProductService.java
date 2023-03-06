package io.github.ax7z1.ws.service;

import io.github.ax7z1.ws.pojo.Product;

import javax.jws.WebService;
import java.util.List;

@WebService     //代理接口
public interface ProductService {

    public List<Product> findAllProducts();

}
