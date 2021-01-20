<?php
$db_name = "sql12263059";
$mysql_username="sql12263059";
$mysql_password="ARzWitS6Vb";
$server_name="sql12.freesqldatabase.com";
$conn =mysqli_connect($server_name, $mysql_username, $mysql_password, $db_name);
if($conn){
echo "connection success";
}
else{
echo "un";
} 
?>
