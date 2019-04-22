<?php
header ("content-type: image/jpeg");
$s=imagecreatefrompng("facebook.png");
$d=imagecreatefromjpeg("tn_s.jpg");

$sx=imagesx($s);
$sy=imagesy($s);
$dx=imagesx($d);
$dy=imagesy($d);

$ddx=$dx-$sx;
$ddy=$dy-$sy;

imagecopymerge($d,$s,$ddx,$ddy,0,0,$sx,$sy,70);

imagejpeg($d);
?>
<?php
header ("content-type: image/jpeg");
$s=imagecreatefromjpeg("tn_s.jpg");
$d=imagecreatetruecolor(200,300);

$sx=imagesx($s);
$sy=imagesy($s);
$dx=imagesx($d);
$dy=imagesy($d);

imagecopyresampled($d,$s,0,0,0,0,$dx,$dy,$sx,$sy);

imagejpeg($d,"");
?>
