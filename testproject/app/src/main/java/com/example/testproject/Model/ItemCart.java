package com.example.testproject.Model;

public class ItemCart {

    public int idProduct;
    public String nameProduct;
    public Integer priceProduct;
    public String srcImgProduct;
 //   public String describe;
    public String topping;
    public int total;

    public ItemCart(int idProduct, String nameProduct, Integer priceProduct, String srcImgProduct, String topping, int total) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.srcImgProduct = srcImgProduct;
        this.topping = topping;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
