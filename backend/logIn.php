<?php
//checking if the script received a post request or not 
if($_SERVER['REQUEST_METHOD']=='POST'){
 
 //Getting post data 
 
 $email = $_POST['email'];
 $password = $_POST['password'];

 //If the values are not blank
 //Connecting to our database by calling dbConnect script 
 require_once('dbConnect.php');
 
 //Creating an SQL Query to insert into database 
 //Here you may need to change the retrofit_users because it is the table I created
 //if you have a different table write your table's name
 
 //This query is to check whether the username or email is already registered or not 
 $sql = "SELECT * FROM testing WHERE email_ID='$email' and password = '$password' ";
 
 //If variable check has some value from mysqli fetch array 
 //That means username or email already exist 
 $check = mysqli_fetch_array(mysqli_query($con,$sql));
 
 //Checking check has some values or not 
 if(isset($check)){
 //If check has some value that means username already exist 
 echo 'validation successful';
 }else{ 
 //If username is not already exist 
 //Creating insert query 
 echo 'invalid';
 } 
 //Closing the database connection 
 mysqli_close($con); 
}else{
echo 'error';
}