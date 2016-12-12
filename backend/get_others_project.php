<?php
if($_SERVER['REQUEST_METHOD']=='GET')
{
  $name = $_GET["name"]; 
if(isset($_GET['name']) & !empty($_GET['name'])){
  require_once('dbConnect.php');
  $sql = "SELECT * FROM project WHERE sid = (SELECT s_id FROM student WHERE CONCAT(s_f_name,s_l_name) = '$name')";
  $res = mysqli_query($con,$sql);
  $result = array();
  while($row = mysqli_fetch_array($res)){

  array_push($result,
  array('ptitle'=>$row[0],
  'pmentor'=>$row[1],
  'pduration'=>$row[2],
  'pdescription'=>$row[3],
  'sid'=>$row[4]
));
}
echo json_encode(array("result"=>$result));
}
mysqli_close($con);
 }