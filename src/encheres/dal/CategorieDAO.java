package encheres.dal;

import java.util.List;

import encheres.BusinessException;
import encheres.bo.Categorie;

public interface CategorieDAO {
	
	public void insert(Categorie categorie) throws BusinessException;
	public List<Categorie> select() throws BusinessException;
	public Categorie select(int noCategorie) throws BusinessException;
	public Categorie select(String libelle) throws BusinessException;
	public void delete(int noCategorie) throws BusinessException;
}
