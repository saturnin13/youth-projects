	<?php
		/*ouverture bdd*/
		include("modele/connexion_sql.php");//ouverture bdd
		include("modele/connexion/verification_identifiant.php");//ouverture des vérifications identifiant
		include("modele/connexion/demarrage_session.php");//ouverture demarrage session
		include("modele/connexion/creation_cookie.php");//ouverture cration cookie
		session_start();
			/*vérification si les cookies existent*/
			if(isset($_COOKIE["pseudo"]) AND isset($_COOKIE["pass"]))
			{
				$resultat_id=verification_identifiant($_COOKIE["pseudo"],$_COOKIE["pass"]);//vérifie les identifiants et renvoi id
				demarrage_session($resultat_id["id"],$_COOKIE["pseudo"]);
			}
			
			/*vérification si les champs existent*/
			elseif(isset($_POST["pseudo"]) AND isset($_POST["pass"]))
			{
				$pass_hach=sha1($_POST["pass"]);//hachage du mot de passe
				$session_ouverte=verification_identifiant($_POST["pseudo"],$pass_hach);
				if($session_ouverte)
				{
					demarrage_session($session_ouverte["id"],$_POST["pseudo"]);
					if(isset($_POST["connexion_automatique"]) AND $_POST["connexion_automatique"]==true)//si on met une connexion automatique
					{
						creation_cookie_identifiant($_SESSION["pseudo"],$pass_hach);
					}
				}
			}
		/*on met la vue de connexion*/
		include("vue/compte/connexion.php");
		?>
