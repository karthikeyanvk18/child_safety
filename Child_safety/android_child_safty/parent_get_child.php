<?php
extract($_POST);
include("dbconnect.php");
$event=$_REQUEST['mail'];
$staff=$_REQUEST['pass'];
//$pass=$_REQUEST['pass'];
//$time=$_REQUEST['time'];
 
  $qry="select * from parent_detail where contact='$event' && pass='$staff'";
$result = $conn->query($qry);
 
	 
		$mobile="";
	 	while($row = $result->fetch_assoc())
		  {
		  $mobile=$row['child'];
		  }
		  echo $mobile;
?>
 