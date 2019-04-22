<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>profil membre</title>
		<link rel="stylesheet" href="vue/profil/profil_membre.css" />
	</head>
	<body>
		<div id="tout">
		<?php 
		if(file_exists("vue/image_membre/".$_SESSION["id"].".jpg"))//si la photo existe alors afficher
		{?>
		<img src="vue/image_membre/<?php echo $_SESSION["id"];?>.jpg" alt="photo de profil" />
		<?php }?>
		<a href="?page=creation_image_membre">choisir une photo de profil</a><br /><br/>
		<p>pseudo: 
			<?php
			if(isset($_POST["nouveaup"]))
			{
				echo $_POST["nouveaup"];
			} 
			else
			{
				echo $_SESSION["pseudo"];
			}
			?>
			<a href="?page=modifier_pseudo">modifier pseudo</a>
			<br />
			date d'inscription: <?php echo $date_inscription["date_inscription"];?>
			<br />
			email: <?php echo $email["email"]?>
		</p>
		<br/>
		<a href="?page=modifier_pass">modifier mot de passe</a><br />
		<a href="?page=connexion">connexion</a><br />
		<a href="?page=accueil">accueil</a>
		</div>
	</body>
</html>