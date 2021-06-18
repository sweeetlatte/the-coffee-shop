<?php
require("connectionsettings.php");
$connection = mysqli_connect($host, $username, $password, $database);
if (!$connection) {
    die('Connect Error: ' . mysqli_connect_error());
}
?>
