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
?>
```
