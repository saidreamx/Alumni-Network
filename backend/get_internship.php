<?php
if($_SERVER['REQUEST_METHOD']=='GET')
{
  $email = $_GET["email"]; 
if(isset($_GET['email']) & !empty($_GET['email'])){
  require_once('dbConnect.php');
  $sql = "SELECT * FROM internship WHERE sid = (SELECT s_id FROM student where s_email LIKE '$email%')";
  $res = mysqli_query($con,$sql);
  $result = array();
  while($row = mysqli_fetch_array($res)){

  array_push($result,
  array('mentor'=>$row[0],
  'organisation'=>$row[1],
  'startdate'=>$row[2],
  'enddate'=>$row[3],
  'sid'=>$row[4]
));
}
echo json_encode(array("result"=>$result));
}
mysqli_close($con);
 }