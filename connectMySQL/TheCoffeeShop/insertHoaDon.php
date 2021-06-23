<?php
    $MaKH = $_POST['makh'];
    $Diachi = $_POST['diachi'];
    $ToTal = $_POST['tongtien'];
    $mysqli = new mysqli("localhost","root","","TheCoffeeShop");
    if(strlen($Diachi) == 0) echo 0;
    if(strlen($MaKH)> 0 && strlen($Diachi) >0 && strlen($ToTal) > 0) {
        $query = "insert into HoaDon(  MaKH, NgayHD, DiaChiGiao, Ship, TongTien) values ( '".$MaKH."', CURRENT_TIMESTAMP(), '".$Diachi."', 10000, ".$ToTal." )";
        $mysqli -> query($query);
        $id = $mysqli -> insert_id;
        echo $id;
    }

?>
