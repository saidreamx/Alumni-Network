<?php
if($_SERVER['REQUEST_METHOD']=='GET')
{
  $PID = $_GET["PID"]; 

  require_once('dbConnect.php');
  $sql = "SELECT * FROM comment where post_id = $PID";
  $res = mysqli_query($con,$sql);
  $result = array();
  while($row = mysqli_fetch_array($res))
  {
  array_push($result,
  array('name'=>$row[1],
  'comment'=>$row[2],
  'time'=>$row[3],
));
  }
echo json_encode(array("result"=>$result));
mysqli_close($con);
 }