<?php
	/*ouverture bdd*/
	try
	{
		$bdd=new PDO("mysql:host=localhost;dbname=site_mvc","root","");
	}
	catch(exception $e)
	{
		die("erreur : ".$e->getmessage());
	}
?>