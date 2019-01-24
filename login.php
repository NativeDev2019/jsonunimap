<?php
include "formlogin.php";
//login.php
include "connect.php";
//provide username& password
$uname=$_POST['uname'];
$pwd=$_POST['pwd'];

//SQL
$sql="SELECT * from a_training_user
	WHERE uname='$uname'
	AND pwd=md5('$pwd')";
//run sql
$rs=mysqli_query($db, $sql);
//check verification
if(mysqli_num_rows($rs)==1){//verified
	$rec=mysqli_fetch_array($rs);
	//return admin fullname
	echo $rec['fullname'];
}
else{//error on username/password
	echo "error";
}

?>




