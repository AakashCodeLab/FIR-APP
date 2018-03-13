

<?php
require_once './library/configf.php';

require_once('database.php');
 define('HOST','localhost');
  define('USER','root');
  define('PASS','');
  define('DB','fir');


/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['name']) && isset($_POST['mo']) && isset($_POST['Description'])) {
    
    $discription = $_POST['Description'];
	
	  $cphone = $_POST['mo'];
	$add = $_POST['add'];
	//$dt_occur = $_POST['occurred'];
	
	//$FirNo = $_POST['FirNo'];
	$cr_type = $_POST['crime'];

	$pstn = $_POST['stn'];
	
    $complainer_name =$_POST['name'];
	

	//$status = $_POST['status'];
	//$complainer_id = (int)$_SESSION['user_id'];
	
	
	

    // include db connect class
   // require_once __DIR__ . '/db_connect.php';

    // connecting to db
   // $db = new DB_CONNECT();

    // mysql inserting a new row
	$result="INSERT INTO fir( cid, cname, c_phone, co_id, cr_type, cr_desc, dt_occurrence, address, status, pid, pname, pstn, comment, create_date)
 VALUES ('$complainer_id','$complainer_name','$cphone','$FirNo','$cr_type','$discription','$dt_occur','$add','$status','','','$pstn','',NOW())";
	
	
   // $result = mysql_query("INSERT INTO products(name, price, description) VALUES('$name', '$price', '$description')");

    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "Fir successfully created.";

        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";
        
        // echoing JSON response
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}
?>







/*session_start();


require_once 'configf.php';
require_once('database.php');

  define('HOST','localhost');
  define('USER','root');
  define('PASS','');
  define('DB','fir');
  $con = mysqli_connect(HOST,USER,PASS,DB);
  
  $cphone = $_POST['mo'];
	$add = $_POST['add'];
	$dt_occur = $_POST['occurred'];
	
	$FirNo = $_POST['FirNo'];
	$cr_type = $_POST['crume'];

	$pstn = $_POST['stn'];
	
    $complainer_name =$_POST[name];
	

	$status = $_POST['status'];
	$discription = $_POST['decription'];
	$complainer_id = (int)$_SESSION['user_id'];
	//$complainer_name = $_SESSION['user_name'];

  
  $sql="INSERT INTO fir( cid, cname, c_phone, co_id, cr_type, cr_desc, dt_occurrence, address, status, pid, pname, pstn, comment, create_date)
 VALUES ('$complainer_id','$complainer_name','$cphone','$FirNo','$cr_type','$discription','$dt_occur','$add','$status','','','$pstn','',NOW())";
	
	
	//echo $sql;
	$result=dbQuery($sql);
	
	 if (mysql_num_rows($result))
	{
        $response["success"] = 1;
        $response["message"] = "Lodge successfully.";
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
	
	
	
	
	
	?>*/