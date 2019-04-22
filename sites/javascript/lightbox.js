function afficherimg(lien)
{
	var img=new Image();
		overlay=document.getElementById("overlay");
	
	img.onload=function(){
		overlay.innerHTML="";
		overlay.appendChild(img);
	}
	
	overlay.style.display="block";
	img.src=lien.href;
	overlay.innerHTML="<p>chargement en cour</p>";
}

var link=document.getElementsByTagName('a');
	longeur=link.length
for(i=0;i<longeur;i++)
{
	link[i].onclick=function(){
		afficherimg(this);
		return false;
	}
}

document.getElementById('overlay').onclick=function(){this.style.display="none";};
alert(link);
