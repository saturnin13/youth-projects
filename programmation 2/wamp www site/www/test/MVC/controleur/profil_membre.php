<?php
session_start();
include("modele/connexion_sql.php");//ouverture bdd

$date_inscription=$bdd->query("SELECT date_inscription FROM membre WHERE id=\"".$_SESSION["id"]."\"")->fetch();
$email=$bdd->query("SELECT email FROM membre WHERE id=\"".$_SESSION["id"]."\"")->fetch();

include("vue/profil/profil_membre.php");//vue
?>


