function convertir(nombreconv)
{
	if(nombreconv==0)
	{
		return "zero";
	}
	else
	{
		unité=nombre%10;
		dizaine=(nombre%100-nombre%10)/10;
		centaine=(nombre-(nombre%100))/100;
	
		valeurdizaine=["","dix","vingt","trente","quarante","cinquante","soixante","soixante-dix","quatre-vingt","quatre-vingt-dix"];
		if(dizaine!=0){dizaine=valeurdizaine[dizaine];}
		else{dizaine="";}
		valeurdix=["","onze","douze","treize","quatorze","quinze","seize","dix-sept","dix-huit","dix-neuf"];
		if(dizaine=="dix" && unité!=0){dizaine=valeurdix[unité]; unité=0;}//convertir dizaine
		
		valeurunité=["","un","deux","trois","quatre","cinq","six","sept","huit","neuf"];
		unité=valeurunité[unité];//convertir unité
		
		if(centaine!=0 && centaine!=1){centaine=valeurunité[centaine]+"-cent";}
		else if(centaine==1){centaine="cent";}
		else{centaine="";}//convertir centaine
		
		if(centaine!=0 && dizaine!=0 && unité==0){ centaine+="-";}
		else if(centaine==0 && dizaine!=0 && unité!=0){ dizaine+="-";}
		else if(centaine!=0 && dizaine==0 && unité!=0){ centaine+="-";}
		else if(centaine!=0 && dizaine!=0 && unité!=0){ centaine+="-"; dizaine+="-";}
		
		return (centaine+dizaine+unité);
	}
}
var nombre,utilisateur;
while(utilisateur=prompt("qu'elle nombre souhaité vous convertir entre 0 et 999"))
{
nombre=parseInt(utilisateur,10);
if(isNaN(nombre)==false)
{
	nombre=convertir(nombre);
	alert(nombre);
}
else
{
	alert("ceci n'est pas un nombre");
}
}
