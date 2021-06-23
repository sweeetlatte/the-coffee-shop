<?php
    include "connect.php";
    $MaHD = $_POST['orderid'];
    $mysqli = new mysqli("localhost","root","","TheCoffeeShop");
    if(strlen($MaHD)> 0 ) {
        $query = "Select * from CTHD where MaHD = ".$MaHD;
        $data = mysqli_query($connection, $query);
        $CTHDList = array();

        while($row = mysqli_fetch_assoc($data)){
            array_push($CTHDList, new CTHD(
                $row['MaMon'],
                $row['Gia'],
                $row['GhiChu'],
                $row['SoLuong'],
                $row['ThanhTien'])
            );
        } 
        echo json_encode($CTHDList);    
    }
    class CTHD{
        function CTHD($mamon, $giaban, $topping, $soluong, $thanhtien){
            $this->mamon = $mamon;
            $this->giaban = $giaban;
            $this->topping = $topping;
            $this->soluong = $soluong;
            $this->thanhtien = $thanhtien;
        }
    }
    
?>