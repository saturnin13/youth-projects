<?php
session_start();
$_SESSION=array();//suppression des variables de la session
session_destroy();
setcookie('pseudo', '');
setcookie('pass', '');
/*on redirige la page*/
header("location:?page=accueil");
?>

