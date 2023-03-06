package cn.items.ssm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.items.ssm.mapper")
public class ItemApp{
    public static void main(String[] args) {
        SpringApplication.run(ItemApp.class,args);
    }
}
