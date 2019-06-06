<?php
require "conn.php";
$function_type=$_POST["FUNCTIONtype"];
if ($function_type == "login") {
	# code...
	$user_name=$_POST["user_name"];
	$user_pass=$_POST["password"];
	$mysql_qry="SELECT * FROM `clear` WHERE Username LIKE '$user_name' and Password like '$user_pass'" ;
	$result = mysqli_query($conn, $mysql_qry);

	if($result){
		echo "login successful";
	}
	else{
	echo "login failed";}
}
else
{
	$part_type=$_POST['partType'];
	$desc=$_POST['description'];
	$cost=$_POST['cost'];
	$currently=$_POST['currentlyWith'];
	$purchased=$_POST['purchased'];
	$qr_result=$_POST['qrResult'];

	$what=$_POST['what'];
	if ($function_type=="add")
	{
		$mysql_qry=" INSERT INTO `Inventory`(`Part_Type`, `Description`, `Cost`, `Currently_With`, `Purchased_by`, `id`) VALUES ('$part_type','$desc','$cost','$currently','$purchased','$qr_result') " ;
			$result = mysqli_query($conn, $mysql_qry);
			if ($result) {echo "doone??";
				# code...
			}
	}
	else if($function_type=="edit"){
		$mysql_qry="INSERT INTO `Inventory`( `Part_Type`, `Description`, `Cost`, `Currently_With`, `Purchased_By`) VALUES ('$part_type','$desc','$cost','$currently','$qr_result')" ;
			$result = mysqli_query($conn, $mysql_qry);
	}
	else if ($function_type=="view") {
		
		switch ($what) {
			case "all":
				$sql="SELECT * FROM `inventory`";
				$result=mysqli_query($conn,$sql);
				while($row=mysqli_fetch_array($result,MYSQLI_ASSOC)){
		printf (": %s, %s, %s, %s, %s, %s",$row["Part_Type"],$row["id"],$row["Description"],$row["Cost"],$row["Currently_With"],$row["Purchased_by"]);
				};
			case "pT":
				$sql="SELECT `Part_Type` FROM `inventory`";
				$result=mysqli_query($conn,$sql);
				while($row=mysqli_fetch_array($result,MYSQLI_NUM)){
				printf (", %s",$row[0]);
				}
			case "id":

				$sql="SELECT * FROM `inventory` WHERE `id` LIKE '$qr_result'";
				$result=mysqli_query($conn,$sql);
				$row=mysqli_fetch_array($result,MYSQLI_ASSOC);

				printf (": %s, %s, %s, %s, %s, %s",$row["Part_Type"],$row["id"],$row["Description"],$row["Cost"],$row["Currently_With"],$row["Purchased_by"]);
				break;
		}
		# code...
	}

}
