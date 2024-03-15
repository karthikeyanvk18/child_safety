<?php
extract($_POST);
include("dbconnect.php");
$mobile=$_REQUEST['mobile'];
 
//$pass=$_REQUEST['pass'];
//$time=$_REQUEST['time'];
 
 $qry="select * from admin_mobile";
$result = mysqli_query($conn, $qry);
if (mysqli_num_rows($result))    
{
 	$qrty=("update admin_mobile set mobile='$mobile'");  
 
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
	 $qrys1="insert into admin_mobile values('$mobile','0','0')";
  	if ($conn->query($qrys1) === TRUE) {
 	echo "success";
	}
	else
	{
	echo "failed";
	}
}
?>
 