var xhr=new XMLHttpRequest(),
text=document.getElementById("search"),
aide=document.getElementById("results"),
resultat=document.getElementsByTagName("div"),
select=0;

function mettreville(mot)//avoir les ville correspondante
{
	var result="";
	xhr.open("GET","auto_completion.php?valeur="+mot);
	xhr.send(null);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			tableau=xhr.responseText.split("|");
			afficherville(tableau);//lien vers la deuxieme fonction
		}	
		};
}
function afficherville(liste)//les afficher dans "results"
{
	if(liste[0]!="")
	{
		var longueurtableau=liste.length;
		for(var i=0;i<longueurtableau;i++)
		{
			var nouveau=document.createElement("div"),
			texte=document.createTextNode(liste[i]);
			nouveau.appendChild(texte);
			aide.appendChild(nouveau);
			nouveau.onmouseover=function()
			{
				this.style.background="rgb(47,57,81)";
			}
			nouveau.onmouseout=function()
			{
				this.style.background="rgb(224,224,224)";
			}
			nouveau.onclick=function()
			{
				text.value=this.innerHTML;
				aide.innerHTML="";
			}
		}
	}
}

text.onkeydown=function(e) //a chaque pressage de touche
{	
	if(e.keyCode==38 || e.keyCode==40 || e.keyCode==13)
	{
		resultat[select].style.background="rgb(224,224,224)"
		if(e.keyCode==38)//haut
		{
			select-=1;
		}
		if(e.keyCode==40)//bas
		{
			select+=1;
		}
		if(select<1)//si select trop petit
		{
			select=1;
		}
		if(select>resultat.length-1)//si select trop grand
		{
			select=resultat.length-1;
		}
		resultat[select].style.background="rgb(47,57,81)";
		if(e.keyCode==13)//enter
		{
			text.value=resultat[select].innerHTML;
			aide.innerHTML="";
		}
	}
	else
	{
		select=0;
	}
}
text.onkeyup=function(e)
{
	if(e.keyCode==38 || e.keyCode==40 || e.keyCode==13)//deplacement haut bas et enter
	{
	}
	else
	{
		if(text.value=="")
		{
			aide.innerHTML="";
		}		
		else
		{
			aide.innerHTML="";
			mettreville(text.value);//bloque le code qui suit ??
		}
	}
}//recommencer à chaque relevé de touche 





