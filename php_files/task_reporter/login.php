<?php
error_reporting(E_ERROR);

$user_email = $_REQUEST["email"];
$user_password = md5($_REQUEST["password"]);

include "db_connect.php";
include "config.php";

mysqli_select_db($db, DB_NAME) or die(mysqli_error($db));



        $json = array();
        
        $query = 'SELECT * FROM  users 
        WHERE email = "'.$user_email.'" AND user_password = "'.$user_password.'"';
        $result = mysqli_query($db, $query);
        $row = mysqli_fetch_array($result, MYSQLI_ASSOC);

        $count = mysqli_num_rows($result);

        if($count > 0){
                    
                $json['success'] = 'Sign in Successful';
                $json['user_id'] = $row['id'];
                $json['email'] = $row['email'];
                $json['firstname'] = $row['firstname'];
                $json['lastname'] = $row['lastname'];
                $json['access_level'] = $row['access_level'];
                $json['employee_id'] = $row['employee_id'];
                $json['user_image'] = $row['user_image'];
                $json['is_team_leader'] = $row['is_team_leader'];
                $json['is_employee'] = $row['is_employee'];
            
            }else{

                $json['success'] = 'Email or Password Invalid';
                $json['user_id'] = 0;
                $json['email'] = "";
                $json['firstname'] = "";
                $json['lastname'] = "";
                $json['access_level'] = -1;
                $json['employee_id'] = "";
                $json['user_image'] = "";
                $json['is_team_leader'] = -1;
                $json['is_employee'] = -1;
            }
  
    print(json_encode($json));


?>