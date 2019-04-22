<?php
function creation_image($image,$id,$ext_demande,$largeur,$hauteur)
{
	include("modele/image/redimensionner_image_jpg.php");//redimension
	include("modele/image/verification_extension.php");//verification ext
	if(verification_extension($image["name"],$ext_demande))
	{
		move_uploaded_file($image["tmp_name"],"vue/image_membre/".$id.".".$ext_demande);
		redimensionner_image_jpg($id,$ext_demande,$largeur,$hauteur);
		$fichier_envoyee=TRUE;//pour vue
	}
	else
	{
		$fichier_envoyee=FALSE;//pour vue
	}
	return $fichier_envoyee;
}
?>