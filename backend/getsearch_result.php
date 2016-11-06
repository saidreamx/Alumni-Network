<?php
//checking if the script received a post request or not 
if($_SERVER['REQUEST_METHOD']=='GET')
{
 //echo 'sanjeev';
 //Getting post data 
  $s_f_name = $_GET["s_f_name"]; 
  $s_l_name = $_GET["s_l_name"]; 
if(isset($_GET['s_f_name']) & !empty($_GET['s_l_name'])){
  //echo $email;
  require_once('dbConnect.php');
  $sql = "SELECT s_f_name,s_l_name FROM student WHERE s_f_name LIKE '$s_f_name%' OR  s_l_name = '$s_l_name%'";
//echo $sql;
  $res = mysqli_query($con,$sql);
  $result = array();
  while($row = mysqli_fetch_array($res)){
  //echo '$row[0]';
  array_push($result,
  array(
  's_f_name'=>$row[0],
  's_l_name'=>$row[1],  
));
}
echo json_encode(array("result"=>$result));
}
mysqli_close($con);
 }
	