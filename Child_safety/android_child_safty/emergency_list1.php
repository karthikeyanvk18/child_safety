<?php
extract($_POST);
include("dbconnect.php");
//$email=$_REQUEST['email'];
//$staff=$_REQUEST['pass'];
//$pass=$_REQUEST['pass'];
//$time=$_REQUEST['time'];
 
  $qry="select * from user_location";
 
 
	 		$result = $conn->query($qry);
 
$result1 = array();	 
		
	 	while($row = $result->fetch_assoc())
		  {
 							
array_push($result1,array("first"=>$row['first']));
}
echo json_encode(array("result"=>$result1));
?>
 