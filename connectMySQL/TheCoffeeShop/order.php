<?php
      include "connect.php";
      $MaHD = 1 ;
      $MaKH = 1;
      $Ngay = "CURDATE()";
      $TongTien = 100;
      if(strlen($MaHD)> 0 && strlen($MaKH) >0 && strlen($Ngay) > 0) {
          $query = "INSERT INTO HOADON(MAKH, NGAY, TONGTIEN) VALUES(null, '$MaKH','$Ngay','$TongTien')";
      }
?>