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
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="css/accueil.css">
		<meta charset="ISO-8859-1">
		<title>Accueil</title>
		<%
				List<Categorie> listeCategorie = (List<Categorie>) request.getAttribute("listeCategorie");
		%>
	</head>
	<body>
		<!-- <h1>${sessionScope.sessionUtilisateur.prenom} ? Bienvenue, ${sessionScope.sessionUtilisateur.prenom} :  </h1> -->
		<div class="container-fluid header">
			<div class="row header-text">
			    <div class="col">
			    	<h2>ENI-Enchères</h2>
			    </div>
			    <div class="col-6">
			    </div>
			    <div class="col login-section">
			    <%
			    	if(request.getSession().getAttribute("sessionUtilisateur") == null){
			    		%>
			    			<a style="color:#33FFAC;" href="<%=request.getContextPath()%>/Connexion"><input class="btn btn-outline-success replay" type="button" value="Se Connecter/S'inscrire"/></a>
			    		<%
			    	}else{
			    		%>
			    			<img src="img/user.svg" class="user-image"/>
			    			<p style="margin-left: 10px" >Bonjour, <strong>${sessionScope.sessionUtilisateur.prenom}</strong></p>
			    			<a style="margin-left: 10px" href="<%=request.getContextPath()%>/Deconnexion"><input class="btn btn-danger" type="button" value="Déconnexion"/></a>
			    		<%
			    	}
			    %>
			      
			    </div>
		  	</div>
		  	<div class="row title-section">
		  		<h1>Liste des enchères</h1>
		  		<%
		  			request.setAttribute("nom", "LOL");
		  		%>
		  	</div>
		  	<div class="row filtres-title">
		  		<div class="col">
		  			<br>
		  			<h3>Filtres</h3>
		  		</div>
    			<div class="col"></div>
		  	</div>
		  	<div class="row filtres-section">
		  		<div class="col-sm filtres-colonne">
			      <input class="form-control" type="text" placeholder="Nom">
				    <select class="form-control filtres-select" id="categorieSelect">
				      <option style="color: #AFAFAF;">Selectionnez une catégorie</option>
				      <c:forEach var="c" items="${listeCategorie}">
		  					<option>${c.getLibelle()}</option>
		  			  </c:forEach>
				    </select>
			    </div>
			    <div class="col-sm recherche-colonne">
			      <button type="button" class="btn btn-outline-info recherche-bouton">Rechercher</button>
			    </div>
			    <div class="col-sm">
			    </div>
		  	</div>
		  	<div class="row encheres-section">
			  	<%
					List<Enchere> listeEnchere = (List<Enchere>) request.getAttribute("listeEnchere");
			  		if(listeEnchere!=null && listeEnchere.size()>0){
			  			for (Enchere e : listeEnchere){
			  				ArticleVenduManager articleVenduManager = new ArticleVenduManager();
			  				UtilisateurManager utilisateurManager = new UtilisateurManager();
			  				CategorieManager categorieManager = new CategorieManager();
				  			ArticleVendu a = articleVenduManager.selectionnerArticleVenduParNoArticleVendu(e.getNoArticle());
				  			Utilisateur u = utilisateurManager.selectionnerUtilisateurParNo(e.getNoUtilisateur());
				  			Categorie c = categorieManager.selectionnerCategorieParNo(a.getNoCategorie());
				  			%>
			  					<div class="enchere" id="${a.getNoArticle()}" onclick="">
				  					<h5><strong><%=a.getNomArticle()%></strong></h5>
				  					<p class="enchere-prix"><%=a.getMiseAPrix()%> crédits</p>
				  					<p class="enchere-categorie"><%=c.getLibelle()%></p>
				  					<p>Fin de l'enchère : <%=a.getDateFinEncheres()%></p>
				  					<p>Vendeur : <strong><%=u.getPseudo()%></strong></p>
			  					</div>
				  			<%
				  		}	
			  		}
				%>
		  	</div>
		</div>
	</body>
	<footer>
		<p>Copyrights, All rights reserved</p>
	</footer>
</html>