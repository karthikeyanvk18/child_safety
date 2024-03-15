<?php
include("dbconnect.php");
extract($_POST);

$name=$_REQUEST['name'];
$regno=$_REQUEST['cont'];
$staff=$_REQUEST['email'];
$dept=$_REQUEST['address'];
$contact=$_REQUEST['pass'];
$child=$_REQUEST['child'];

 	


 $sql = "SELECT id FROM parent_detail order by id ASC";

 $sid=0;
  $result = $conn->query($sql);
  while($row = $result->fetch_assoc()) 
  {
       $sid=$row['id'];
  }
    $sid=$sid+1;
    
   $qrys1="insert into parent_detail values('$sid','$name','$regno','$staff','$dept','$contact','0','$child')";
  if ($conn->query($qrys1) === TRUE) {
  echo "success";
}
else
{
echo "failed";
}
?>