<?php

include 'db_connect.php';


$query = 'CREATE DATABASE IF NOT EXISTS task_reporter_db';

if($db->query($query) == TRUE){
	echo 'Database created Successfully';

//create tables.

include 'config.php';

	mysqli_select_db($db, DB_NAME) or die(mysqli_error($db));


	$query = 'CREATE TABLE IF NOT EXISTS users(
	             id     INTEGER UNSIGNED   NOT NULL AUTO_INCREMENT,
	             firstname           VARCHAR(55)       NOT NULL,
				 lastname            VARCHAR(55)       NOT NULL,
				 employee_id         VARCHAR(55)       NOT NULL,
				 email               VARCHAR(55)       NOT NULL,
				 user_password       VARCHAR(255)      NOT NULL,
				 user_image          VARCHAR(255)      NOT NULL,
				 access_level        TINYINT NOT NULL DEFAULT 0,
				 is_team_leader        TINYINT NOT NULL DEFAULT 0,
				 is_employee        TINYINT NOT NULL DEFAULT 0,    
	             created_at          TIMESTAMP,
	             updated_at          TIMESTAMP,	 
	             PRIMARY KEY(id))
	             ENGINE=MyISAM';

	 if($db->query($query) == TRUE){
	 	echo 'Created Users table successfully.';
	 }else{
	 	echo 'Server Error while Creating Users Table:

	 	 '. $db->error;
	 }

	 	$query = 'CREATE TABLE IF NOT EXISTS tasks(
	             task_id        INTEGER UNSIGNED   NOT NULL AUTO_INCREMENT,
	             employee_id             INTEGER UNSIGNED    NOT NULL,
	             team_leader_names               VARCHAR(255)       NOT NULL,
	             team_leader_email               VARCHAR(255)       NOT NULL,
	             team_leader_emp_id               VARCHAR(55)       NOT NULL,
				 task_title            VARCHAR(255)       NOT NULL,
				 task_description         MEDIUMTEXT       NOT NULL,
				 start_date               VARCHAR(55)       NOT NULL,
				 end_date            VARCHAR(55)      NOT NULL,	 
				 is_role_or_task            VARCHAR(55)      NOT NULL,
				 created_at          TIMESTAMP,
	             updated_at          TIMESTAMP,	 
	             PRIMARY KEY(task_id),
	             FOREIGN KEY(employee_id) REFERENCES users(id))
	             ENGINE=MyISAM';

	 if($db->query($query) == TRUE){
	 	echo 'Created Tasks table successfully.';
	 }else{
	 	echo 'Server Error while Creating Tasks Table:

	 	 '. $db->error;
	 }

	 }else{
		 echo 'DATABASE NOT CREATED';
	 } 
?>