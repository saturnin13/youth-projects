<?php
	if(!isset($_GET["page"]) OR $_GET["page"]=="accueil")
	{
		include("vue/accueil/accueil.php");;//mettre page d'accueil
	}
	elseif($_GET["page"]=="deconnexion")
	{
		include("controleur/deconnexion.php");//page deco
	}
	elseif($_GET["page"]=="connexion")
	{
		include("controleur/connexion.php");//page connec
	}	
	elseif($_GET["page"]=="inscription")
	{
		include("controleur/inscription.php");//page inscription
	}
	elseif($_GET["page"]=="profil")
	{
		include("controleur/profil_membre.php");//page profil
	}
	elseif($_GET["page"]=="creation_image_membre")
	{
		include("controleur/profil_membre.php");//page profil
		include("controleur/creation_image_membre.php");//page creation image 
	}	
	elseif($_GET["page"]=="modifier_pseudo")
	{
		include("controleur/profil_membre.php");//page profil
		include("controleur/modifier_identifiant/modifier_pseudo.php");//page creation pseudo 
	}
	elseif($_GET["page"]=="modifier_pass")
	{
		include("controleur/profil_membre.php");//page profil
		include("controleur/modifier_identifiant/modifier_pass.php");//page creation pass 
	}	
?>