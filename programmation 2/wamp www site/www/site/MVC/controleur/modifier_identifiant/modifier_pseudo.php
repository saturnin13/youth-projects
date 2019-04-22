<?php
include("modele/connexion_sql.php");//ouverture bdd
include("modele/profil/modifier_identifiant.php");//modele
//on doit ouvrir une session si pas deja fait pour que ça fonctionne

if(isset($_POST["nouveaup"]))
{
	modifier_pseudo($_SESSION["id"],$_POST["nouveaup"]);
	$_SESSION["pseudo"]=$_POST["nouveaup"];
}

include("vue/profil/modifier_pseudo.php");//vue
?>