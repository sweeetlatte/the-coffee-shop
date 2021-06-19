<?php
    include "connect.php";
    $MaKH = $_POST['maKH'];
    
    if(strlen($MaKH)> 0){
        $mysqli = new mysqli("localhost","root","","TheCoffeeShop");
        $query = "Select * from HoaDon where MaKH =".$MaKH;
        $data = mysqli_query($connection, $query);
        // $mysqli -> query($query);
        $orderList = array();

        while($row = mysqli_fetch_assoc($data)){
            array_push($orderList, new Order(
                $row['NgayHD'],
                $row['TongTien'],
                $row['MaHD'])
            );
        } 
        echo json_encode($orderList);     
    }
    class Order{
        function Order($date, $total, $orderid){
            $this->date = $date;
            $this->total = $total;
            $this->orderid = $orderid;
        }
    }
?>