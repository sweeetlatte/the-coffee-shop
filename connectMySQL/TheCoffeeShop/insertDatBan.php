<?php
    $MaKH = $_POST['idCustomer'];
    $SoNguoi = $_POST['numberOfPeople'];
    $Ghichu = $_POST['mesage'];
    $Ngay = $_POST['date'];
    $Gio = $_POST['time'];
    $mysqli = new mysqli("localhost","root","","TheCoffeeShop");
    if(strlen($MaKH)> 0 && strlen($Ngay) >0 && strlen($SoNguoi) > 0 && strlen($Gio) > 0) {
        $query = "insert into DatBan( MaKH, NgayDat, SoNguoi,GhiChu) values ( ".$MaKH.",'".$Ngay." ".$Gio."', ".$SoNguoi.", '".$Ghichu."' )";
        $mysqli -> query($query);
        $id = $mysqli -> insert_id;
        echo $query;
    }
    
?>