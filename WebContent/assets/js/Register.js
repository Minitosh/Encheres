/**
 * 
 */

function saveAccount(){
	var mdp = document.getElementById(mdp);
	var mdpConfirm = document.getElementById(mdpConfirm)
	 
	if(mdp.equals(mdpConfirm)){
		alert("mdp identique");
	}else {
		alert("mdp error");
	}
}