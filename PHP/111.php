<?php
$db_name = "rotonity";
$mysql_username="rotonity";
$mysql_password="rotonity";
$server_name="rotonity.000webhostapp.com";
$conn =mysqli_connect($server_name, $mysql_username, $mysql_password, $db_name);
if($conn){
echo "connection success";
}
else{
echo "un";
} 
?>
