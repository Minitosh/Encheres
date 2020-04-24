<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Nouvelle vente</title>
</head>
<body>
	<script src="./assets/js/jquery-3.5.0.js"></script>
	<script src="./assets/js/NewSale.js"></script>
	<h1>Nouvelle vente</h1>
	<form id="newSaleRegister" name="newSaleRegister"
		action="NouvelleVenteValidation" method="POST">
	<table>
		<thead>
			<th></th>
			<th></th>
		</thead>
		<tbody>
			<tr>
				<td><img class="fit-picture"
					src="/media/examples/grapefruit-slice-332-332.jpg" alt="img"
					id="imgNewSale" name="imgNewSale"></td>
				<td><label for="name">Article :</label> <input type="text"
					name="name" id="name"required></td>
			</tr>
			<tr>
				<td></td>
				<td><label for="description">Description :</label> <input
					type="text" name="description" id="description"></td>
			</tr>
			<tr>
				<td></td>
				<td><label for="categorie">Catégorie :</label> 
				<select	name="categorie" id="categorie">
					<option> -- Categories --</option>
						<c:forEach var="element" items="${requestScope['categories']}">
							<option value="${element.getNoCategorie()}">${element.getLibelle()}</option>
						</c:forEach>

				</select></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="file" id="imgNewSaleUpdate"
					name="imgNewSaleUpdate" accept="image/png, image/jpeg"
					onchange="uploadNewSaleImg(this);"></td>
			</tr>
			<tr>
				<td></td>
				<td><label for="prix">Mise à prix :</label> <input
					type="number" name="prix" id="prix"></td>
			</tr>
			<tr>
				<td></td>
				<td><label for="dateDebut">Début de l'enchère :</label> <input
					type="date" name="dateDebut" id="dateDebut" required></td>
			</tr>
			<tr>
				<td></td>
				<td><label for="dateFin">Fin de l'enchère :</label> <input
					type="date" name="dateFin" id="dateFin" required></td>
			</tr>
			<tr>
				<td></td>
				<td><h3>Retrait :</h3></td>
			</tr>
			<tr>
				<td></td>
				<td><label for="rue">Rue :</label> <input
					type="text" name="rue" id="rue" 
					value="<%= request.getAttribute("rue").equals("") ? "" :   request.getAttribute("rue")%>"
					required></td>
			</tr>
			<tr>
				<td></td>
				<td><label for="cp">Code postal :</label>
				<input type="number" name="cp" id="cp" pattern="[0-9]{5}" 
					value="<%= request.getAttribute("cp").equals("") ? "" :   request.getAttribute("cp")%>"
					 required></td>
			</tr>
			<tr>
				<td></td>
				<td><label for="ville">Ville :</label>
				<input type="text" name="ville" id="ville" pattern="[a-zA-Z \-]+" 
					value="<%= request.getAttribute("ville").equals("") ? "" :   request.getAttribute("ville")%>"
					 required></td>
			</tr>
	</table>
	<button type="button" onclick="saveArticle()">Ajouter</button>
	<button type="button" onclick="cancel()">Annuler</button>
	</form>
</body>
</html>