package com.example.testproject.Model;

import java.io.Serializable;

public class ItemOrderHistory implements Serializable {
    public String date;
    public String total;
    public String orderid;

    public ItemOrderHistory(String date, String total, String orderid) {
        this.date = date;
        this.total = total;
        this.orderid = orderid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }
}
