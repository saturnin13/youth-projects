var drag=document.getElementsByTagName("div");
var storage={}
for(var i=0;i<drag.length;i++)
{
	if(drag[i].className=="drag")
	{
		drag[i].addEventListener("mousedown",function(e)
		{
			storage.target=e.target;
			storage.x=e.clientX-storage.target.offsetLeft;
			storage.y=e.clientY-storage.target.offsetTop;
		},false);
		
		drag[i].addEventListener("mouseup",function(e)
		{
			storage={};
		},false)
	}
};
	document.addEventListener("mousemove",function(e)
		{
			if(storage.target){
			storage.target.style.left=e.clientX-storage.x+"px";
			storage.target.style.top=e.clientY-storage.y+"px";}
		},false)
	
 
