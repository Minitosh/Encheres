package encheres.bll;

import encheres.BusinessException;
import encheres.bo.Retrait;
import encheres.dal.DAOFactory;
import encheres.dal.RetraitDAO;

public class RetraitManager {

	private RetraitDAO retraitDAO;
	
	public RetraitManager() {
		this.retraitDAO = DAOFactory.getRetraitDAO();
	}
	
	public Retrait ajouterRetrait(int noArticle, String rue, String codePostal, String ville) throws BusinessException {
		
		
		BusinessException businessException = new BusinessException();
		
		this.validerChampStr(rue, businessException);
		this.validerChampStr(codePostal, businessException);
		this.validerChampStr(ville, businessException);
		
		Retrait retrait = null;
		
		if(!businessException.hasErreurs()) {
			
			retrait = new Retrait();
			
			retrait.setNoArticle(noArticle);
			retrait.setRue(rue);
			retrait.setCodePostal(codePostal);
			retrait.setVille(ville);
			
			retrait.setNoArticle(noArticle);
			
			this.retraitDAO.insert(retrait);
		} else {
			throw businessException;
		}
		
		return retrait;
	}
	
	public Retrait selectionnerRetrait(int noArticle) throws BusinessException {
		return this.retraitDAO.select(noArticle);
	}
	
	public void supprimerRetrait(int noArticle) throws BusinessException {
		BusinessException businessException = new BusinessException();
		
		if(!businessException.hasErreurs())
		{	
			this.retraitDAO.delete(noArticle);
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
}
