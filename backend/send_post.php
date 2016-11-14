<?php

if($_SERVER['REQUEST_METHOD']=='POST')
{ 
 $post_id = $_POST['post_id'];
 $post = $_POST['post'];
 $name = $_POST['name'];
 require_once('dbConnect.php');

 $sql = "INSERT INTO post VALUES ($post_id, $post, $name, DEFAULT)";
 
 //$check = mysqli_fetch_array(mysqli_query($con,$sql));  
 if(mysqli_query($con, $sql))
 {
 	echo "Posted";
 }
 else
 {
 	echo "Error, Posting try again";
 }
}
else
{
  echo 'error';
}			
