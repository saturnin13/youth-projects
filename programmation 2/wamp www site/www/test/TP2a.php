<!DOCTYPE html>
<html>
    <head>
        <title>minichat</title>
        <meta charset="utf-8" />
    </head>
	<body>
		<form action="TP2b.php" method="post"><!--création du formulaire-->
			<label>pseudo</label>
			<input type="text" placeholder="ton pseudo ici" name="pseudo" />
			<br />
			<label>message</label>
			<input type="text" placeholder="ton message ici" name="message" />
			<br />
			<input type="submit" value="tape moi dessus" />
			<input type="reset" value="essaye encore" />
			<br />
		</form>
		<?php
		try//ouverture de la BDD
		{
			$bdd=new PDO("mysql:host=localhost;dbname=saturnin","root","");
		}
		catch(exception $a)
		{
			die('erreur : '.$a->getmessage());
		}
		$lecture=$bdd->query("SELECT * FROM minichat ORDER BY ID DESC LIMIT 0,10");//lecture de la table minichat et limitation à 10
		while($entre=$lecture->fetch())
		{
		?>
			<p><strong><?php echo $entre["pseudo"]?></strong>: <?php echo $entre["message"]?></p>
		<?php
		}
		$lecture->closecursor()
		?>
		<p><a href="TP2a.php">rafraichir la page</a>
	</body>
</html>