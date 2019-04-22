<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>creation image</title>
	</head>
	<body>
		<form method="post">
			<label>nouveau pseudo</label>
			<input type="text" name="nouveaup" />
			<br />
			<input type="submit" value="changer de pseudo" />
		
			<?php
				if(isset($_POST["nouveau"]))
				{
			?>
					<br />le pseudo a bien était modifié
			<?php 
				}
			?>
			<br/>
			<a href="?page=profil">terminé</a>
		</form>
	</body>
</html>