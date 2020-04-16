<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Accueil</title>
</head>
<body>
	<h1>Bienvenue, ${sessionScope.sessionUtilisateur.prenom}</h1>
	<h2>Liste des enchères</h2>
	<a style="color:#d10202;" href="<%=request.getContextPath()%>/Deconnexion"><input class="btn btn-outline-danger replay" type="button" value="Déconnexion"/></a>
</body>
</html>