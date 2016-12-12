<?php
if($_SERVER['REQUEST_METHOD']=='GET')
{
  require_once('dbConnect.php');
  $sql = "SELECT * FROM post order by date";
  $res = mysqli_query($con,$sql);
  $result = array();
  while($row = mysqli_fetch_array($res))
  {
  array_push($result,
  array('post_id'=>$row[0],
  'post_data'=>$row[1],
  'post_name'=>$row[2],
  'post_time'=>$row[3]
));
  }
echo json_encode(array("result"=>$result));
mysqli_close($con);
 }	