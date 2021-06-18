<?php
    $MaHD = $_POST['mahd'];
    $MaMon = $_POST['mamon'];
    $SoLuong = $_POST['soluong'];
    $gia = $_POST['gia'];
    $ghichu = $_POST['ghichu'];
    $thanhtien = $_POST['thanhtien'];
    $mysqli = new mysqli("localhost","root","","TheCoffeeShop");
    if(strlen($MaHD)> 0 && strlen($MaMon) >0 && strlen($SoLuong) > 0 && strlen($gia) > 0 && strlen($ghichu) > 0 && strlen($thanhtien) > 0) {
        $query = "insert into CTHD( MaHD, MaMon, SoLuong, Gia, GhiChu, ThanhTien) values ( ".$MaHD.",".$MaMon.", ".$SoLuong.", ".$gia.", '".$ghichu."',".$thanhtien." )";
        $mysqli -> query($query);
        $id = $mysqli -> insert_id;
        echo $query;
    }
    
?>