package encheres.dal;

import java.sql.Date;
import java.util.List;

import encheres.BusinessException;
import encheres.bo.Enchere;

public interface EnchereDAO {
	public void insert(Enchere enchere) throws BusinessException;
	public Enchere selectByNoArticle(int noEnchere) throws BusinessException;
	public List<Enchere> selectByNoUtilisateur(int noUtilisateur) throws BusinessException;
	public List<Enchere> selectByDateEnchere(Date dateEnchere) throws BusinessException;
	public List<Enchere> selectByPeriod(Date dateDebut, Date dateFin) throws BusinessException;
	public void delete(int noUtilisateur) throws BusinessException;
}
