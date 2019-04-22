<?php
	function redimensionner_image_jpg($id,$ext,$largeur,$hauteur)
	{
	$s=imagecreatefromjpeg("vue/image_membre/".$id.".".$ext);//d:destination
	$d=imagecreatetruecolor($largeur,$hauteur);//s:source

	$sx=imagesx($s);
	$sy=imagesy($s);
	$dx=imagesx($d);
	$dy=imagesy($d);

	imagecopyresampled($d,$s,0,0,0,0,$dx,$dy,$sx,$sy);

	imagejpeg($d,"vue/image_membre/".$id.".".$ext);
	}
?>

