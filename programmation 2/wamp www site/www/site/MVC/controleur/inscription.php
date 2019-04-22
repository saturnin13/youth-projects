	<?php
	include("modele/connexion_sql.php");//ouverture bdd
	include("modele/inscription/verification_inscription.php");//ouverture fonctions inscription
	include("modele/inscription/creation_du_compte.php");//ouverture création compte
	include("modele/image/creation_image.php");//creation image utilise modele verification ext et redimension
	//on vérifie si tout les champs sont plein et existent
	if(isset($_POST['pseudo']) AND strlen($_POST['pseudo'])>0 AND isset($_POST['pass']) AND strlen($_POST['pass'])>0 AND isset($_POST['passtest']) AND strlen($_POST['passtest'])>0 AND isset($_POST['email']) AND strlen($_POST['email'])>0) 
	{
		/*vérification pseudo, pass et email*/
		$pseudo=autorisation_pseudo($_POST["pseudo"]);
		$pass=autorisation_pass($_POST["pass"],$_POST["passtest"]);
		$email=autorisation_email($_POST["email"]);
		/*si toutes les variables sont TRUE*/
		if ($email==TRUE AND $pass==TRUE AND $pseudo==TRUE)
		{
			creation_compte($_POST["pseudo"],$_POST["pass"],$_POST["email"]);
			echo "votre inscription a bien était pris en compte";
			if(isset($_FILES["image"])and $_FILES["image"]["error"]==0)//si on met une photo de profil
			{
				$req=$bdd->query("SELECT id FROM membre WHERE pseudo=\"".$_POST["pseudo"]."\"");
				$table=$req->fetch();//recuperation id
				$fichier_envoyee=creation_image($_FILES["image"],$table["id"],"jpg",150,160);//return true ou false
			}
		}
	}
	else
	{
		$inscrit=FALSE;
	}
	/*on met la vue de inscription*/
	include("vue/compte/inscription.php");
	?>