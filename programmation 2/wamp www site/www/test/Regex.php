<p>
<?php
if (isset($_GET['telephone']))
{
    $_GET['telephone'] = ($_GET['telephone']); // On rend inoffensives les balises HTML que le visiteur a pu entrer
 
    if (preg_match("#^0[1-68]([-. ]?[0-9]{2}){4}$#", $_GET['telephone']))
    {
        echo 'Le ' . $_GET['telephone'] . ' est un numero <strong>valide</strong> !';
    }
    else
    {
        echo 'Le ' . $_GET['telephone'] . ' n\'est pas valide, recommencez !';
    }
}
?>
</p>
 
<form method="get">
<p>
    <label for="telephone">Votre telephone ?</label> <input id="telephone" name="telephone" /><br />
    <input type="submit" value="Verifier le numero" />
</p>
</form>
<?php
$texte = preg_replace('#http://[a-z0-9._/-]+#i', '<a href="$0">$0</a>', "");
?>
<?php
$texte = preg_replace('#\[color=(red|green|blue|yellow|purple|olive)\](.+)\[/color\]#isU', '<span style="color:$1">$2</span>', "");
?>