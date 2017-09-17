package com.example.artefacto.order_app_client;

/**
 * Created by fjrois on 18/11/16.
 */
public class Product {
    private String name;
    private String type;
    private double price;
    private String drawable;
    private int id;


    public Product(){}
    public Product(String name, String type, String drawable, double price){
        this.name = name;
        this.type = type;
        this.drawable = drawable;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public String getDrawable() {
        return drawable;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDrawable(String drawable) {
        this.drawable = drawable;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
