<?php

error_reporting(E_ERROR);
$user_id = (int)$_REQUEST['user_id'];

include "db_connect.php";
include "config.php";

mysqli_select_db($db, DB_NAME) or die(mysqli_error($db));
$json = array();

$is_employee = 1;

$query = 'UPDATE users SET
          is_employee = "'.$is_employee.'"
		  WHERE
          id = "'.$user_id.'"';
          
if($db->query($query) == TRUE){
        $json['success'] = 'Employee Rights Granted.';

     }else{
        $json['success'] =  'Network Failed.';
     }

     print(json_encode($json));

?>