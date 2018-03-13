<?php
//require_once 'configf.php';

/* define('HOST','localhost');
//echo "connection";
  define('USER','root');
//echo "connection";
  define('PASS','');
//echo "connection";
  define('DB','fir');*/
  $dbHost = 'localhost';
$dbUser = 'root';
$dbPass = '';
$dbName = 'employee';

$dbConn = mysql_connect($dbHost, $dbUser, $dbPass) or die ('MySQL connect failed. ' . mysql_error());
mysql_select_db($dbName) or die('Cannot select database. ' . mysql_error());

//echo "connection";
$response = array();
//if (isset($_POST['name']) && isset($_POST['pass']) ) 
 // { 
   // $userName = $_POST['name'];
   // $password = $_POST['pass'];
   // require_once __DIR__ . '/db_connect.php';
   // $db = new DB_CONNECT();
	$sql = "SELECT  *
					FROM data
					WHERE username = 'say1' AND password= 'say1'";
					
			$result =  mysql_query($sql);
			if (mysql_num_rows($result) == 1) {echo "Success";
				$row = mysql_fetch_assoc($result);
				//$_SESSION['user_id'] = $row['cid'];
				//$_SESSION['user_name'] = $row['cname'];
	
				
		    // if (mysql_num_rows($result))
	        //{
        $response["success"] = 1;
        $response["message"] = "Login successfully.";
        echo json_encode($response);
    } 
	//else
	//echo "unsucess";
 
 
/* else 
 {
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
    echo json_encode($response);
} */
?>





