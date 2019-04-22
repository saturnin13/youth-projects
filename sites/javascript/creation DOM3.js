var div1=document.createElement("div");
div1.id="TP3";

var p=document.createElement("p");
var textp=document.createTextNode("Langages basés sur ECMAScript :");
p.appendChild(textp);

var def=[
	{t:"JavaScript",
	 d:"JavaScript est un langage de programmation de scripts principalement utilisé dans les pages web interactives mais aussi coté serveur."},
	{t:"JScript",
	 d:"JScript est le nom générique de plusieurs implémentations d'ECMAScript 3 créées par Microsoft."},
	{t:"ActionScript",
	 d:"ActionScript est le langage de programmation utilisé au sein d'applications clientes (Adobe Flash, Adobe Flex) et serveur (Flash media server, JRun, Macromedia Generator)."},
	{t:"EX4",
	 d:"ECMAScript for XML (E4X) est une extension XML au langage ECMAScript."}
	];
	
var dl=document.createElement("dl");
	for(var i=0;def.length>i;i++)
	{
		dt=document.createElement("dt");
		dd=document.createElement("dd");
		
		dttexte=document.createTextNode(def[i].t);
		ddtexte=document.createTextNode(def[i].d);
		
		dt.appendChild(dttexte);
		dd.appendChild(ddtexte);
		
		dl.appendChild(dt);
		dl.appendChild(dd);
	}

div1.appendChild(p);
div1.appendChild(dl);
document.body.appendChild(div1);
