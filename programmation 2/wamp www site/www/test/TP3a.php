<!DOCTYPE html>
<html>
	<head>
		<title>blog</title>
		<meta charset="utf-8"/>
	</head>
	<body>
	<h1>mon super blog</h1>
	<br />
	<p>Dernier billet du blog:</p>
	<?php
		try//connexion base
		{
			$bdd=new PDO("mysql:host=localhost;dbname=saturnin","root","");
			$bdd->exec("SET NAMES utf8");//utf8 pour éviter les bug de é
		}
		catch(exception $e)
		{
			die("erreur :".$e->getmessage());
		}
		$reponse=$bdd->query("SELECT * FROM tp3a ORDER BY date DESC LIMIT 0,5");//trié par ordre
		while($table=$reponse->fetch())
		{
	?>
		<h2><?php echo $table["titre"];?></h2>
		<p><?php echo $table["contenu"];?><br />
		<a href="TP3b.php?billet=<?php echo $table["id"];?>">commentaires</a></p><!--envoi vers le deuxième fichier-->
		</form>
	<?php
		}
		$reponse->closecursor();
	?>
	</body>
</html>