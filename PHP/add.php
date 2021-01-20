<?php
require "conn.php";
$part_type=$_POST["partType"];
$desc=$_POST["description"];
$cost=$_POST["cost"];
$currently=$_POST["currentlyWith"];
$purchased=$_POST["purchased"];
$qr_result=$_POST["qrResult"];
$mysql_qry="INSERT INTO `Inventory`( `Part_Type`, `Description`, `Cost`, `Currently_With`, `Purchased_By`) VALUES ($part_type,$desc,$cost,$currently,$qr_result)" ;
$result = mysqli_query($conn, $mysql_qry);

if(mysqli_num_rows($result) >0 ){
echo "Add successful!";
}
else{
echo "failed";}

?>