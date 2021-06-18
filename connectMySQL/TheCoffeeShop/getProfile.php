<?php
    include "connect.php";
    $numberPhone = $_POST['numberPhone'];
    $query = "select * from KhachHang where SoDT = ".$numberPhone;
    $data = mysqli_query($connection, $query);

    while($row = mysqli_fetch_assoc($data)){
         $profile = new Profile(
            $row['MaKH'],
            $row['TenKH'],
            $row['HoKH']
        );
    }
  
    class Profile{
        function Profile($MaKH, $TenKH, $HoKH){
            $this->MaKH = $MaKH;
            $this->TenKH = $TenKH;
            $this->HoKH = $HoKH;
        }
    }
    echo json_encode($profile);     
?>