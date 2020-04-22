<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
</head>
<body>
	<script src="./assets/js/Register.js"></script>
	<h1>Créer mon profil</h1>
	<form id="accountInfoRegister" name="accountInfoRegister"
		action="ServletAccountRegister" method="POST">
		<table>
			<thead>
				<th></th>
				<th></th>
			</thead>
			<tbody>
				<tr>
					<td>
					<label for="pseudo">Pseudo :</label> <input type="text"
						name="pseudo" id="pseudo" 
						value="<%=request.getParameter("pseudo") == null ? "" : (((boolean)request.getAttribute("isPseudoDuplicate")) ? "" : request.getParameter("pseudo")) %>"
						required></td>
					<td><label for="nom">Nom :</label> <input type="text"
						name="nom" id="nom" pattern="[a-zA-Z -]+"
						value="<%=request.getParameter("nom") == null ? "" : request.getParameter("nom")%>"
						required></td>
				</tr>
				<tr>
					<td><label for="prenom">Prenom :</label> <input type="text"
						name="prenom" id="prenom" pattern="[a-zA-Z -]+"
						value="<%=request.getParameter("prenom") == null ? "" : request.getParameter("prenom")%>"
						required></td>
					<td><label for="mail">Email :</label> <input type="email"
						name="mail" id="mail" 
						value="<%=request.getParameter("mail") == null ? "" : (((boolean)request.getAttribute("isEmailDuplicate")) ? "" : request.getParameter("mail")) %>"
						required></td>
				</tr>
				<tr>
					<td><label for="tel">Telephone :</label> <input type="tel"
						name="tel" id="tel" pattern="[0-9]{5}"
						value="<%=request.getParameter("tel") == null ? "" : request.getParameter("tel")%>"></td>
					<td><label for="rue">Rue :</label> <input type="text"
						name="rue" id="rue"
						value="<%=request.getParameter("rue") == null ? "" : request.getParameter("rue")%>"
						required></td>
				</tr>
				<tr>
					<td><label for="cp">Code postal :</label> <input type="number"
						name="cp" id="cp" pattern="[0-9]{5}"
						value="<%=request.getParameter("cp") == null ? "" : request.getParameter("cp")%>"
						required></td>
					<td><label for="ville">Ville :</label> <input type="text"
						name="ville" id="ville" pattern="[a-zA-Z \-]+"
						value="<%=request.getParameter("ville") == null ? "" : request.getParameter("ville")%>"
						required></td>
				</tr>
				<tr>
					<td><label for="mdp">Mot de passe :</label> <input
						type="password" name="mdp" id="mdp" required></td>
					<td><label for="mdpConfirm">Confirmation :</label> <input
						type="password" name="mdpConfirm" id="mdpConfirm" required>
					</td>
				</tr>
			</tbody>
		</table>
		<button type="button" onclick="saveAccount()">Créer</button>
		<button type="button" onclick="cancel()">Annuler</button>
	</form>

</body>
</html>