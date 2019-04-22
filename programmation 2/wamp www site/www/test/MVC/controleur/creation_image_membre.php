<?php
include("modele/image/creation_image.php");//utilise modele verification ext et redimension
//ouvrir session si pas ouvert précedemment
	if(isset($_FILES["image"])and $_FILES["image"]["error"]==0)
	{
		$fichier_envoyee=creation_image($_FILES["image"],$_SESSION["id"],"jpg",300,320);//return true ou false
	}
	
include("vue/profil/creation_image_membre.php");//vue
?>