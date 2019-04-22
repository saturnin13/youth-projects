<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>formulaire php</title>
	</head>
	<body>
		<!--condition d'entrer sur le site prénom et lettre de motivation-->
		<?php
			//3 donné a changer pour changer le nombre de caractere de la lettre de motivation
			if(isset($_POST["prenom"]) and strlen($_POST["prenom"])>0 and strlen($_POST["texte"])>150)
				{	echo "bienvenue sur <a href=\"site/vrai site.php\">mon site</a> ".$_POST["prenom"];}
			elseif(strlen($_POST["texte"])<150 and strlen($_POST["prenom"])>0)
				{	echo "ta lettre de motivation est un peu a chier je suis sur que tu peux faire mieux";}	
			elseif(strlen($_POST["prenom"])==0 and strlen($_POST["texte"])>150)
				{	echo "marque ton prénom connard";}	
			else
				{	echo "donc tu t'apelle rien et en plus tu fais des lettre de motivation pourri et tu crois que tu vas rentrer sur mon site heu...LOL";}
		?>
		<br />
		<!--condition d'acceptation du fichier-->
		<?php
			//dans l'ordre des if: rempli ou erreur, taille et extension plus message d'erreur a chaque étage
			if(isset($_FILES["fichier"])and $_FILES["fichier"]["error"]==0)
			{	if($_FILES["fichier"]["size"]<=27000)
				{
					$info=pathinfo($_FILES["fichier"]["name"]);
					$ext=$info["extension"];
					$ext_autorisé=array("xlsx");
					if(in_array($ext,$ext_autorisé))
					{
						move_uploaded_file($_FILES["fichier"]["tmp_name"],"upload/".basename($_FILES["fichier"]["name"]));
						echo "<br />le fichier a bien été envoyer";
					}
					else
					{
						echo "<br />si vous  voyez ce texte c'est que l'extension est mauvaise";
					}
				}
				else
				{
					echo "<br />si vous  voyez ce texte c'est que le fichier est trop lourd";
				}
			}
			else
			{
				echo "<br />si vous  voyez ce texte c'est que le fichier est vide ou qu'il n'a pas était envoyé correctement vous pouvez le ré-envoyer ci-dessous";
			}
			?>
		<p>si tu veux changer ton prénom, ta lettre de motivation ou ton fichier <a href="formulaire.php">clique ici</a></p>
	</body>
</html>
<!--variables utilisé:	-$info
						-$ext
						-$ext_autorisé
-->