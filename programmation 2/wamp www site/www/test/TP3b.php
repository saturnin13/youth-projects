<!DOCTYPE html>
<html>
	<head>
		<title>blog</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	</head>
	<body>
	<h1>mon super blog</h1>
	<p><a href="TP3a.php">revenir à la liste des billets</a></p>
		<?php
		try//ouverture
		{
			$bdd=new PDO("mysql:host=localhost;dbname=saturnin","root","");
			$bdd->exec("SET NAMES utf8");
		}
		catch(exception $e)
		{
			die("erreur :".$e->getmessage());
		}
		$reponse=$bdd->prepare("SELECT * FROM TP3a WHERE id=:a");//condition par rapport à l'autre
		$reponse->execute(array("a"=>$_GET["billet"]));
		$table=$reponse->fetch();
		?>
		<h2><?php echo $table["titre"];?></h2>
		<p><?php echo $table["contenu"];?></p>
		<?php
		$reponse->closecursor();
		?>
		<h2>commentaire</h2>
		<?php
		$reponse=$bdd->prepare("SELECT * FROM TP3b WHERE id_billet=:a ORDER BY date DESC");//commentaire par date
		$reponse->execute(array("a"=>$_GET["billet"]));
		WHILE($a=$reponse->fetch())
		{
		?>
		<p><h2><?php echo $a["auteur"];?> le <?php echo $a["date"];?></h2></p>
		<p><?php echo $a["commentaire"];?></p>
		<?php	
		}
		$reponse->closecursor();
		?>
	</body>
</html>