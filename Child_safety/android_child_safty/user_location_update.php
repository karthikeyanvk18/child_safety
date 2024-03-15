<?php
extract($_POST);
include("dbconnect.php");
$first=$_REQUEST['first'];
$second=$_REQUEST['second'];
$l1=$_REQUEST['l1'];
$l2=$_REQUEST['l2'];
 
//$pass=$_REQUEST['pass'];
//$time=$_REQUEST['time'];
 
 $qry="select * from user_location where first='$first' and second='$second'";
$result = mysqli_query($conn, $qry);
if (mysqli_num_rows($result))    
{
 	$qrty=("update user_location set l1='$l1',l2='$l2' where first='$first' and second='$second'");  
 
		if ($conn->query($qrty) === TRUE) {
 	echo "success";
	}
	else
	{
	echo "failed";
	}


}
else
{
	 $qrys1="insert into user_location values('$first','$second','$l1','$l2','0','0')";
  	if ($conn->query($qrys1) === TRUE) {
 	echo "success";
	}
	else
	{
	echo "failed";
	}
}
?>
 