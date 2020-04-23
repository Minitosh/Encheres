<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
    	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="css/connexion.css">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
        <meta charset="utf-8" />
        <title>S'enregister</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    </head>
    <body>
    	<div class="container-fluid content">
    		<div class="row content">
    			<div class="col-md-6 col-sm offset-md-3">
			      <script src="./assets/js/Register.js"></script>
	<h1 style="text-align:center;"> Mon profil</h1>
	<form id="accountInfoRegister" name="accountInfoRegister" action="ServletAccountRegister" method="POST">
	
	
<div class="card bg-light">
<article class="card-body mx-auto" style="max-width: 400px;">
	<h4 class="card-title mt-3 text-center">Créer un compte</h4>
	
	<div class="form-group input-group">
		<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
		 </div>
        <input name="pseudo" id="pseudo" class="form-control" placeholder="Pseudo" type="text" required>
    </div>
	
	<div class="form-group input-group">
		<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
		 </div>
        <input name="nom" id="nom" class="form-control" placeholder="Nom" type="text" pattern="[a-zA-Z -]+" required>
    </div>
    
    	<div class="form-group input-group">
		<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
		 </div>
        <input name="prenom" id="prenom" class="form-control" placeholder="Prénom" type="text" pattern="[a-zA-Z -]+" required>
    </div>
    
    
    <div class="form-group input-group">
    	<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-envelope"></i> </span>
		 </div>
        <input name="mail" id="mail" class="form-control" placeholder="Adresse mail" type="email" required>
    </div>
    
    
    <div class="form-group input-group">
    	<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-phone"></i> </span>
		</div>
    	<input name="tel" id="tel" class="form-control" placeholder="Numéro de téléphone" type="tel"  pattern="[0-9]{10}">
    </div>
    
    
	<div class="form-group input-group">
		<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
		 </div>
        <input name="rue" id="rue" class="form-control" placeholder="Adresse" type="text" required>
    </div>
    
    	<div class="form-group input-group">
		<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
		 </div>
        <input name="cp" id="cp" class="form-control" placeholder="Code postal" type="text" required pattern="[0-9]{5}">
    </div>
    
    	<div class="form-group input-group">
		<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
		 </div>
        <input name="ville" id="ville" class="form-control" placeholder="Ville" type="text" required pattern="[a-zA-Z \-]+">
    </div>
    
	
    <div class="form-group input-group">
    	<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
		</div>
        <input class="form-control" placeholder="Mot de passe" type="password" id="mdp" name="mdp" required>
    </div>
    
    <div class="form-group input-group">
    	<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
		</div>
        <input class="form-control" placeholder="Confirmation mot de passe" type="password" id="mdpConfirm" name="mdpConfirm" required>
    </div>
                                       
    <div class="form-group">
        <button type="button" onClick="saveAccount();" class="btn btn-primary btn-block"> Créer le compte </button>
        <button type="button" onClick="cancel();" class="btn btn-danger btn-block"> Annuler </button>
    </div>     
    
    <p class="text-center">Vous avez un compte ? <a href="<%=request.getContextPath()%>/connexion">Se connecter</a></p>                                                                 
</article>
</div>
  	


			    </div>
    		</div>
    	</div>
    </body>
</html>
