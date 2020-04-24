/**
 * 
 */
function cancel(){
	 document.location.href='/Encheres/Accueil';
}

function saveAccount(){
	var mdp = document.getElementById("mdp");
	var mdpConfirm = document.getElementById("mdpConfirm");
	var pseudo = document.getElementById("pseudo");
	var nom = document.getElementById("nom");
	var prenom = document.getElementById("prenom");
	var mail = document.getElementById("mail");
	var rue = document.getElementById("rue");
	var cp = document.getElementById("cp");
	var ville = document.getElementById("ville");
	var tel= document.getElementById("tel");
	var nbError = 0;
	
	nbError=checkValidity(pseudo,nbError);
	nbError=checkValidity(nom,nbError);
	nbError=checkValidity(prenom,nbError);
	nbError=checkValidity(mail,nbError);
	nbError=checkValidity(rue,nbError);
	nbError=checkValidity(cp,nbError);
	nbError=checkValidity(ville,nbError);
	nbError=checkValidity(mdp,nbError);
	nbError=checkValidity(tel,nbError);
	
	if(nbError==0){
		if(mdp.value == mdpConfirm.value){
			mdpConfirm.removeAttribute('style');
			mdp.removeAttribute('style');
			if(confirm('Voulez-vous creer un compte ? ')){
				accountInfoRegister.submit();
			}
		}else {
			mdpConfirm.setAttribute('style','border-color: red;');
			mdp.setAttribute('style','border-color: red;');
			alert("mdp error");
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


