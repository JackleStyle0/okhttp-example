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
# PHP upload file

```php
<?php
  include 'conn.php';

   $todo = $_POST['todo'];

   if(isset($_POST['todo']) && $todo == "INSERT"){
     $name = $_POST['name'];
     $number = $_POST['number'];
     $price = $_POST['price'];

     $pathImage = "image/";
     //file
     $file = $_FILES['file']['name'];
     // get type file
    $type = end(explode('/', $_FILES['file']['type']));

     $pathImage = $pathImage. basename($file.".".$type);
     $sql_insert = "INSERT INTO productTable(proId, proName, proPrice, proImg) VALUES('$number', '$name', '$price', '$pathImage')";
     $result = $conn->query($sql_insert);

     $response = array();
     if($result){
      $response['success'] = 'true';
      move_uploaded_file($_FILES['file']['tmp_name'], $pathImage);
     }else{
       $response['success'] = 'false';
     }

     echo json_encode($response);
     mysqli_close($conn);
   }
 ?>
 ...

