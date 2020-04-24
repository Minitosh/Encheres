<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="encheres.bo.ArticleVendu"%>
<%@page import="encheres.bo.Categorie"%>
<%@page import="encheres.bo.Enchere"%>
<%@page import="encheres.bo.Retrait"%>
<%@page import="encheres.bo.Utilisateur"%>
<%@page import="encheres.bll.UtilisateurManager"%>
<%@page import="encheres.bll.ArticleVenduManager"%>
<%@page import="encheres.bll.CategorieManager"%>
<%@page import="encheres.bll.EnchereManager"%>
<%@page import="encheres.bll.RetraitManager"%>
<%@page import="encheres.EtatVente"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Enchère</title>
</head>
<body>
<h1>Détail vente</h1>
<%
ArticleVenduManager articleManager = new ArticleVenduManager();
CategorieManager categorieManager = new CategorieManager();
UtilisateurManager utilisateurManager = new UtilisateurManager();
EnchereManager enchereManager = new EnchereManager();
RetraitManager retraitManager = new RetraitManager();


ArticleVendu article = articleManager.selectionnerArticleVenduParNoArticleVendu(Integer.parseInt(request.getParameter("idArticle")));
Categorie categorie = categorieManager.selectionnerCategorieParNo(article.getNoCategorie());
Utilisateur vendeur = utilisateurManager.selectionnerUtilisateurParNo(article.getNoUtilisateur());
Enchere enchere = enchereManager.selectionnerEnchereParNoArticle(article.getNoArticle());
Utilisateur acheteur = utilisateurManager.selectionnerUtilisateurParNo(enchere.getNoUtilisateur());
Retrait retrait = retraitManager.selectionnerRetrait(article.getNoArticle());

%>
<table>
		<thead>
			<th></th>
			<th></th>
			<th></th>
		</thead>
		<tbody>
			<tr>
				<td><img class="fit-picture"
					src="" alt="img"
					id="imgNewSale" name="imgNewSale"></td>
				<td><p>Article :</p> 
				</td>
				<td><p><%=article.getNomArticle()%></p></td>
			</tr>
			<tr>
				<td></td>
				<td><p>Description :</p></td>
				<td><p><%=article.getDescription()%></p></td>
			</tr>
			<tr>
				<td></td>
				<td><p >Catégorie :</p> </td>
				<td><p><%=categorie.getLibelle()%></p></td>
			</tr>
			<tr>
				<td></td>
				<td><p>Meilleur offre :</p></td>
				<td><p><%=enchere.getMontantEnchere()+" pts par "+acheteur.getPseudo() %></p></td>
				
			</tr>
			<tr>
				<td></td>
				<td><p >Mise à prix :</p> </td>
				<td><p><%=article.getMiseAPrix() %></p></td>
			</tr>
			<tr>
				<td></td>
				<td><p>Fin de l'enchère :</p></td>
				<td><p><%=article.getDateFinEncheres() %></p></td>
			</tr>
			<tr>
				<td></td>
				<td><h3>Retrait :</h3></td>
				<td><p><%=retrait.getRue()+" "+retrait.getCodePostal()+" "+retrait.getVille() %></p></td>
			</tr>
			<tr>
			<form id="userForm" action="Utilisateur" method="GET">
				<td></td>
				<td><p >Vendeur :</p></td>
				<td><a  href="#" onclick="document.getElementById('userForm').submit();"><%=vendeur.getPseudo() %></a></td>
				<input type="hidden" id="idVendeur" name="idVendeur" value="<%=vendeur.getNoUtilisateur()%>">
				</form>
			</tr>
			<tr>
			<%
			    	if(request.getSession().getAttribute("sessionUtilisateur") != null && article.getEtatVente().equals(EtatVente.EN_COURS)){
			    		%>
			<form action="ServletNewAuction" method="POST">
				<td></td>
				<td><label for="montant">Ma proposition :</label>
				<input type="number" name="montant" id="montant" value="<%=article.getPrixVente()%>"></td>
				<input type="hidden" id="idArticle" name="idArticle" value="<%=article.getNoArticle()%>">
				<td><button type="submit">Enchérir</button></td>
				</form>
				<%}else {
				 if(article.getEtatVente().equals(EtatVente.TERMINE)){
					%>
					 <td></td>
					 <td><h1 style="color:red">Terminé</h1></td>
					 <td></td>
					 <%
				 }
				}
					
					%>
				
			</tr>
	</table>


</body>
</html>