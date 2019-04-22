<?php
	try//ouverture de la BDD
	{
		$bdd=new PDO("mysql:host=localhost;dbname=saturnin","root","");
	}
	catch(exception $a)
	{
		die('erreur : '.$a->getmessage());
	}
	if(strlen($_POST["pseudo"])>0 and strlen($_POST["message"])>0)//vérification du pseudo et mot de passe 
	{
		$insert=$bdd->prepare("INSERT INTO minichat VALUES('',:pseudo,:message,curtime())");//écriture dans la table si pseudo et texte
		$insert->execute(array(
				"pseudo"=>$_POST["pseudo"],
				"message"=>$_POST["message"])) or die(print_r($insert->errorinfo()));
		$insert->closecursor();
		header("location:TP2a.php");
	}
	else
	{
		echo'<p>vous devez remplir le champs pseudo ET texte <a href="TP2a.php">revenir au minichat</a></p>';//message si pseudo ou texte manquant
	}
	
?>
	