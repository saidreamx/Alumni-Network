<?php
if($_SERVER['REQUEST_METHOD']=='GET')
{
  $s_id = $_GET["s_id"]; 
if(isset($_GET['email']) & !empty($_GET['email'])){
  require_once('dbConnect.php');
  $sql = "SELECT photo FROM project WHERE sid = s_id";
  $res = mysqli_query($con,$sql);
  $result = array();
//   while($row = mysqli_fetch_array($res)){

//   array_push($result,
//   array('ptitle'=>$row[0],
//   'pmentor'=>$row[1],
//   'pduration'=>$row[2],
//   'pdescription'=>$row[3],
//   'sid'=>$row[4]
// ));
// }

//echo json_encode(array("result"=>$result));
  echo "photo";
}
mysqli_close($con);
 }