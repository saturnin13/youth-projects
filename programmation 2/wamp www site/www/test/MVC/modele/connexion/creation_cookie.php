<?php
	function creation_cookie_identifiant($pseudo,$pass)
	{
	setcookie("pseudo",$pseudo,time()+60*60);
	setcookie("pass",$pass,time()+60*60);
	}
?>