package com.itmo.data;

import java.time.LocalDateTime;
import java.util.List;

public class Order {

/*
    номер кассы обслуживания - int
    лист позиций - List<MenuItem>
    дата начала - LocalDate
    дата конца - LocalDate
    номер заказа - String
    категория заказа (online, v_zavedenii) - enum
    персонал (выдавший заказ) - record
 */

    private int cashboxNumber;
    private List<MenuItem> items;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String orderNumber;
    private OrderCategory category;
    private Staff staff;


    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }


    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public OrderCategory getCategory() {
        return category;
    }

    public void setCategory(OrderCategory category) {
        this.category = category;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }


    public int getCashboxNumber() {
        return cashboxNumber;
    }

    public void setCashboxNumber(int cashboxNumber) {
        this.cashboxNumber = cashboxNumber;
    }
}
