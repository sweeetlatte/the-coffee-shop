<?php
    include "connect.php";
    $query = "select * from LoaiSanPham";
    $data = mysqli_query($connection, $query);
    $categoryList = array();

    while($row = mysqli_fetch_assoc($data)){
        array_push($categoryList, new Category(
            $row['MaLSP'],
            $row['TenLSP'])
        );
    } 
    echo json_encode($categoryList);     
    class Category{
        function Category($id, $name){
            $this->id = $id;
            $this->name = $name;
        }
    }
?>