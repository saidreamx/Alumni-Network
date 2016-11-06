<?php
if($_SERVER['REQUEST_METHOD']=='GET')
{
  $email = $_GET["email"]; 
if(isset($_GET['email']) & !empty($_GET['email'])){
  require_once('dbConnect.php');
  $sql = "SELECT * FROM internship WHERE s_id = (SELECT s_id FROM student where s_email LIKE '$email%')";
  $res = mysqli_query($con,$sql);
  $result = array();
  while($row = mysqli_fetch_array($res)){

  array_push($result,
  array('s_id'=>$row[0],
  's_course'=>$row[1],
  's_f_name'=>$row[2],
  's_l_name'=>$row[3],
  's_internship'=>$row[4]
));
}
echo json_encode(array("result"=>$result));
}
mysqli_close($con);
 }