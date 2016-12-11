<?php

$target_dir = "uploads/";

$time=number_format(round(microtime(true) * 1000),0,'.','');

$target_file = $target_dir . $time .".". pathinfo($_FILES["image"]["name"],PATHINFO_EXTENSION);

$target_file_output=$time .".". pathinfo($_FILES["image"]["name"],PATHINFO_EXTENSION);

$sid=$_POST["s_id"];

echo $sid;

$uploadOk = 1;
echo "QWERTYYYYY";

// Check if file already exists
if (file_exists($target_file)) {
    echo "Sorry, file already exists.";
    $uploadOk = 0;
}

// Check if $uploadOk is set to 0 by an error

echo "VALUE OF UPLOADOK   ";
echo $uploadOk;

if ($uploadOk == 0) {
    echo "{\"Message\":\"Sorry, there was an error uploading your file.\"}";
// if everything is ok, try to upload file
} 

else {
    if (move_uploaded_file($_FILES["image"]["tmp_name"], $target_file)) {
       echo "added successfully";       
       require_once('dbConnect.php');
       echo $sid;

      $abc = "SELECT sid from photograph where sid = '$sid'";   

      $get_sid = mysqli_query($con, $abc);

//      echo $get_sid;
echo "SIZE OF NUM  ";
echo mysqli_num_rows($get_sid);

      if(mysqli_num_rows($get_sid) == 1)
      {
        $fin_q = "UPDATE photograph SET photo = '$target_file_output' where sid = '$sid'";
        $if_exe = mysqli_query($con,$fin_q);
      } 
if(mysqli_num_rows($get_sid) == 0)
      {
echo " NEW ENTRY ";
       $fin_q = "INSERT INTO photograph VALUES ('$sid','$target_file_output')";
        $if_exe = mysqli_query($con,$fin_q);
      }   
             		

		if(! $if_exe )
		{
echo "die out";
		  die('Could not update data: ' . mysqli_error($con));
		}
		mysqli_close($con);
		
	} else {
		echo "{\"Message\":\"Sorry, there was an error uploading your file.\"}";
        
    }
}
?>					