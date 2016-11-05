<?php
if($_SERVER['REQUEST_METHOD']=='POST')
{
 $email = $_POST['email'];
 $password = $_POST['password'];
 require_once('dbConnect.php');
 $sql = "SELECT * FROM verify_table WHERE s_id='$email' and bool_register = 1";
 $sql2 = "SELECT * FROM verify_table WHERE s_id='$email' and bool_register = 0";
 $check = mysqli_fetch_array(mysqli_query($con,$sql));
 $check2 = mysqli_fetch_array(mysqli_query($con,$sql2));
   if(isset($check))
   {
    echo 'Already registered';
   }
  if(!isset($check2))
   {  
    $sql = "UPDATE verify_table set bool_register = 1, password = '$password' where sid = '$email'"
      if(mysqli_query($con,$sql))
      {
       echo 'successfully registered';
      }
      else
      {
       echo 'oops! Please try again!';
      }
   mysqli_close($con);
   }
}
else
{
 echo 'error';
}		