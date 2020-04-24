<%@page import="encheres.bll.UtilisateurManager"%>
<%@page import="encheres.bll.ArticleVenduManager"%>
<%@page import="encheres.bll.CategorieManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="encheres.bo.ArticleVendu"%>
<%@page import="encheres.bo.Categorie"%>
<%@page import="encheres.bo.Enchere"%>
<%@page import="encheres.bo.Utilisateur"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="css/accueil.css">
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
		<title>Accueil</title>
	</head>
	<body>
		<!-- <h1>${sessionScope.sessionUtilisateur.prenom} ? Bienvenue, ${sessionScope.sessionUtilisateur.prenom} :  </h1> -->
		<div class="container-fluid header">
			<div class="row header-text">
			    <div class="col">
			    	<h2><a class="noLink" href="<%=request.getContextPath()%>">ENI-Enchères</a></h2>
			    </div>
			    <div class="col-6">
			    </div>
			    <div class="col login-section">
			    <%
			    	if(request.getSession().getAttribute("sessionUtilisateur") == null){
			    		%>
			    			<a style="color:#33FFAC;" href="<%=request.getContextPath()%>/Connexion"><input class="btn btn-success" type="button" value="Se Connecter/S'inscrire"/></a>
			    		<%
			    	}else{
			    		%>
			    			<img src="img/user.svg" class="user-image"/>
			    			<a style="margin-left: 10px" href="<%=request.getContextPath()%>/Profil"><p style="margin-left: 10px" >Bonjour, <strong>${sessionScope.sessionUtilisateur.prenom}</strong></p></a>
			    			<a style="margin-left: 10px" href="<%=request.getContextPath()%>/NouvelleVente"><input class="btn btn-success" type="button" value="Vendre"/></a>
			    			<a style="margin-left: 10px" href="<%=request.getContextPath()%>/Deconnexion"><input class="btn btn-danger" type="button" value="Déconnexion"/></a>
			    		<%
			    	}
			    %>
			      
			    </div>
		  	</div>


	<script src="./assets/js/jquery-3.5.0.js"></script>
	<script src="./assets/js/NewSale.js"></script>
	<h1>Nouvelle vente</h1>
	<form id="newSaleRegister" name="newSaleRegister"
		action="NouvelleVenteValidation" method="POST">
		
		
		<img class="fit-picture"
					src="/media/examples/grapefruit-slice-332-332.jpg" alt="img"
					id="imgNewSale" name="imgNewSale">
		
		<div class="form-group input-group">
			<div class="input-group-prepend">
			    <span class="input-group-text"> <i class="fa fa-tag"></i> </span>
			 </div>
	        <input name="name" id="name" class="form-control" placeholder="Libellé de l'article" type="text" required>
   	 	</div>
   	 	
   	 			<div class="form-group input-group">
			<div class="input-group-prepend">
			    <span class="input-group-text"> <i class="fa fa-tag"></i> </span>
			 </div>
	        <input name="description" id="description" class="form-control" placeholder="Description de l'article" type="text" required>
   	 	</div>
   	 	
   	 	<div class="form-group">
    <label for="categorie">Catégorie</label>
    <select class="form-control" id="categorie" name="categorie">
    <option disabled> -- Categories --</option>
		<c:forEach var="element" items="${requestScope['categories']}">
			<option value="${element.getNoCategorie()}">${element.getLibelle()}</option>
		</c:forEach>
    </select>
  </div>
		
		<div class="custom-file">
  <input type="file" name="imgNewSaleUpdate" class="custom-file-input" id="imgNewSaleUpdate" accept="image/png, image/jpeg"
					onchange="uploadNewSaleImg(this);">
  <label class="custom-file-label" for="imgNewSaleUpdate">Photo de l'article</label>
</div>
		
		
   	 			<div class="form-group input-group">
			<div class="input-group-prepend">
			    <span class="input-group-text"> <i class="fa fa-money-bill-alt"></i> </span>
			 </div>
	        <input name="prix" id="prix" class="form-control" placeholder="Mise à prix" type="number" required>
   	 	</div>
   	 	
   	 	   	 			<div class="form-group input-group">
			<div class="input-group-prepend">
			    <span class="input-group-text"> <i class="fa fa-clock"></i> </span>
			 </div>
	        <input name="dateDebut" id="dateDebut" class="form-control" placeholder="Début de l'enchère" type="date" required value="">
   	 	</div>
		
		
		   	 	   	 			<div class="form-group input-group">
			<div class="input-group-prepend">
			    <span class="input-group-text"> <i class="fa fa-clock"></i> </span>
			 </div>
	        <input name="dateFin" id="dateFin" class="form-control" placeholder="Fin de l'enchère" type="date" required value="">
   	 	</div>	

		
		<h5>Retrait :</h5>
		
		
				   	 	   	 			<div class="form-group input-group">
			<div class="input-group-prepend">
			    <span class="input-group-text"> <i class="fa fa-map-marker"></i> </span>
			 </div>
	        <input name="rue" id="rue" class="form-control" placeholder="Rue" type="text" required value="<%= request.getAttribute("rue").equals("") ? "" :   request.getAttribute("rue")%>">
   	 	</div>
   	 	
   	 			   	 	   	 			<div class="form-group input-group">
			<div class="input-group-prepend">
			    <span class="input-group-text"> <i class="fa fa-map-marker"></i> </span>
			 </div>
	        <input name="cp" id="cp" pattern="[0-9]{5}"  class="form-control" placeholder="Code postal" type="number" required value="<%= request.getAttribute("cp").equals("") ? "" :   request.getAttribute("cp")%>">
   	 	</div>
   	 	
   	 			   	 	   	 			<div class="form-group input-group">
			<div class="input-group-prepend">
			    <span class="input-group-text"> <i class="fa fa-map-marker"></i> </span>
			 </div>
	        <input name="ville" id="ville" pattern="[a-zA-Z \-]+" class="form-control" placeholder="Fin de l'enchère" type="text" required value="<%= request.getAttribute("ville").equals("") ? "" :   request.getAttribute("ville")%>">
   	 	</div>
		

	<button type="button" onclick="saveArticle()">Ajouter</button>
	<button type="button" onclick="cancel()">Annuler</button>
	</form>
	

		</div>
	</body>
	<footer>
		<p>Copyrights, All rights reserved</p>
	</footer>
</html>