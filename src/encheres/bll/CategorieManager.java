package encheres.bll;

import java.util.List;

import encheres.BusinessException;
import encheres.bo.Categorie;
import encheres.dal.CategorieDAO;
import encheres.dal.DAOFactory;

public class CategorieManager {
	
	private CategorieDAO categorieDAO;
	
	public CategorieManager() {
		this.categorieDAO = DAOFactory.getCategorieDAO();
	}
	
	public Categorie ajouterCategorie(String libelle) throws BusinessException {
		
		BusinessException businessException = new BusinessException();
		
		this.validerChampStr(libelle, businessException);
		
		Categorie categorie = null;
		
		if(!businessException.hasErreurs()) {
			
			categorie = new Categorie();
			categorie.setLibelle(libelle);
			
			this.categorieDAO.insert(categorie);
		} else {
			throw businessException;
		}
		return categorie;
	}
	
	public List<Categorie> selectionnerToutesLesCategories() throws BusinessException {
		return this.categorieDAO.select();
	}
	
	public Categorie selectionnerCategorieParNo(int noCategorie) throws BusinessException {
		return this.categorieDAO.select(noCategorie);
	}
	
	public Categorie selectionnerCategorieParLibelle(String libelle) throws BusinessException {
		return this.categorieDAO.select(libelle);
	}
	
	public void supprimerCategorie(int noCategorie) throws BusinessException {
		BusinessException businessException = new BusinessException();
		
		if(!businessException.hasErreurs())
		{	
			this.categorieDAO.delete(noCategorie);
		}
		else
		{
			throw businessException;
		}
	}
	
	private void validerChampStr(String champ, BusinessException businessException) {
		if(champ.length() == 0 || champ == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CATEGORIE_ERREUR);
		}
	}
}
