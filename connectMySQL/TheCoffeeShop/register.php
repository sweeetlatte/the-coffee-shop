<?php

    $TenKH = $_POST['last_name'];
    $HoKH = $_POST['first_name'];
    $SDT = $_POST['sdt'];
    $PassWord = $_POST['password'];
    $check = false;
    $mysqli = new mysqli("localhost","root","","TheCoffeeShop");

    if ($mysqli -> connect_errno) {
      echo "Failed to connect to MySQL: " . $mysqli -> connect_error;
      exit();
    }

    $query = "INSERT INTO KHACHHANG(TenKH, SoDT, TrangThai, HoKH) values ('" . $TenKH . "', '" . $SDT . "', 1,'" . $HoKH . "')";
    $mysqli -> query($query);
    
    if (empty($mysqli -> insert_id)) {
        echo 0;
        die;
    } 

    $query2 = "  INSERT INTO TaiKhoan(SDT, PassWord, MaQuyen) values ('" .$SDT . "', '" . $PassWord . "', " . 2 . ")";
    $mysqli -> query($query2);
    
    $id = $mysqli -> insert_id;
    if (!empty($id) || $id == 0) {
        $result = 1;
    }
    else $result = 0;

    echo $result;

    $mysqli -> close();

?>