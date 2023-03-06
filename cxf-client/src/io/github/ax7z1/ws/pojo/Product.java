package io.github.ax7z1.ws.pojo;

import java.io.Serializable;

/**
 * 产品的封装类
 */
public class Product implements Serializable {
    private Integer id;
    private String name;
    private Double price;
    private String address;

    public Product() {
    }

    public Product(Integer id, String name, Double price, String address) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
