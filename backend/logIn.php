<?php

if($_SERVER['REQUEST_METHOD']=='POST')
{ 
 $email = $_POST['email'];
 $password = $_POST['password'];
 require_once('dbConnect.php');
 $sql = "SELECT * FROM testing WHERE email_ID = '$email' AND password = '$password' ";
 $check = mysqli_fetch_array(mysqli_query($con,$sql));
  if(isset($check))
  {
   echo 'validation successful';
  }
  else
  { 
   echo 'invalid';
  } 
 mysqli_close($con); 
}
else
{
  echo 'error';
}			
