<?php

error_reporting(E_ERROR);
$firstname = $_REQUEST['firstname'];
$lastname = $_REQUEST['lastname'];
$employee_id = $_REQUEST['employee_id'];
$email = $_REQUEST['email'];
$password = md5($_REQUEST['password']);
$user_image = $_REQUEST['image_file'];
$user_image_en = $_REQUEST['image_file_en'];

include "db_connect.php";
include "config.php";

mysqli_select_db($db, DB_NAME) or die(mysqli_error($db));
$json = array();

$rand = substr(md5(microtime()),rand(0,26),5);
$time_date = date('Ymd');
$item_image_dir = 'profiles/'.$time_date.$rand.$user_image;

$query = 'INSERT INTO users(firstname, lastname, employee_id, email, user_password, user_image) VAlUES("'.$firstname.'", "'.$lastname.'", "'.$employee_id.'", "'.$email.'", "'.$password.'","'.$item_image_dir.'" )';

if($db->query($query) == TRUE){

$binary1 = base64_decode($user_image_en);
header('Content-Type: bitmap; charset=utf-8');

$file1 = fopen($item_image_dir, 'wb');
fwrite($file1, $binary1);
fclose($file1);

        $json['success'] = 'Registration Successful, Please Login to  use System.';
        $user_id = $db->insert_id;
        $json['user_id'] = $user_id;

     }else{
        $json['success'] =  'Registration failed, try again.';
        $json['user_id'] = 0;
     }

     print(json_encode($json));

?>