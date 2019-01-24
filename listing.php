<?php
//listing.php
include "connect.php";

//test simple JSON
$traininglist= array();
$traininglist["id"] = "A3";
$traininglist["trainingname"] = "Android and JSON";
// push single training into final response array
$response["traininglist"]=array();
array_push($response["traininglist"], $traininglist);
//generate JSON encoded data
//print_r $traininglist;
echo json_encode($response);

//database 

?>