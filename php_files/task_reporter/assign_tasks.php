<?php

error_reporting(E_ERROR);
$employee_id = (int)$_REQUEST['employee_id'];
$names = $_REQUEST['names'];
$email = $_REQUEST['email'];
$team_leader_emp_id = $_REQUEST['team_leader_emp_id'];
$title = $_REQUEST['title'];
$description = $_REQUEST['description'];
$start_date = $_REQUEST['start_date'];
$end_date = $_REQUEST['end_date'];
$is_role_or_task = $_REQUEST['is_role_or_task'];

include "db_connect.php";
include "config.php";

mysqli_select_db($db, DB_NAME) or die(mysqli_error($db));
$json = array();


$query = 'INSERT INTO tasks(employee_id, team_leader_names, team_leader_email, team_leader_emp_id, task_title, task_description, start_date, end_date, is_role_or_task) VALUES("'.$employee_id.'", "'.$names.'", "'.$email.'", "'.$team_leader_emp_id.'", "'.$title.'","'.$description.'", "'.$start_date.'", "'.$end_date.'", "'.$is_role_or_task.'" )';

if($db->query($query) == TRUE){

        $json['success'] = 'Task Assigned Successfully.';

     }else{
        $json['success'] =  'Task Not Assigned, try Again.';
    }

     print(json_encode($json));

?>