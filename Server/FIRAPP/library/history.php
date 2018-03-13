<?php
 
    mysql_connect("localhost","root",""); // host, username, password...
    mysql_select_db("fir"); // db name...
      
    $q=mysql_query("SELECT cr_type,pname,feedback FROM fir ");
    while($row=mysql_fetch_assoc($q))
            $json_output[]=$row;
      
    print(json_encode($json_output));
      
    mysql_close();
     
?>