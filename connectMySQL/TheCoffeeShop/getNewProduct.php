<?php
    include "connect.php";
    $query = "select * from thucdon order by MaMon DESC LIMIT 6";
    $data = mysqli_query($connection, $query);
    $productList = array();

    while($row = mysqli_fetch_assoc($data)){
        array_push($productList, new Product(
            $row['MaMon'],
            $row['TenMon'],
            $row['Gia'], 
            $row['srcImg']
            )
        );
    } 
    echo json_encode($productList);     
    class Product{
        function Product($id, $name, $price, $srcImg){
            $this->id = $id;
            $this->name = $name;
            $this->price = $price;
            $this->srcImg = $srcImg;

        }
    }
?>