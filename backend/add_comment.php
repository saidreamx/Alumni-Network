<?php

if($_SERVER['REQUEST_METHOD']=='POST')
{ 
 $post_id = $_POST['post_id'];
 $comment = $_POST['comment'];
 $name = $_POST['name'];
 require_once('dbConnect.php');

 $sql = "INSERT INTO comment VALUES ($post_id, $comment, $name)";
 
 //$check = mysqli_fetch_array(mysqli_query($con,$sql));  
 if(mysqli_query($con, $sql))
 {
 	echo "Commented";
 }
 else
 {
 	echo "Error, commenting try again";
 }
}
else
{
  echo 'error';
}			
