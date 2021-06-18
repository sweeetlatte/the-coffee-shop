<?php
    include "connect.php";
    $query = "select * from taikhoan";
    $data = mysqli_query($connection, $query);
    $accountList = array();

    while($row = mysqli_fetch_assoc($data)){
        array_push($accountList, new Account(
            $row['SDT'],
            $row['PassWord'])
        );
    }
  
    class Account{
        function Account($sdt, $pass){
            $this->sdt = $sdt;
            $this->pass = $pass;
        }
    }

    //  while($row = mysqli_fetch_assoc($data)){
    //     $accountList = array(
    //         'sdt' => $row['SDT'],
    //         'pass' => $row['PassWord'],
    //         'idcustomer' => $row['MaKH']);
    // }
    echo json_encode($accountList);     
    if(isset($_POST['sdt']) && isset($_POST['pass']) && isset($_POST['idcustomer'])){
        
    }
?>