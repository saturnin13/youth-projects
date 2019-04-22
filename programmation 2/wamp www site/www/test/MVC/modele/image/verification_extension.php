<?php
function verification_extension($nom_image,$ext_voulu)
{
	$info=pathinfo($nom_image);
	$ext=$info["extension"];
	$ext_autorise=array($ext_voulu);//format autorisé
	if(in_array($ext,$ext_autorise))
	{
	return TRUE;
	}
}
?>