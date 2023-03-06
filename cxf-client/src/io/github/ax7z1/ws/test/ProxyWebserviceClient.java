package io.github.ax7z1.ws.test;

import io.github.ax7z1.ws.pojo.Product;
import io.github.ax7z1.ws.service.ProductService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import java.util.List;

public class ProxyWebserviceClient {
    public static void main(String[] args) {

        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ProductService.class);
        factory.setAddress("http://localhost:8080/services/productService");

        ProductService productService = (ProductService) factory.create();
        List<Product> list = productService.findAllProducts();  // 远程方法调用
        for (Product product : list){
            System.out.println(product.getId()+","+product.getName()+","+product.getPrice()+","+product.getAddress());
        }

    }
}
