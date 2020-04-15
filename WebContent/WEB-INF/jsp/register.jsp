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
	<form id="accountInfoRegister" name="accountInfoRegister">
		<table>
			<thead>
				<th></th>
				<th></th>
			</thead>
			<tbody>
			<tr>
				<td>
					<label for="pseudo">Pseudo :</label>
					<input type="text" name="pseudo" id="pseudo">
				</td>
				<td>
					<label for="nom">Nom :</label>
					<input type="text" name="nom" id="nom">
				</td>
			</tr>
			<tr>
				<td>
				<label for="prenom">Prenom :</label>
				<input type="text" name="prenom" id="prenom">
				</td>
				<td>
				
				<label for="mail">Email :</label>
				<input type="email" name="mail" id="mail">
				</td>
			</tr>
			<tr>
				<td>
				<label for="tel">Telephone :</label>
				<input type="tel" name="tel" id="tel">
				</td>
				<td>
				<label for="rue">Rue :</label>
				<input type="text" name="rue" id="rue">
				</td>
				</tr>
				<tr>
				<td>
				<label for="cp">Code postal :</label>
				<input type="number" name="cp" id="cp">
				</td>
				<td>
				<label for="ville">Ville :</label>
				<input type="text" name="ville" id="ville">
				</td>
				</tr>
				<tr>
				<td>
				<label for="mdp">Mot de passe :</label>
				<input type="password" name="mdp" id="mdp">
				</td>
				<td>
				<label for="mdpConfirm">Confirmation :</label>
				<input type="password" name="mdpConfirm" id="mdpConfirm">
				</td>
				</tr>		
			</tbody>
		</table>
	</form>
	
	<button onclick="saveAccount()">Créer</button>
	
	<button>Annuler</button>

</body>
</html>