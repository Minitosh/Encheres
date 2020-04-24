<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Register</title>
</head>
<body>
<script src="./assets/js/Register.js"></script>
	<h1> Mon profil</h1>
	<form id="accountInfoRegister" name="accountInfoRegister" action="ServletAccountRegister" method="POST">
		<table>
			<thead>
				<th></th>
				<th></th>
			</thead>
			<tbody>
			<tr>
				<td>
					<label for="pseudo">Pseudo :</label>
					<input type="text" name="pseudo" id="pseudo" required>
				</td>
				<td>
					<label for="nom">Nom :</label>
					<input type="text" name="nom" id="nom" pattern="[a-zA-Z -]+" required>
				</td>
			</tr>
			<tr>
				<td>
				<label for="prenom">Prenom :</label>
				<input type="text" name="prenom" id="prenom" pattern="[a-zA-Z -]+" required>
				</td>
				<td>
				
				<label for="mail">Email :</label>
				<input type="email" name="mail" id="mail" required>
				</td>
			</tr>
			<tr>
				<td>
				<label for="tel">Telephone :</label>
				<input type="tel" name="tel" id="tel" pattern="[0-9]{10}" >
				</td>
				<td>
				<label for="rue">Rue :</label>
				<input type="text" name="rue" id="rue" required>
				</td>
				</tr>
				<tr>
				<td>
				<label for="cp">Code postal :</label>
				<input type="number" name="cp" id="cp" pattern="[0-9]{5}" required>
				</td>
				<td>
				<label for="ville">Ville :</label>
				<input type="text" name="ville" id="ville" pattern="[a-zA-Z \-]+" required>
				</td>
				</tr>
				<tr>
				<td>
				<label for="mdp">Mot de passe :</label>
				<input type="password" name="mdp" id="mdp" required>
				</td>
				<td>
				<label for="mdpConfirm">Confirmation :</label>
				<input type="password" name="mdpConfirm" id="mdpConfirm" required>
				</td>
				</tr>		
			</tbody>
		</table>
		<button type="button" onclick="saveAccount()">Créer</button>
		<button type="button" onclick="cancel()">Annuler</button>
	</form>

</body>
</html>