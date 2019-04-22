<?php 
$age=7;
$sexe="fille"; 
?>
<?php
if($age>=8 or $sexe=="garçon")
{
	echo "degage connard";
	$autorisation=true;
}
elseif ($age<8 and $sexe=="fille")
{
	echo "vien mon cheri";
	$autorisation=false;
}
else
{
	echo"c'est trop dur de donner ton nom et ton age";
	$autorisation=false;
}
echo "<br />autorisation de visiter mon site:";
?>
<?php
if($autorisation)
{
	echo " oui, tu vas trop t'amuser tu vas voir";
}
else
{
	echo " non, ta mere la chauve";
}
?>
<br />
<?php
if($sexe=="fille" or $sexe=="garçon")
{
	echo "bonjour humain";
}
else
{
	echo "fuck you dog";
}
?>
<?php
if(isset($_get['nom']))
{
	echo '<br />bonjour'.$_GET['nom'];
}
else
{
	echo '<br />degage';
}

?>
<?php
	$compteur=fopen("compteur.txt","r+");
	
		$vue=fgets($compteur);
		$vue++;
		fseek($compteur,0);
		fputs($compteur,$vue);
		
	fclose($compteur);
	echo "cette page a ete vue ".$vue." fois";
?>





