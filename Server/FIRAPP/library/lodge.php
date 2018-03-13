<?php

/*
  mysql_connect("127.0.0.1","root","");
  mysql_select_db("fir");
  $sql=mysql_query("select * from complainer where cname='aakash'");
  while($row=mysql_fetch_assoc($sql)) $output[]=$row;
  print(json_encode($output));
  mysql_close();*/
  session_start();
  

require_once 'configf.php';
require_once 'functions.php';

  define('HOST','localhost');
  define('USER','root');
  define('PASS','');
  define('DB','fir');
  $con = mysqli_connect(HOST,USER,PASS,DB);
  $complainer_name = $_POST['name'];
  $cr_type = $_POST['crime'];
  $add   = $_POST['add'];
  $discription = $_POST['description'];
  $cphone   = $_POST['mo'];
  $FirNo=$_POST['firid'];
  $pstn=$_POST['Stn'];
  	$complainer_id = (int)$_SESSION['user_id'];
 /* $sql = "SELECT cname
					FROM complainer
					WHERE cname = '$userName'";
			$result = dbQuery($sql);
			if (dbNumRows($result) == 1) {
			echo  'failure';	
			} else{*/
  $sql   ="INSERT INTO fir( cid, cname, c_phone, co_id, cr_type, cr_desc, dt_occurrence, address, status, pid, pname, pstn,feedback,comment, create_date)
 VALUES ('$complainer_id','$complainer_name','$cphone','$FirNo','$cr_type','$discription','$dt_occur','$add','$status','','','$pstn','','',NOW())";
	
/* "INSERT INTO complainer (cname, cpass, address, email, c_mobile, date_time)
						  VALUES ('$userName', '$password', '$txtAdd','$txtEmail','$txtMob',NOW())";*/
				
 // }
  if(mysqli_query($con,$sql)){
    echo 'success';
  }
  else{
    echo 'failure';
  }
  mysqli_close($con);
  
?>
