<?php
//checking if the script received a post request or not 
if($_SERVER['REQUEST_METHOD']=='POST')
{
 
 //Getting post data 
  $email = $_POST['email']; 
  require_once('dbConnect.php');
  $sql = "SELECT * FROM student WHERE email_ID='$email'";
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
mysqli_close($con);
 }

else
{
echo 'error';
}