<?php

require_once 'configf.php';
 define('HOST','localhost');
  define('USER','root');
  define('PASS','');
  define('DB','fir');

$response = array();
if (isset($_POST['name']) && isset($_POST['pass']) ) 
  { 
    $userName = $_POST['name'];
    $password = $_POST['pass'];
   // require_once __DIR__ . '/db_connect.php';
   // $db = new DB_CONNECT();
	$sql = "SELECT  cid, cname
					FROM complainer
					WHERE cname = '$userName' AND cpass = '$password'";
			$result = dbQuery($sql);
			if (mysql_num_rows($result) == 1) {
				$row = dbFetchAssoc($result);
				$_SESSION['user_id'] = $row['cid'];
				$_SESSION['user_name'] = $row['cname'];
	
				
		    // if (mysql_num_rows($result))
	        //{
        $response["success"] = 1;
        $response["message"] = "Login successfully.";
        echo json_encode($response);
    } 
	else 
	{
        $response["success"] = 0;
        $response["message"] = "Please Enter correct mobile number and password.";
        echo json_encode($response);
    }
 
 }
 else 
 {
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
    echo json_encode($response);
} 
?>


