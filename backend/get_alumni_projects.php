<?php
if($_SERVER['REQUEST_METHOD']=='GET')
{
  $email = $_GET["email"]; 
if(isset($_GET['email']) & !empty($_GET['email'])){
  require_once('dbConnect.php');
  $sql = "SELECT * FROM alumni WHERE a_id = (SELECT s_id FROM student where s_email LIKE '$email%')";
  $res = mysqli_query($con,$sql);
  $result = array();
  while($row = mysqli_fetch_array($res)){

  array_push($result,
  array('a_id'=>$row[0],
  'a_indus_project'=>$row[1],
  'a_score'=>$row[2],
  'amentor'=>$row[3]
));
}
echo json_encode(array("result"=>$result));
}
mysqli_close($con);
 }