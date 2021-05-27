package com.example.testproject.Model;

import java.io.Serializable;

public class ItemCart implements Serializable {

    public int idProduct;
    public String nameProduct;
    public Integer priceProduct;
    public String srcImgProduct;
    public String topping;
    public int quantity;
    public int total;

    public ItemCart(int idProduct, String nameProduct, Integer priceProduct, String srcImgProduct, String topping, int quantity, int total) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.srcImgProduct = srcImgProduct;
        this.topping = topping;
        this.quantity = quantity;
        this.total = total;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Integer getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(Integer priceProduct) {
        this.priceProduct = priceProduct;
    }

    public String getSrcImgProduct() {
        return srcImgProduct;
    }

    public void setSrcImgProduct(String srcImgProduct) {
        this.srcImgProduct = srcImgProduct;
    }

    public String getTopping() {
        return topping;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
