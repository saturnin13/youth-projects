<?php
	/*creation compte*/
	function creation_compte($pseudo_utilisateur,$pass_utilisateur,$email_utilisateur)
	{
				global $bdd;
				$pass_hach=sha1($pass_utilisateur);
				$rep=$bdd->prepare("INSERT INTO membre(id,pseudo, pass, email, date_inscription) VALUES('',:pseudo,:pass,:email,NOW())");
				$rep->execute(array("pseudo"=>$pseudo_utilisateur,
							"pass"=>$pass_hach,
							"email"=>$email_utilisateur
						    ));
	}
?>