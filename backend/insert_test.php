<?php
//checking if the script received a post request or not 
if($_SERVER['REQUEST_METHOD']=='POST'){
 
 //Getting post data 
 
 $email = $_POST['email'];
 $password = $_POST['password'];
 
 //checking if the received values are blank
 if($password == '' || $email == ''){
 //giving a message to fill all values if the values are blank
 echo 'please fill all values';
 }else{


 require_once('dbConnect.php');
 
 $sql = "SELECT * FROM testing WHERE `email_ID`='$email'";
$query=mysqli_query($con,$sql);
 $check=mysqli_num_rows($query);
 //Checking check has some values or not 
 if($check > 0){
echo $check;
 echo 'useris already exists';
 }else{ 
 //If username is not already exist 
 //Creating insert query 
 $sql = "INSERT INTO testing (email_ID,password) VALUES('$email','$password')";
 
 //Trying to insert the values to db 
 if(mysqli_query($con,$sql)){
 //If inserted successfully 
 echo 'successfully registered';
 }else{
 //In case any error occured 
 echo 'oops! Please try again!';
 }
 }
 //Closing the database connection 
 mysqli_close($con);
 }
}else{
echo 'error';
}