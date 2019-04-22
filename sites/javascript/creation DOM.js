var div1=document.createElement('div');
div1.id="TP1";

 var texte=[
    document.createTextNode('Le '),
    document.createTextNode('World Wide Web Consortium'),
    document.createTextNode(', abrégé par le sigle '),
    document.createTextNode('W3C'),
    document.createTextNode(', est un '),
    document.createTextNode('organisme de standardisation'),
    document.createTextNode(' à but non-lucratif chargé de promouvoir la compatibilité des technologies du '),
    document.createTextNode('World Wide Web'),
    document.createTextNode('.')
	];
var strong1=document.createElement("strong")
	strong2=document.createElement("strong")

	strong1.appendChild(texte[1]);
	strong2.appendChild(texte[3]);
	
var lien1=document.createElement("a")
	lien2=document.createElement("a")

	lien1.href="http://fr.wikipedia.org/wiki/Organisme_de_normalisation";
	lien1.title="Organisme de normalisation";
	
	lien2.href="http://fr.wikipedia.org/wiki/World_Wide_Web";
	lien2.title="World Wide Web";
	
	lien1.appendChild(texte[5]);
	lien2.appendChild(texte[7]);

div1.appendChild(texte[0]);	
div1.appendChild(strong1);	
div1.appendChild(texte[2]);	
div1.appendChild(strong2);	
div1.appendChild(texte[4]);	
div1.appendChild(lien1);	
div1.appendChild(texte[6]);	
div1.appendChild(lien2);	
div1.appendChild(texte[8]);	

document.body.appendChild(div1);
alert(div1);







