<?php
extract($_POST);
include("dbconnect.php");
$event=$_REQUEST['mail'];
$staff=$_REQUEST['pass'];
//$pass=$_REQUEST['pass'];
//$time=$_REQUEST['time'];
 
  $qry="select * from parent_detail where contact='$event' && pass='$staff'";
$result = mysqli_query($conn, $qry);
 if (mysqli_num_rows($result)) 
  {
echo "success";
}
else
{
echo "failed";
}
?>
 