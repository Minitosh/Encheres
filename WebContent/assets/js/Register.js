/**
 * 
 */

function saveAccount(){
	var mdp = document.getElementById(mdp);
	var mdpConfirm = document.getElementById(mdpConfirm)
	 
	if(mdp == mdpConfirm){
		if(confirm('Voulez-vous creer un compte ? ')){
			accountInfoRegister.submit();
		}
	}else {
		alert("mdp error");
	}
}