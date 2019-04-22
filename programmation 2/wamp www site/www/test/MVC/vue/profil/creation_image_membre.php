<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>creation image</title>
	</head>
	<body>
		<form method="post" enctype="multipart/form-data">
			<label>selectionner votre photo jpg ici</label><br />
			<input type="file" name="image" />
			<br />
			<input type="submit" value="envoyer" />
			<br />
			<?php if(isset($fichier_envoyee) AND $fichier_envoyee){echo "la photo à bien était enregisté";}?><!--si fichier correct-->
			<?php if(isset($fichier_envoyee) AND !$fichier_envoyee){echo "mauvaise extension";}?><!--si fichier faux-->
			<br />
			<a href="?page=profil">terminé</a>
		</form>
	</body>
</html>