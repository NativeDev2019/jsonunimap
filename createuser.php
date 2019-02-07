<?php
include "formcreateuser.php";
// code @GITHUB
// bit.ly/gitjson
// insert.php
include "connect.php";

//$id=$_POST['id'];
if (isset($_POST['uname']) && $_POST['uname']!=null && $_POST['pwd']!=null){
	$uname=$_POST['uname'];
	$pwd=$_POST['pwd'];
	$userlevel=$_POST['userlevel'];
	$fullname=$_POST['fullname'];
	$email=$_POST['email'];

	$rs = mysqli_query($db,
		"INSERT INTO a_training_user
		(uname, pwd, userlevel, fullname, email)
		VALUES 
		('$uname', md5('$pwd'),
		'$userlevel','$fullname','$email')");

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
}//end null
else{
	echo "error";
}

?>
