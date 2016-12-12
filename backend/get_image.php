<?php
if($_SERVER['REQUEST_METHOD']=='GET')
{
  $s_id = $_GET["s_id"]; 
  require_once('dbConnect.php');
  $sql = "SELECT photo FROM photograph WHERE sid = '$s_id'";
$res = mysqli_query($con,$sql);
if(mysqli_num_rows($res)>0)
{
//$res = mysqli_query($con,$sql);
 $row = mysqli_fetch_array($res);
  echo $row[0];
mysqli_close($con);
}

else
{
  $sql1 = "SELECT photo FROM photograph WHERE sid = '0'";
$res1 = mysqli_query($con,$sql1);
 $row = mysqli_fetch_array($res1);
  echo $row[0];
mysqli_close($con);
}
  
 }    