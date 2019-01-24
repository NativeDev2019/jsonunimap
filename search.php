<?php
// code @GITHUB
// bit.ly/gitjson
// search.php
include "connect.php";

//keyword
$key=$_GET['key'];

//generate JSON from table
//$traininglist= array();
$response["traininglist"]=array();

$sql="SELECT id,trainingname,website,trainingdesc
FROM a_training
WHERE trainingname LIKE '%$key%'
OR trainingdesc LIKE  '%$key%' ";
//run query
$rs=mysqli_query($db,$sql);
if($rs==false){
	echo mysqli_error($rs);
}

//no record found
if (mysqli_num_rows($rs)==0){
}
else{//found some records
	while($rec=mysqli_fetch_array($rs)){
		//capture one record
		$traininglist=array();
		$traininglist["id"] = $rec["id"];
		$traininglist["trainingname"] = $rec["trainingname"];
		$traininglist["website"] = $rec["website"];
		
		//push to response
		array_push($response["traininglist"], $traininglist);
	}//end while
}//end found records

//generate JSON encoded data
//print_r $traininglist;
echo json_encode($response);

//database 

?>