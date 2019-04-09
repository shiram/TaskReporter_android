
<?php
error_reporting(E_ERROR);


$access_level = 0;

include 'db_connect.php';
include 'config.php';
mysqli_select_db($db, DB_NAME) or die(mysqli_error($db));

$query = 'SELECT *  FROM  users WHERE access_level="'.$access_level.'"  ORDER BY id DESC';


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