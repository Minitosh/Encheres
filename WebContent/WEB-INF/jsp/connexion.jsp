<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
    	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="css/connexion.css">
        <meta charset="utf-8" />
        <title>Connexion</title>
        <link type="text/css" rel="stylesheet" href="form.css" />
    </head>
    <body>
    	<div class="content">
    		<form method="post" action="connexion">
	            <fieldset>
	            	<img src="img/login.svg" class="login-image"/>
	                <h1>Connexion</h1>
					
	                <label for="nom">Adresse email <span class="requis">*</span></label>
	                <input class="form-control" type="email" id="email" name="email" value="<c:out value="${utilisateur.email}"/>" size="20" maxlength="60" />
	                <div class="erreur">${form.erreurs['email']}</div>
	                <br />
	
	                <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
	                <input class="form-control" type="password" id="motDePasse" name="motDePasse" value="" size="20" maxlength="20" />
	                <div class="erreur">${form.erreurs['motDePasse']}</div>
	                <br />
	
	                <input type="submit" value="Connexion" class="btn btn-outline-success replay" />
	                <br />
	                
	                <c:if test="${!empty sessionScope.sessionUtilisateur}">
	                    <%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
	                    <p class="succes">Vous êtes connecté(e) avec l'adresse : ${sessionScope.sessionUtilisateur.email}</p>
	                </c:if>
	                
	            </fieldset>
        	</form>
    	</div>
    </body>
</html>