<?php

if($_SERVER['REQUEST_METHOD']=='POST')
{ 
 $post = $_POST['post'];
 $name = $_POST['name'];
 require_once('dbConnect.php');
echo "sanjeev dubey";
 $sql = "INSERT INTO post VALUES ('', '$post', '$name', DEFAULT)";
   
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
