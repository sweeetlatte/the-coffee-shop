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
    echo json_encode($accountList);     
    class Account{
        function Account($sdt, $pass){
            $this->sdt = $sdt;
            $this->pass = $pass;
        }
    }
    if(isset($_POST['sdt']) && isset($_POST['pass'])){
        
    }
?>