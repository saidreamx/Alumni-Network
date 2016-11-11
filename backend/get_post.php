<?php
if($_SERVER['REQUEST_METHOD']=='GET')
{
  $start = $_GET["start"]; 

  require_once('dbConnect.php');
  $sql = "SELECT * FROM project LIMIT '$start' OFFSET 15";
  $res = mysqli_query($con,$sql);
  $result = array();
  while($row = mysqli_fetch_array($res)){

  array_push($result,
  array('name'=>$row[0],
  'time'=>$row[1],
  'post'=>$row[2],
  'comments'=>$row[3]
));
}
echo json_encode(array("result"=>$result));
mysqli_close($con);
 }