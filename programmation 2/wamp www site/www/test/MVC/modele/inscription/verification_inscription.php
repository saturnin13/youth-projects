<?php
		/*pseudo libre*/
		function autorisation_pseudo($pseudo_utilisateur)
		{
		global $bdd;
		$req=$bdd->prepare("SELECT pseudo FROM membre WHERE pseudo=:pseudo");
		$req->execute(array("pseudo"=>$pseudo_utilisateur));
		$table=$req->fetch();
			if($table["pseudo"]==$pseudo_utilisateur)
			{
				$pseudo=FALSE;
			}
			else
			{
				$pseudo=TRUE;
			}
		$req->closecursor();
		return $pseudo;
		}
		
		/*pass pareils*/
		function autorisation_pass($pass_utilisateur,$pass_utilisateur2)
		{
		if($pass_utilisateur==$pass_utilisateur2)
		{
			$pass=TRUE;
		}
		else
		{
			$pass=FALSE;
		}
			return $pass;
		}
		
		/*email correct*/
		function autorisation_email($email_utilisateur)
		{
		if(preg_match("#^[a-zA-Z0-9-._]+@[a-zA-Z0-9.-_]{2,}\.[a-z]{2,4}$#",$email_utilisateur))
		{
			$email=TRUE;
		}
		else
		{
			$email=FALSE;
		}
		return $email;
		}
		
?>