<?php
//checking if the script received a post request or not 
if($_SERVER['REQUEST_METHOD']=='POST'){
 
 //Getting post data 
 
 $title = $_POST['email'];
 $mentor = $_POST['password'];
 $duration = $_POST['duration'];
 $description = $_POST['description'];
 $sid = $_POST['sid'];

 
 //checking if the received values are blank
 if($title == '' || $mentor == '' || $duration == '' || $sid == ''){
 //giving a message to fill all values if the values are blank
 echo 'please fill all values';
 }else{
 //If the values are not blank
 //Connecting to our database by calling dbConnect script 
 require_once('dbConnect.php');
 
 //Creating an SQL Query to insert into database 
 //Here you may need to change the retrofit_users because it is the table I created
 //if you have a different table write your table's name
 
 //This query is to check whether the username or email is already registered or not 
 
 //If username is not already exist 
 //Creating insert query 
 $sql = "INSERT INTO project (ptitle,pmentor,pduration,pdescription,sid) VALUES('$title','$mentor','$duration','$description','$sid')";
 
 //Trying to insert the values to db 
 if(mysqli_query($con,$sql)){
 //If inserted successfully 
 echo 'successfully added';
 }else{
 //In case any error occured 
 echo 'oops! Please try again!';
 }
 
 //Closing the database connection 
 mysqli_close($con);
 
else{
echo 'error';
}
