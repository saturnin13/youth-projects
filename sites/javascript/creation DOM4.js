var div1=document.createElement("div");
div1.id="TP4";

	var form=document.createElement("form");
	form.enctype="multipart/form-data";
	form.method="post";
	form.action="upload.php";
	
	var fieldset=document.createElement("fieldset");
	

		var legend=document.createElement("legend"),
			tlegend=document.createTextNode("Uploader une image");
			legend.appendChild(tlegend);
	
		var div2=document.createElement("div");	
			div2.setAttribute('style', 'text-align: center');
	
			var label=document.createElement("label");
				tlabel=document.createTextNode("Image à uploader :");
				label.appendChild(tlabel);
				label.htmlfor="inputUpload";
				
			var input=document.createElement("input");
				input.type="file";
				input.name="inputUpload";
				input.id="inputUpload";
				
			var br=document.createElement("br");
			var br2=br.cloneNode(false);
			
			var input2=document.createElement("input");
				input2.type="submit";
				input2.value="envoyer";
				
		div2.appendChild(label);
		div2.appendChild(input);
		div2.appendChild(br);
		div2.appendChild(br2);
		div2.appendChild(input2);

fieldset.appendChild(div2);
fieldset.appendChild(legend);
form.appendChild(fieldset);
div1.appendChild(form);
document.body.appendChild(div1);