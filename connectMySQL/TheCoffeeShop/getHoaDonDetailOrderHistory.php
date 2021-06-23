<?php
    include "connect.php";
    $MaHD = $_POST['orderid'];
    
    if(strlen($MaHD)> 0){
        $mysqli = new mysqli("localhost","root","","TheCoffeeShop");
        $query = "Select * from HoaDon where MaHD =".$MaHD;
        $data = mysqli_query($connection, $query);
        // $mysqli -> query($query);
        $order;

        while($row = mysqli_fetch_assoc($data)){
            $order = new Order(
                $row['DiaChiGiao'],
                $row['TongTien']);
        } 
        echo json_encode($order);     
    }
    class Order{
        function Order($address, $total){
            $this->address = $address;
            $this->total = $total;
        }
    }
?>