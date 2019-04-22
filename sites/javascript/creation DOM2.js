var div1=document.createElement('div');
div1.id="TP2";

var texte=[
	document.createTextNode("langage basés sur ECMAScript :"),
	document.createTextNode("javascript"),
	document.createTextNode("Jscript"),
	document.createTextNode("actionscript"),
	document.createTextNode("EX4")
	];
	
var p=document.createElement("p")
	p.appendChild(texte[0]);
	
var ul=document.createElement("ul"); 
	i=1;
	do
	{	
		li=document.createElement("li");
		li.appendChild(texte[i]);
		ul.appendChild(li);
		i+=1;
	}while(texte[i])
	
div1.appendChild(p);
div1.appendChild(ul);
document.body.appendChild(div1);

