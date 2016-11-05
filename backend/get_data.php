<?php
define('HOST','mysql.hostinger.in');
define('USER','u562159323_getme');
define('PASS','simplex55');
define('DB','u562159323_track');
$con = mysqli_connect(HOST,USER,PASS,DB);
$sql = "select * from student where s_email = ";
$res = mysqli_query($con,$sql);
$result = array();
while($row = mysqli_fetch_array($res)){
array_push($result,
array('time'=>$row[0],
'latitude'=>$row[1],
'longitude'=>$row[2]
));
}
echo json_encode(array("result"=>$result));
mysqli_close($con);
?>