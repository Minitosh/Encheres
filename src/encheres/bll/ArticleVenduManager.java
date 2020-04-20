package encheres.bll;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import encheres.BusinessException;
import encheres.bo.ArticleVendu;
import encheres.dal.ArticleVenduDAO;
import encheres.dal.DAOFactory;

public class ArticleVenduManager {
	
	private ArticleVenduDAO articleVenduDAO;
	
	public ArticleVenduManager() {
		this.articleVenduDAO = DAOFactory.getArticleVenduDAO();
	}
	
	public ArticleVendu ajouterArticleVendu(String nomArticle, String description, Date dateDebutEncheres, Date dateFinEncheres, int miseAPrix, int prixVente, int noUtilisateur, int noCategorie) throws BusinessException{
		
		BusinessException businessException = new BusinessException();
		
		this.validerChampStr(nomArticle, businessException);
		this.validerChampStr(description, businessException);
		this.validerChampDate(dateDebutEncheres, businessException);
		this.validerChampDate(dateFinEncheres, businessException);
		
		ArticleVendu articleVendu = null;
		
		if(!businessException.hasErreurs()) {
			
			articleVendu = new ArticleVendu();
			
			articleVendu.setNomArticle(nomArticle);
			articleVendu.setDescription(description);
			articleVendu.setDateDebutEncheres(dateDebutEncheres);
			articleVendu.setDateFinEncheres(dateFinEncheres);
			articleVendu.setMiseAPrix(miseAPrix);
			articleVendu.setPrixVente(prixVente);
			articleVendu.setNoUtilisateur(noUtilisateur);
			articleVendu.setNoCategorie(noCategorie);
			
			this.articleVenduDAO.insert(articleVendu);
		} else {
			throw businessException;
		}
		
		return articleVendu;
	}
	
	public ArticleVendu selectionnerArticleVenduParNoArticleVendu(int noArticleVendu) throws BusinessException {
		return this.articleVenduDAO.selectByNoArticleVendu(noArticleVendu);
	}
	
	public ArticleVendu selectionnerArticleVenduParNom(String nomArticle) throws BusinessException {
		return this.articleVenduDAO.selectByNom(nomArticle);
	}
	
	public List<ArticleVendu> selectionnerArticleVenduParNoCategorie(int noCategorie) throws BusinessException {
		return this.articleVenduDAO.selectAllByCategorie(noCategorie);
	}
	
	public List<ArticleVendu> selectionnerArticleVenduParNoUtilisateur(int noUtilisateur) throws BusinessException {
		return this.articleVenduDAO.selectAllByUtilisateur(noUtilisateur);
	}
	
	public void supprimerArticleVendu(int noArticleVendu) throws BusinessException {
		BusinessException businessException = new BusinessException();
		
		if(!businessException.hasErreurs())
		{	
			this.articleVenduDAO.delete(noArticleVendu);
		}
		else
		{
			throw businessException;
		}
	}
	
	
	private void validerChampStr(String champ, BusinessException businessException) {
		if(champ.length() == 0 || champ == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_VENDU_ERREUR);
		}
	}
	
	private void validerChampDate(Date champ, BusinessException businessException) {
		
		if(champ == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_VENDU_ERREUR);
		}else {
			Calendar cal = Calendar.getInstance();
			cal.setLenient(false);
			cal.setTime(champ);
			try {
			    cal.getTime();
			}
			catch (Exception e) {
				businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_VENDU_ERREUR);
			}
		}
	}
}
