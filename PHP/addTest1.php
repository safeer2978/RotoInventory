<?php
require "conn.php";
$part_type="motor";
$desc="Test entry";
$cost="10k";
$currently="safeer";
$purchased="safeer";
$qr_result="12123312";
$mysql_qry="INSERT INTO `Inventory`( `Part_Type`, `Description`, `Cost`, `Currently_With`, `Purchased_By`) VALUES ("$part_type","$desc","$cost","$currently","$qr_result")" ;
$result = mysqli_query($conn, $mysql_qry);

if(mysqli_num_rows($result) >0 ){
echo "Add successful!";
}
else{
echo "failed";}

?>