var reference=1,
	left="935px";
function tirer()//deplacement tir
{	
	var nouveaux=document.createElement("div");
	document.body.appendChild(nouveaux)
	
	nouveaux.style.position="absolute";//creation tir
	nouveaux.style.backgroundColor="#222";
	nouveaux.style.width="10px";
	nouveaux.style.height="25px";
	nouveaux.style.top="768px";
	nouveaux.style.left=left;
	nouveaux.id="tir"+reference;
	
	deplacer("tir"+reference,40,"haut")
	
	reference++;
	setTimeout(function()
	{nouveaux.parentNode.removeChild(nouveaux)},4000);//suppresion tir
	
	setTimeout(tirer,400);
}

var element1=document.getElementById("move1"),
	element2=document.getElementById("move2"),
	element3=document.getElementById("move3"),
	a1=0,
	a2=0,
	a3=0,
	nombre=0;

document.onkeydown=function(e)
{
	if(nombre!=1)
	{
		nombre=1
		if (e.keyCode==37)//deplacement tank gauche
		{
			a1=deplacer("move1",60,"gauche");
			a2=deplacer("move2",60,"gauche");
			a3=deplacer("move3",60,"gauche");
		}
		else if (e.keyCode==39)//deplacement tank droite
		{
			a1=deplacer("move1",60,"droite");
			a2=deplacer("move2",60,"droite");
			a3=deplacer("move3",60,"droite");
		}
	}
	if (e.keyCode==37||e.keyCode==39)//deplacement des tir avec tank
	{
		left=document.getElementById("move3").style.left;
	}
};
document.onkeyup=function(e)
{
	if (e.keyCode==37||e.keyCode==39)//stoppage de l'interval
	{
		nombre=0;
		clearInterval(a1);
		clearInterval(a2);
		clearInterval(a3);
		left=document.getElementById("move3").style.left;//ajustement des tirs
	}
};

tirer()