<?php
// code @GITHUB
// bit.ly/gitjson
// insert.php
include "connect.php";

$id=$_POST['id'];
$trainingname=$_POST['trainingname'];
$website=$_POST['website'];
$contact=$_POST['contact'];
$trainingdesc=$_POST['trainingdesc'];

$rs = mysqli_query($db,
	"INSERT INTO a_training 
	VALUES 
	('$id','$trainingname', '$website',
	'$contact','$trainingdesc')");

//echo (mysql_error());

// check for empty result

if ($rs==true) {
	//insert successfull

		echo "success";

	}//end match found

	else{//no match found

		//send status fail

		echo "error";

	}

?>
