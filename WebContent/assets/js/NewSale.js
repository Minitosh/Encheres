/**
 * 
 */

function cancel(){
	 document.location.href='/Encheres/Acceuil';
}

function saveArticle(){
	var nom = document.getElementById("name");
	var desc = document.getElementById("description");
	var cat = document.getElementById("categorie");
	var prix = document.getElementById("prix");
	var debut = document.getElementById("dateDebut");
	var fin = document.getElementById("dateFin");
	var rue = document.getElementById("rue");
	var cp = document.getElementById("cp");
	var ville = document.getElementById("ville");

	var nbError = 0;
	
	nbError=checkValidity(nom,nbError);
	nbError=checkValidity(cat,nbError);
	nbError=checkValidity(prix,nbError);
	nbError=checkValidity(debut,nbError);
	nbError=checkValidity(fin,nbError);
	nbError=checkValidity(rue,nbError);
	nbError=checkValidity(cp,nbError);
	nbError=checkValidity(ville,nbError);
	
	if(nbError==0){
		if(cat.value != "-- Categories --"){
			cat.removeAttribute('style');
			if(confirm('Voulez-vous vendre setArticle ? ')){
				accountInfoRegister.submit();
			}
		}else {
			cat.setAttribute('style','border-color: red;');
			alert("catégorie invalide");
		}
	}else {
		alert(nbError+" saisie(s) incorrecte");
	}
							
}

function checkValidity(element, nbError){
	element.removeAttribute('style');
	if(!element.validity.valid){
		nbError +=1;
		element.setAttribute('style','border-color: red;');
	}
	return nbError;
}