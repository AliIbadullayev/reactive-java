package com.itmo.data;

public class MenuItem {
 /*
    название - string
    цена - double
    количество - int
    калории - double
*/
    private String name;
    private double price;
    private double calories;

    // Геттеры и сеттеры
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public double getCalories() { return calories; }
    public void setCalories(double calories) { this.calories = calories; }

}
