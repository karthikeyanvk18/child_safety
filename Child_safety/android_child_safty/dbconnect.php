 
<?php

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "android_child_safty";

$conn = new mysqli($servername, $username, $password, $dbname);
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 
 
?>