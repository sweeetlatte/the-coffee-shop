package com.example.testproject.Untils;

public class Server {
    public static String localhost = "192.168.1.2:8080";
    public static String pathLogin = "http://" + localhost + "/TheCoffeeShop/getUser.php";
    public static String pathGetNewProduct = "http://" + localhost + "/TheCoffeeShop/getNewProduct.php";
    public static String pathGetCoffeProduct = "http://" + localhost + "/TheCoffeeShop/getCoffeeProduct.php";
    public static String pathGetTeaProduct = "http://" + localhost + "/TheCoffeeShop/getTeaProduct.php";
    public static String pathGetSmoothieProduct = "http://" + localhost + "/TheCoffeeShop/getSmoothieProduct.php";
    public static String pathInsertUser = "http://" + localhost + "/TheCoffeeShop/register.php";
}
