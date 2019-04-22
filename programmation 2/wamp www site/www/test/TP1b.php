<!DOCTYPE html>
<html >
	<head>
		<meta charset="utf8" />
		<title>mot de passe</title>
	</head>
	<body>
		<?php
			if($_POST["password"]=="kangourou")
				echo "tu peux aller sur le site de la nasa tu est un grand hacker";
			else
				echo "mot de passe incorrect";
		?>
	</body>
</html>