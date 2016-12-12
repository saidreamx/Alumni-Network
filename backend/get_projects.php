<?php
if($_SERVER['REQUEST_METHOD']=='GET')
{
  $email = $_GET["email"]; 
if(isset($_GET['email']) & !empty($_GET['email'])){
  require_once('dbConnect.php');
  $sql = "SELECT * FROM project WHERE sid = (SELECT s_id FROM student where s_email LIKE '$email%')";
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