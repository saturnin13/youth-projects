var element=document.getElementById("in"),b=0,a="a";

function bouger()
{
return (function manger()
{
	b=b+1;
	return setTimeout(bouger,200);
})()
}
element.onkeydown=function(e)
{
	var a=bouger();
	clearTimeout(a);
	alert(a);
}

element.onkeyup=function(e)
{
	
}

