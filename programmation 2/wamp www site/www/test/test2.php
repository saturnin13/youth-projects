<?php
setcookie("nom","saturnin1",time()+5);
?>
<?php
$age=15;
$majeur=($age >=18)?"9":"10";
?>
<?php
if($majeur)
{
 echo "coucou<br />";
}
else
{
 echo "aurevoir<br />";
}
?>
<?php
$a=1;
while($a<=5)
{
	echo "cette ligne est la numero".$a."<br />";
	$a++;
}
?>
<?php
for ($b=1;$b<= 5;$b++)
{
	echo "".$b."<br />";
}
?>
<?php
$c="tu est moche connard";
$d=strlen($c);
echo "$c<br />la phrase ci-dessus comporte $d caractere";
?>
<?php
$e=str_replace('t','m','dans ton cul');
echo"<br />$e";
?>
<?php
$f=str_shuffle($c);
echo"<br />$f";
?>
<?php
$jour = date('d');
$mois = date('m');
$annee = date('Y');
$heure = date('H');
$minute = date('i');
$heures = $heure+2;
// Maintenant on peut afficher ce qu'on a recueilli
echo '<br />Bonjour ! Nous sommes le ' . $jour . '/' . $mois . '/' . $annee . 'et il est ' . $heures. ' h ' . $minute;
?>
<?php
$nom = 'Sandra';
echo '<br />Bonjour, ' . $nom . ' !<br />';
 
$nom = 'Patrick';
echo 'Bonjour, ' . $nom . ' !<br />';
 
$nom = 'Claude';
echo 'Bonjour, ' . $nom . ' !<br />';
?>
<?php
function volumecone($rayon,$hauteur)
{
	$volume=$rayon*$rayon*3.14*$hauteur*(1/3);
	return $volume;
}
$volumes= volumecone(51,32);
	echo "le volume est $volumes";
?>
<?php
$g=array(
'nom'=>'sat',
'metier'=>'beaux gosse',
'nationalite'=>'russe'
);
echo '<br />'.$g['nom'].'';
foreach($g as $cle => $element)
{
	echo '<br />';
    echo $cle.' vaut '.$element.'<br />';
	echo $g[$cle];
}
?>
<br />
<?php
print_r($g);

?>
<br />
<?php
// On crée notre array $prenoms
$prenoms = array ('Francois', 'Michel', 'Nicole', 'Veronique', 'Benoit');
 
// Puis on fait une boucle pour tout afficher :
for ($numero = 0; $numero < 5; $numero++)
{
    echo '<br />'.$prenoms[$numero].'<br />'; // affichera $prenoms[0], $prenoms[1] etc.
}
?>
<?php
$prenoms = array ('Francois', 'Michel', 'Nicole', 'Veronique', 'Benoit');
 
foreach($prenoms as $i)
{
    echo $i.'<br />'; // affichera $prenoms[0], $prenoms[1] etc.
}
?>
<?php
$coordonnees = array (
    'prenom' => 'François',
    'nom' => 'Dupont',
    'adresse' => '3 Rue du Paradis',
    'ville' => 'Marseille');
 
if (array_key_exists('nom', $coordonnees))
{
    echo 'La cle "nom" se trouve dans les coordonnees !';
}
 
if (array_key_exists('pays', $coordonnees))
{
    echo 'La cle "pays" se trouve dans les coordonnees !';
}
 
?>
<a href="test.php?nom=sat&amp;age=16">test</a>
