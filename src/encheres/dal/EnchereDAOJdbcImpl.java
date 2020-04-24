package encheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import encheres.BusinessException;
import encheres.bo.Enchere;

public class EnchereDAOJdbcImpl implements EnchereDAO {

	private static final String INSERT_ENCHERE = "insert into encheres(no_utilisateur, no_article, date_enchere, montant_enchere) values(?,?,?,?)";
	private static final String DELETE_ENCHERE = "DELETE FROM encheres where no_article = ?";
	private static final String SELECT_ENCHERE_ALL = " SELECT " + "	e.no_article as noArticle,"
			+ "	e.no_utilisateur as noUtilisateur," + "	e.date_enchere as dateEnchere,"
			+ "	e.montant_enchere as montantEncheres" + " FROM" + "	ENCHERES e";
	private static final String SELECT_ENCHERE_UTILISATEUR = "SELECT e.no_article as noArticle,"
			+ "	e.no_utilisateur as noUtilisateur," + "	e.date_enchere as dateEnchere,"
			+ "	e.montant_enchere as montantEncheres" + " FROM" + "	ENCHERES e" + " WHERE e.no_utilisateur = ?";
	private static final String SELECT_ENCHERE_ARTICLE = "	SELECT e.no_article as noArticle,"
			+ "	e.no_utilisateur as noUtilisateur," + "	e.date_enchere as dateEnchere,"
			+ "	e.montant_enchere as montantEncheres" + " FROM" + "	ENCHERES e" + " WHERE e.no_article = ?";
	private static final String SELECT_ENCHERE_DATE = "	SELECT e.no_article as noArticle,"
			+ "	e.no_utilisateur as noUtilisateur," + "	e.date_enchere as dateEnchere,"
			+ "	e.montant_enchere as montantEncheres" + " FROM" + "	ENCHERES e" + " WHERE e.date_enchere = ?";
	private static final String SELECT_ENCHERE_PERIODE = "SELECT e.no_article as noArticle,"
			+ "	e.no_utilisateur as noUtilisateur," + "	e.date_enchere as dateEnchere,"
			+ "	e.montant_enchere as montantEncheres" + " FROM" + "	ENCHERES e"
			+ " WHERE e.date_enchere BETWEEN ? AND ?";
	@Override
	public void insert(Enchere enchere) throws BusinessException {
		// TODO Auto-generated method stub
		if (enchere == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}

		try (Connection cnx = ConnectionProvider.getConnection()) {

			try {
				cnx.setAutoCommit(false);
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_ENCHERE, PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, enchere.getNoUtilisateur());
				pstmt.setInt(2, enchere.getNoArticle());
				pstmt.setDate(3, enchere.getDateEnchere());
				pstmt.setInt(4, (int) enchere.getMontantEnchere());
				pstmt.executeUpdate();
				pstmt.close();
				cnx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
	}

	@Override
	public List<Enchere> selectAll() throws BusinessException {
		// TODO Auto-generated method stub
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ENCHERE_ALL);
			ResultSet rs = pstmt.executeQuery();
			Enchere enchereCourante = new Enchere();
			while (rs.next()) {
				if (rs.getInt("noArticle") != enchereCourante.getNoArticle()) {
					enchereCourante = enchereBuilder(rs);
					listeEnchere.add(enchereCourante);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_VENDU_ECHEC);
			throw businessException;
		}
		return listeEnchere;
	}

	@Override
	public Enchere selectByNoArticle(int noArticle) throws BusinessException {
		// TODO Auto-generated method stub
		Enchere enchere = new Enchere();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ENCHERE_ARTICLE,
					PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, noArticle);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
					if(rs.getInt("noArticle")!=enchere.getNoArticle())
					{
						enchere = enchereBuilder(rs);
					}

			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ENCHERE_ECHEC);
			throw businessException;
		}
		return enchere;
	}

	@Override
	public List<Enchere> selectByNoUtilisateur(int noUtilisateur) throws BusinessException {
		// TODO Auto-generated method stub
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ENCHERE_UTILISATEUR);
			pstmt.setInt(1, noUtilisateur);
			ResultSet rs = pstmt.executeQuery();
			Enchere enchereCourante=new Enchere();
			while(rs.next())
			{
				if(rs.getInt("noArticle")!=enchereCourante.getNoArticle())
				{
					enchereCourante = enchereBuilder(rs);
					listeEnchere.add(enchereCourante);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_VENDU_ECHEC);
			throw businessException;
		}
		return listeEnchere;
	}

	@Override
	public List<Enchere> selectByDateEnchere(Date dateEnchere) throws BusinessException {
		// TODO Auto-generated method stub
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ENCHERE_DATE);
			pstmt.setDate(1, dateEnchere);
			ResultSet rs = pstmt.executeQuery();
			Enchere enchereCourante=new Enchere();
			while(rs.next())
			{
				if(rs.getInt("noArticle")!=enchereCourante.getNoArticle())
				{
					enchereCourante = enchereBuilder(rs);
					listeEnchere.add(enchereCourante);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_VENDU_ECHEC);
			throw businessException;
		}
		return listeEnchere;
	}

	@Override
	public List<Enchere> selectByPeriod(Date dateDebut, Date dateFin) throws BusinessException {
		// TODO Auto-generated method stub
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ENCHERE_PERIODE);
			pstmt.setDate(1, dateDebut);
			pstmt.setDate(2, dateFin);
			ResultSet rs = pstmt.executeQuery();
			Enchere enchereCourante=new Enchere();
			while(rs.next())
			{
				if(rs.getInt("noArticle")!=enchereCourante.getNoArticle())
				{
					enchereCourante = enchereBuilder(rs);
					listeEnchere.add(enchereCourante);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_VENDU_ECHEC);
			throw businessException;
		}
		return listeEnchere;
	}

	@Override
	public void delete(int noArticle) throws BusinessException {
		// TODO Auto-generated method stub
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {

				cnx.setAutoCommit(false);
				PreparedStatement pstmt = cnx.prepareStatement(DELETE_ENCHERE, PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, noArticle);
				pstmt.executeUpdate();
				pstmt.close();
				cnx.commit();

			} catch (Exception e) {
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.DELETE_OBJET_ECHEC);
			throw businessException;
		}
	}

	private Enchere enchereBuilder(ResultSet rs) throws SQLException {
		Enchere enchere;
		enchere = new Enchere();
		enchere.setNoArticle(rs.getInt("noArticle"));
		enchere.setNoUtilisateur(rs.getInt("noUtilisateur"));
		enchere.setDateEnchere(rs.getDate("dateEnchere"));
		enchere.setMontantEnchere(rs.getInt("montantEncheres"));
		return enchere;
	}

}
