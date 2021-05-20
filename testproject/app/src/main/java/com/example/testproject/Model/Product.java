package com.example.testproject.Model;

public class Product {
    public int idProduct;
    public String nameProduct;
    public Integer priceProduct;
    public String srcImgProduct;

    public Product(int idProduct, String nameProduct, Integer priceProduct, String srcImgProduct) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.srcImgProduct = srcImgProduct;
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
}