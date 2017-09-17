package com.example.maria.order_app_restaurant;

public class Order {

    private int id;
    private String drinks;
    private String food;
    private String customerName;
    private String phoneNumber;

    public Order(){}

    public Order(String customerName, String phoneNumber, String drinks, String food) {
        super();
        this.drinks = drinks;
        this.food = food;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
    }

    //getters & setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getDrinks() {
        return drinks;
    }
    public void setDrinks(String drinks) {
        this.drinks = drinks;
    }

    public String getFood() {
        return food;
    }
    public void setFood(String food) {
        this.food = food;
    }

    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Order [id=" + Integer.toString(id) + ", customerName="+customerName+", drinks=" + drinks + ", food=" + food
                + ", phoneNumber=" + phoneNumber+" ]";
    }

}