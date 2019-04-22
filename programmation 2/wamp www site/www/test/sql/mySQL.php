<!DOCTYPE html>
<html>
    <head>
        <title>mySQL</title>
        <meta charset="utf-8" />
    </head>
	<body>
	<?php
		try
		{
			$bdd=new PDO("mysql:host=localhost;dbname=test","root","");
		}
		catch(exception $e)
		{
			die("erreur : ".$e->getmessage());
		}
			$reponse=$bdd->query("SELECT * FROM jeux_video");
			while($table=$reponse->fetch())
		{
	?>
			<p>
			<strong>jeux</strong>:<?php echo $table["nom"];?><br />
			le posseseur de se jeux est <?php echo $table["possesseur"];?>, et il le vend à <?php echo $table["prix"];?> euros<br />
			le jeu fonctionne sur <?php echo $table["console"];?> et on peut y jouer à <?php echo $table["nbre_joueurs_max"];?> au maximum<br />
			<?php echo $table["possesseur"];?> a laissé ce commentaire: "<?php echo $table["commentaires"];?>"
			</p>
	<?php
	}
	$reponse->closecursor();
		$reponse=$bdd->query("SELECT UPPER(nom) AS nom FROM jeux_video WHERE possesseur='Patrick' AND prix<20 ORDER BY prix DESC LIMIT 1,2")or die(print_r($bdd->errorInfo()));
			while ($a=$reponse->fetch())
			{
				echo $a["nom"]."<br />";
			}
		$bdd->exec("INSERT INTO jeux_video(ID, nom, possesseur, console, prix, nbre_joueurs_max, commentaires) VALUES('warhammer', 'patrick','NES',4,1,'amazing!!')");
		$bdd->exec("UPDATE jeux_video SET prix=65 WHERE ID=55");
		$bdd->exec("DELETE FROM jeux_video WHERE nom='warhammer'");
		
		$reponse->closecursor();
		$reponse=$bdd->query("SELECT AVG(prix) AS prix FROM jeux_video")or die(print_r($bdd->errorInfo()));
			while ($a=$reponse->fetch())
			{
				echo $a["prix"]."<br />";
			}
		$reponse->closecursor();
		$reponse=$bdd->query("SELECT COUNT(DISTINCT possesseur) AS nombre FROM jeux_video");
		$donne=$reponse->fetch();
		echo $donne["nombre"];
		$reponse->closecursor();
		$reponse=$bdd->query("SELECT AVG(prix) AS prix_moyen, console FROM jeux_video WHERE possesseur='Patrick' GROUP BY console HAVING prix_moyen <= 10");
		while ($a=$reponse->fetch())
			{
				echo $a["prix_moyen"],$a["console"]."<br />";
			}
	?>
	
	</body>
</html>