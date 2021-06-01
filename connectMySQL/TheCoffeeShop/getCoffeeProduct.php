<?php
    include "connect.php";
    $query = "select * from thucdon where MALSP = 2";
    $data = mysqli_query($connection, $query);
    $productList = array();

    JSONARRAY array=new JSONARRAY(response);
    while($row = mysqli_fetch_assoc($data)){
        array_push($productList, new Product(
            $row['MaMon'],
            $row['TenMon'],
            $row['Gia'], 
            $row['srcImg'],
            $row['MoTa']
            )
        );
    } 
    echo json_encode($productList);     
    class Product{
        function Product($id, $name, $price, $srcImg, $describe){
            $this->id = $id;
            $this->name = $name;
            $this->price = $price;
            $this->srcImg = $srcImg;
            $this ->describe = $describe;
        }
    }
?>