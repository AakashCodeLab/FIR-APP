<?php

/*
 * Following code will get single status details
 * A status is identified by status id (pid)
 */

// array for JSON response
$response = array();


// include db connect class


require_once 'configf.php';
define('HOST','localhost');
define('USER','');
define('PASS','');
define('DB','fir');

// check for post data
if (isset($_GET["fid"])) {
	 $fid = 'BRP5L631';
   // $fid = $_GET['fid'];

    // get a status from fir table
    $result = mysql_query("SELECT *FROM fir WHERE co_id= $fid");

    if (!empty($result)) {
        // check for empty result
        if (mysql_num_rows($result) > 0) {

            $result = mysql_fetch_array($result);

            $status = array();
            $status["cid"] = $result["cid"];
            $status["cname"] = $result["cname"];
           // $status["price"] = $result["price"];
            //$status["description"] = $result["description"];
            //$status["created_at"] = $result["created_at"];
            //$status["updated_at"] = $result["updated_at"];
            // success
            $response["success"] = 1;

            // user node
            $response["status"] = array();

            array_push($response["status"], $status);

            // echoing JSON response
            echo json_encode($response);
        } else {
            // no status found
            $response["success"] = 0;
            $response["message"] = "No status found";

            // echo no users JSON
            echo json_encode($response);
        }
    } else {
        // no status found
        $response["success"] = 0;
        $response["message"] = "No status found";

        // echo no users JSON
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