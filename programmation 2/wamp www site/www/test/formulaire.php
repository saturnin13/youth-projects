<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>formulaire php</title>
	</head>
	<body>
		<form method="post" action="cible.php" enctype="multipart/form-data">
			<label>prénom</label>
			<input type="text" name="prenom" />
			<br />
			<p>lettre de motivation<br />
			<textarea name="texte" rows="8" cols="45" placeholder="écris ici un petit texte de motivation pour avoir l'autorisation de rentrer sur mon site "></textarea></p>
			<p>prendre un fichier excel d'un maximum de 29Ko<br />
			<input type="file" name="fichier" /></p>
			<br /><br />
			<input type="submit" value="appuyer moi dessus" />
			<input type="reset" value="reset" />
			
		</form>
	</body>
</html>
<!--name utilisé:	-prenom
					-texte
					-fichier
-->
					