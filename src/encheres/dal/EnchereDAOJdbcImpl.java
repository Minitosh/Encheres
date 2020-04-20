package encheres.dal;

import java.sql.Date;
import java.util.List;

import encheres.BusinessException;
import encheres.bo.Enchere;

public class EnchereDAOJdbcImpl implements EnchereDAO{

	private static final String INSERT_ENCHERE = "insert into encheres(no_utilisateur, no_article, date_enchere, montant_encheres) values(?,?,?,?)";
	private static final String DELETE_ENCHERE = "DELETE FROM encheres where no_article = ?";
	
	
	@Override
	public void insert(Enchere enchere) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Enchere selectByNoArticle(int noEnchere) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Enchere> selectByNoUtilisateur(int noUtilisateur) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Enchere> selectByDateEnchere(Date dateEnchere) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Enchere> selectByPeriod(Date dateDebut, Date dateFin) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int noUtilisateur) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

}
