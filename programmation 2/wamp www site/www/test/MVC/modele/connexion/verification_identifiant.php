<?php
	function verification_identifiant($pseudo,$pass)
	{
		global $bdd;
		$req=$bdd->prepare("SELECT id FROM membre WHERE pseudo=:pseudo AND pass=:pass");
		$req->execute(array("pseudo"=>$pseudo,
							"pass"=>$pass
							));
		$resultat_id=$req->fetch();
	return $resultat_id;
	}
?>