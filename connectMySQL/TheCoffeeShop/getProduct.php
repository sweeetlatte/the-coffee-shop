<?php
    include "connect.php";
    $query = "select * from thucdon";
    $data = mysqli_query($connection, $query);
    $productList = array();

    while($row = mysqli_fetch_assoc($data)){
        array_push($productList, new Product(
            $row['MaMon'],
            $row['TenMon'],
            $row['Gia']);
    } 
    echo json_encode($productList);     
    class Product{
        function Product($id, $name){
            $this->id = $id;
            $this->name = $name;
        }
    }
?>