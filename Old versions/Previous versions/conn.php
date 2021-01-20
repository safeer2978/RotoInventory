<?php
$db_name = "barcode";
$mysql_username="barcode";
$mysql_password="Ms4j!fHd-2DX";
$server_name="den1.mysql3.gear.host";
$conn =mysqli_connect($server_name, $mysql_username, $mysql_password, $db_name);
if($conn){
echo "connection success";
}
else{
echo "un";
} 
?>
