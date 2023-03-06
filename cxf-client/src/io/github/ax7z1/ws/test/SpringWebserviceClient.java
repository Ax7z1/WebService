package io.github.ax7z1.ws.test;

import io.github.ax7z1.ws.pojo.Product;
import io.github.ax7z1.ws.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * 远程调用的程序（独立）
 * 远程的边界怎么判断？ 以工程？
 *      以JVM为基础（以容器为基础）
 *
 */
public class SpringWebserviceClient {
    public static void main(String[] args) {

        //读取spring容器
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 远程方法调用的代理接口实例
        // 对比体会： mybatis    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        ProductService productService =  (ProductService) context.getBean("productService");
        List<Product> list = productService.findAllProducts();

        for (Product product : list){
            System.out.println(product.getId() +"," + product.getName()+","+product.getPrice()+","+product.getAddress());
        }

    }
}
