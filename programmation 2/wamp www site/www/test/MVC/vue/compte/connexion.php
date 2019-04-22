<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>connexion php</title>
	</head>
	<body>		
		<?php if(isset($_SESSION["id"]))
		{?><p>vous êtes connécté <?php echo $_SESSION['pseudo'];?></p><?php }//si la session est ouverte
		else {
			 if(isset($session_ouverte) AND $session_ouverte==FALSE)
			 {?><p>Le pseudo ou le mot de passe est incorrecte</p><?php ;}?>
			<!--formulaire-->
			<form method="post" >
				<label>Pseudo</label>
				<br />
				<input type="text" name="pseudo" />
				<br />
				<label>Mot de passe</label>
				<br />
				<input type="password" name="pass" />
				<br />
				<input type="checkbox" name="connexion_automatique" />
				<label>Connexion automatique</label>
				<br />
				<input type="submit" value="Se connecter" />
			</form>
		<?php }?>
	</body>
</html>