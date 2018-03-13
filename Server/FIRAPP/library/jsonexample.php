<?php

/*
 * Following code will get single product details
 * A product is identified by product id (pid)
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


    // get a product from products table
    $result = mysql_query("SELECT *FROM fir");

    if (!empty($result)) {
        // check for empty result
        if (mysql_num_rows($result) > 0) {

            $result = mysql_fetch_array($result);

            $product = array();
            $product["cid"] = $result["cid"];
            $product["cname"] = $result["cname"];
           // $product["price"] = $result["price"];
            //$product["description"] = $result["description"];
            //$product["created_at"] = $result["created_at"];
           // $product["updated_at"] = $result["updated_at"];
            // success
            $response["success"] = 1;

            // user node
            $response["product"] = array();

            array_push($response["product"], $product);

            // echoing JSON response
            echo json_encode($response);
        } else {
            // no product found
            $response["success"] = 0;
            $response["message"] = "No product found";

            // echo no users JSON
            echo json_encode($response);
        }
    } else {
        // no product found
        $response["success"] = 0;
        $response["message"] = "No product found";

        // echo no users JSON
        echo json_encode($response);
    }

?>


/*
require_once 'configf.php';
define('HOST','localhost');
define('USER','');
define('PASS','');
define('DB','fir');
 
$con = mysqli_connect(HOST,USER,PASS,DB);
 
 $result = mysql_query("SELECT *FROM complainer");

    if (!empty($result)) {
        // check for empty result
        if (mysql_num_rows($result) > 0) {

            $result = mysql_fetch_array($result);

            $product = array();
            $product["pid"] = $result["cid"];
            $product["name"] = $result["cname"];
             // success
            $response["success"] = 1;

            // user node
            $response["product"] = array();

            array_push($response["product"], $product);

            // echoing JSON response
            echo json_encode($response);
		}
	}

 */