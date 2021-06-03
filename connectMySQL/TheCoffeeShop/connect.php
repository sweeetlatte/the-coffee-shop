<?php
$connection = mysqli_connect('localhost', 'root', '', 'TheCoffeeShop');
if (!$connection) {
    die('Connect Error: ' . mysqli_error());
}
?>
