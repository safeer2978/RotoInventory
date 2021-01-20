<?php
require "conn.php";
$user_name=$_POST["user_name"];
$user_pass=$_POST["password"];
$mysql_qry="SELECT * FROM `clear` WHERE Username LIKE '$user_name' and Password like '$user_pass'" ;
$result = mysqli_query($conn, $mysql_qry);

if($result){
echo "login successful";
}
else{
echo "failed";}

?>
