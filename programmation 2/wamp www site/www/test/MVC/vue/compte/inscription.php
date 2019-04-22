<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>inscription php</title>
		<link rel="stylesheet" href="vue/compte/inscription.css" />
	</head>
	<body>
		<?php if(isset($inscrit) AND $inscrit==FALSE){
		?><p>veuillez remplir tout les champs</p><?php }?>
		<form method="post" enctype="multipart/form-data"><!--formulaire-->
			<label>Pseudo</label>
			<input type="text" name="pseudo" value="<?php if(isset($_POST['pseudo'])){echo $_POST['pseudo'];}?>" /><!--valeur pour ne pas réécrire texte en cas d'erreur-->
			<?php if(isset($pseudo) AND $pseudo==FALSE){echo "Pseudo déjà utilisé";}?><!--explication en cas d'erreur-->
			<br />
			<label>Mot de passe</label>
			<input type="password" name="pass" />
			<?php if(isset($pass) AND $pass==FALSE){echo "Les deux mots de passe différent";}?><!--explication en cas d'erreur-->
			<br />
			<label>Retaper le mot de passe</label>
			<input type="password" name="passtest" />
			<br />
			<label>adresse email</label>
			<input type="text" name="email" value="<?php if(isset($_POST['email'])){echo $_POST['email'];}?>"/>
			<?php if(isset($email)){ if($email==FALSE){echo "Adresse email incorrect";}}?><!--explication en cas d'erreur-->
			<br />
			<?php if(isset($fichier_envoyee) AND !$fichier_envoyee){echo "mauvaise extension de fichier pour votre photo";?><br /><?php }?><!--si fichier pas enregistré-->
			<label>selectionner votre photo de profil au format jpg</label>
			<input type="file" name="image" id="photo"/>
			<br />
			<input type="submit" value="valider" />
		</form>
		<div id="lien">
		<a href="?page=connexion">connexion</a><br />
		<a href="?page=accueil">accueil</a>
		</div>
	</body>
</html>