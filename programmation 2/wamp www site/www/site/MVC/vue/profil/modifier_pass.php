<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>creation image</title>
	</head>
	<body>
		<form method="post">
			<label>mot de passe actuel</label>
			<input type="password" name="ancien" />
			<?php if(isset($mot_de_passe) AND $mot_de_passe==FALSE){echo "mot de passe incorrect";}?><!--si mot de passe faux-->
			<br />
			<label>nouveau mot de passe</label>
			<input type="password" name="nouveau" />
			<br />
			<input type="submit" value="changer de mot de passe" />
			<br/>
			<?php if(isset($mot_de_passe) AND $mot_de_passe==TRUE){echo "le mot de passe à bien était modifié";}?><!--si mot de passe modifié-->
			<br/>
			<a href="?page=profil">terminé</a>
		</form>	
	</body>
</html>