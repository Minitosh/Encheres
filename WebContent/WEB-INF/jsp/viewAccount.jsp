<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.lang.Integer"%>
<%@page import="encheres.bo.Utilisateur"%>
<%@page import="encheres.bll.UtilisateurManager"%>
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
   <%
   UtilisateurManager utilisateurManager = new UtilisateurManager();
   Utilisateur vendeur = utilisateurManager.selectionnerUtilisateurParNo(Integer.parseInt(request.getParameter("idVendeur")));
   
   %> 
    	<div class="container-fluid content">
    		<div class="row content">
    			<div class="col-md-6 col-sm offset-md-3">
			      <script src="./assets/js/AccountModify.js"></script>
	<h1 style="text-align:center;"> Mon profil</h1>
	<form id="accountInfoRegister" name="accountInfoRegister" action="ServletAccountUpdate" method="POST">
	
<div class="card bg-light">
<article class="card-body mx-auto" style="max-width: 400px;">
	<h4 class="card-title mt-3 text-center">profil de <%=vendeur.getPseudo() %></h4>
	
	<div class="form-group p-group">
		<div class="p-group-prepend">
		    <span class="p-group-text"> <i class="fa fa-user"></i> </span>
		 </div>
        <p id="pseudo" class="form-control"><%=vendeur.getPseudo() %></p>
    </div>
	
	<div class="form-group p-group">
		<div class="p-group-prepend">
		    <span class="p-group-text"> <i class="fa fa-user"></i> </span>
		 </div>
        <p id="nom" class="form-control"> <%=vendeur.getNom() %> </p>
    </div>
    
    	<div class="form-group p-group">
		<div class="p-group-prepend">
		    <span class="p-group-text"> <i class="fa fa-user"></i> </span>
		 </div>
        <p id="prenom" class="form-control"><%=vendeur.getPrenom() %></p>
    </div>
    
    
    <div class="form-group p-group">
    	<div class="p-group-prepend">
		    <span class="p-group-text"> <i class="fa fa-envelope"></i> </span>
		 </div>
        <p id="mail" class="form-control"><%=vendeur.getEmail() %></p>
    </div>
    
    
    <div class="form-group p-group">
    	<div class="p-group-prepend">
		    <span class="p-group-text"> <i class="fa fa-phone"></i> </span>
		</div>
    	<p id="tel" class="form-control"><%=vendeur.getTelephone() %></p>
    </div>
    
  
    
    	<div class="form-group p-group">
		<div class="p-group-prepend">
		    <span class="p-group-text"> <i class="fa fa-user"></i> </span>
		 </div>
        <p  id="cp" class="form-control"><%=vendeur.getCodePostal() %></p>
    </div>
    
    	<div class="form-group p-group">
		<div class="p-group-prepend">
		    <span class="p-group-text"> <i class="fa fa-user"></i> </span>
		 </div>
        <p  id="ville" class="form-control"><%=vendeur.getVille() %></p>
    </div>
    
	                                                           
</article>
</div>
  	


			    </div>
    		</div>
    	</div>
    </body>
</html>
