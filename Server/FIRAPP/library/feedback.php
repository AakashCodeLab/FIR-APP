<?php
 session_start();
  

require_once 'configf.php';
require_once 'functions.php';
 define('HOST','localhost');
  define('USER','root');
  define('PASS','');
  define('DB','fir');
  $con = mysqli_connect(HOST,USER,PASS,DB);
  
  $FirNo=$_POST['firNo'];
  $feedback = $_POST['feedback'];
  

  $result = mysql_query("UPDATE fir SET feedback = '$feedback' WHERE co_id = '$FirNo'");

  
 if(mysqli_query($con,$result)){
    echo 'success';
  }
  else{
    echo 'failure';
  }
  mysqli_close($con);
  
?>