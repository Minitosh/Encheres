package encheres.bll;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import encheres.BusinessException;
import encheres.bo.Enchere;
import encheres.dal.DAOFactory;
import encheres.dal.EnchereDAO;

public class EnchereManager {
	
	private EnchereDAO enchereDAO;
	
	public EnchereManager() {
		this.enchereDAO = DAOFactory.getEnchereDAO();
	}
	
	public Enchere ajouterEnchere(int noArticle, int noUtilisateur, Date dateEnchere, int montantEnchere) throws BusinessException {
		
		BusinessException businessException = new BusinessException();
		
		this.validerChampDate(dateEnchere, businessException);
		
		Enchere enchere = null;
		
		if(!businessException.hasErreurs()) {
			
			enchere = new Enchere();
			
			enchere.setNoArticle(noArticle);
			enchere.setNoUtilisateur(noUtilisateur);
			enchere.setDateEnchere(dateEnchere);
			enchere.setMontantEnchere(montantEnchere);
			
			enchere.setNoArticle(noArticle);
			
			this.enchereDAO.insert(enchere);
		} else {
			throw businessException;
		}
		
		return enchere;
	}
	
	public Enchere selectionnerEnchereParNoArticle(int noArticle) throws BusinessException {
		return this.enchereDAO.selectByNoArticle(noArticle);
	}
	
	public List<Enchere> selectionnerToutesLesEnchere() throws BusinessException {
		return this.enchereDAO.selectAll();
	}
	
	public List<Enchere> selectionnerEnchereParNoUtilisateur(int noUtilisateur) throws BusinessException {
		return this.enchereDAO.selectByNoUtilisateur(noUtilisateur);
	}
	
	public List<Enchere> selectionnerEnchereParDate(Date dateEnchere) throws BusinessException {
		return this.enchereDAO.selectByDateEnchere(dateEnchere);
	}
	 
	public List<Enchere> selectionnerEnchereParPeriode(Date dateDebut, Date dateFin) throws BusinessException {
		return this.enchereDAO.selectByPeriod(dateDebut, dateFin);
	}
	
	public void supprimerEnchere(int noArticle) throws BusinessException {
		BusinessException businessException = new BusinessException();
		
		if(!businessException.hasErreurs())
		{	
			this.enchereDAO.delete(noArticle);
		}
		else
		{
			throw businessException;
		}
	}
	
	private void validerChampDate(Date champ, BusinessException businessException) {
		
		if(champ == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ENCHERE_ERREUR);
		}else {
			Calendar cal = Calendar.getInstance();
			cal.setLenient(false);
			cal.setTime(champ);
			try {
			    cal.getTime();
			}
			catch (Exception e) {
				businessException.ajouterErreur(CodesResultatBLL.REGLE_ENCHERE_ERREUR);
			}
		}
	}

}
