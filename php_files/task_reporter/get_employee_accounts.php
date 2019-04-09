
<?php
error_reporting(E_ERROR);


$is_employee = 0;
$is_team_leader = 0;

include 'db_connect.php';
include 'config.php';
mysqli_select_db($db, DB_NAME) or die(mysqli_error($db));

$query = 'SELECT *  FROM  users WHERE is_employee="'.$is_employee.'" AND is_team_leader="'.$is_team_leader.'"  ORDER BY id DESC';


$result = mysqli_query($db, $query);

if($result){
	while($row=mysqli_fetch_array($result)){
		$data[] = $row;
	
	}
}
else {
    $data[] = 'Error getting Items';
}

print(json_encode($data));

?>