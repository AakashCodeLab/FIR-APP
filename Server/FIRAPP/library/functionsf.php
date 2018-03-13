<?php

/*
  mysql_connect("127.0.0.1","root","");
  mysql_select_db("fir");
  $sql=mysql_query("select * from complainer where cname='aakash'");
  while($row=mysql_fetch_assoc($sql))
  $output[]=$row;
  print(json_encode($output));
  mysql_close();*/

require_once 'configf.php';

  define('HOST','localhost');
  define('USER','root');
  define('PASS','');
  define('DB','fir');
  $con = mysqli_connect(HOST,USER,PASS,DB);
  $userName = $_POST['name'];
  $password = $_POST['pass'];
  $txtAdd   = $_POST['add'];
  $txtEmail = $_POST['mail'];
  $txtMob   = $_POST['mo'];
  $sql = "SELECT cname
					FROM complainer
					WHERE cname = '$userName'";
			$result = dbQuery($sql);
			if (dbNumRows($result) == 1) {
			echo  'failure';	
			} else{
  $sql   = "INSERT INTO complainer (cname, cpass, address, email, c_mobile, date_time)
						  VALUES ('$userName', '$password', '$txtAdd','$txtEmail','$txtMob',NOW())";
				
  }
  if(mysqli_query($con,$sql)){
    echo 'success';
  }
  else{
    echo 'failure';
  }
  mysqli_close($con);
  
?>
