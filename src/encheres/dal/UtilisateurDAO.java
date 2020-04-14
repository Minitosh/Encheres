package encheres.dal;

import encheres.BusinessException;
import encheres.bo.Utilisateur;

public interface UtilisateurDAO {
	
	public void insert(Utilisateur utilisateur) throws BusinessException;
	public Utilisateur select() throws BusinessException;
	public Utilisateur select(String nom) throws BusinessException;
	public Utilisateur select(int noUtilisateur) throws BusinessException;
	public void delete(int noUtilisateur) throws BusinessException;

}
