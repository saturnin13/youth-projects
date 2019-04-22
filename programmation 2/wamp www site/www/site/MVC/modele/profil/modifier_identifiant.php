<?php
function modifier_pseudo($id,$nouveau_pseudo)
{
	global $bdd;
	$bdd->exec("UPDATE membre SET pseudo=\"".$nouveau_pseudo."\" WHERE id=\"".$id."\"");
}
function modifier_pass($id,$nouveau_pass)
{
	global $bdd;
	$pass_hach=sha1($nouveau_pass);
	$bdd->exec("UPDATE membre SET pass=\"".$pass_hach."\" WHERE id=\"".$id."\"");
}
?>