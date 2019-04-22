<?php
	function demarrage_session($session_id,$session_pseudo)
	{
		if($session_id)
		{
		$_SESSION["id"]=$session_id;
		$_SESSION["pseudo"]=$session_pseudo;
		$session_ouverte=TRUE;
		}
		else
		{
		$_SESSION["id"]="??";
		$_SESSION["pseudo"]="problème cookie";
		$session_ouverte=FALSE;
		}
		return $session_ouverte;
	}
?>
	