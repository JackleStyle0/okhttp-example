# PHP file
```php
<?php
  include 'conn.php';
  
  $inputJSON = file_get_contents('php://input');
  $input = json_decode( $inputJSON, TRUE );

  if($input["todo"] == "SELECT"){
    $sql_select_user = "SELECT * FROM profile";
    $result = mysqli_query($conn, $sql_select_user);
    if($row = mysqli_fetch_array($result)) {
      $out[] = $row;
    }

    print(json_encode($out));
    mysqli_close($conn);
  }

//for insert user
  if($input["todo"] == "INSERT"){
    $user = $input["user"];
    $pass = $input["pass"];
    $fname = $input["fname"];
    $lname = $input["lname"];

    $sql_insert_user = "INSERT INTO profile(username, password, firstname, lastname) VALUES('$user', '$pass', '$fname', '$lname')";
    $result = $conn->query($sql_insert_user);

    $response = array();
    if($result){
      $response['success'] = "true";
    }else{
      $response['success'] = "false";
    }

    echo json_encode($response);
    mysqli_close($conn);
  }
?>

```
