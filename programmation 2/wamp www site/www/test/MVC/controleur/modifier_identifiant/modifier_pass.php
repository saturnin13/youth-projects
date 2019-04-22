<?php
include("modele/connexion_sql.php");//ouverture bdd
include("modele/profil/modifier_identifiant.php");//modele modifier pass
include("modele/connexion/verification_identifiant.php");//ouverture des vérifications identifiant
//on doit ouvrir une session si pas deja fait pour que ça fonctionne

if(isset($_POST["ancien"]) AND isset($_POST["nouveau"]))
{
	$pass_hach=sha1($_POST["ancien"]);
	if(verification_identifiant($_SESSION["pseudo"],$pass_hach))
	{
		modifier_pass($_SESSION["id"],$_POST["nouveau"]);	
		$mot_de_passe=TRUE;
	}
	else
	{
		$mot_de_passe=FALSE;
	}
}

include("vue/profil/modifier_pass.php");//vue
?>