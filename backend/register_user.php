<?php
if($_SERVER['REQUEST_METHOD']=='POST')
{
	$sid = $_POST['sid'];
 	$password = $_POST['password'];
 	require_once('dbConnect.php');
 	$sql = "SELECT * FROM verify_table WHERE s_id='$sid'";
 	$check = mysqli_fetch_array(mysqli_query($con,$sql));
 	if(isset($check))
 	{
 		$sql = "SELECT * FROM verify_table WHERE s_id='$sid' and bool_register = '1'";
 		$check = mysqli_fetch_array(mysqli_query($con,$sql));
 		if(isset($check))
 		{
 			echo 'Already registered';
 		}	
 		else
 		{
 			$sql = "UPDATE verify_table SET bool_register = 1, password = '$password' WHERE sid='$sid'";
 			echo 'Registered successfully';
 		}
 	}
}
else
{
	echo 'error';
}