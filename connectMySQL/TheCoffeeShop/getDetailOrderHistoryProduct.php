<?php
    include "connect.php";
    $MaMon = $_POST['masp'];
    $mysqli = new mysqli("localhost","root","","TheCoffeeShop");
    if(strlen($MaMon)> 0 ) {
        $query = "Select * from ThucDon where MaMon = ".$MaMon;
        $data = mysqli_query($connection, $query);
        $MonList ;

        while($row = mysqli_fetch_assoc($data)){
            $MonList = new Mon(
                $row['TenMon'],
                $row['srcImg']
            );
        } 
    }
    class Mon{
        function Mon($tensanpham, $srcImg){
            $this->tensanpham = $tensanpham;
            $this->srcImg = $srcImg;
        }
    }
    echo json_encode($MonList);    
    
?>