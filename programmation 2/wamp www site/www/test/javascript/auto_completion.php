<?php

	$ville=unserialize(file_get_contents("auto_completion.txt"));
	sort($ville);
	$nombre_ville=count($ville);
	$recherche=$_GET["valeur"];//à prendre du javascript (valeur $_GET)
	$ok=array();
	for($i=0;$i<$nombre_ville;$i++)
	{
		if(preg_match("#".$recherche."#i",$ville[$i]) && count($ok)<10)
		{
			array_push($ok,$ville[$i]." ");
		}
	}
	echo implode("|",$ok);//renvoyer vers javascript (ville à montrer)
?>