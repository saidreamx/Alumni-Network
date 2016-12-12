<?php
 
 if($_SERVER['REQUEST_METHOD']=='POST')
{
 $User_name = $_POST['User_name'];
 $Email_ID = $_POST['Email_ID'];
 $Password = $_POST['Password'];
 $Vehicle_model = $_POST['Vehicle_model'];
 $Vehicle_ID = $_POST['Vehicle_ID'];
 
 require_once('dbconnect.php');
 
 $sql = "INSERT INTO Users (User_name,Email_ID,Password,Vehicle_model,Vehicle_ID) VALUES ('$User_name','$Email_ID','$Password','$Vehicle_model','$Vehicle_ID')";
 
 
 if(mysqli_query($con,$sql)){
 echo "Successfully Registered";
 }else{
 echo "Could not register";
 
 }
 }else{
echo 'error';
}