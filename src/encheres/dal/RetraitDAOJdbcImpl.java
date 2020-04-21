package encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import encheres.BusinessException;
import encheres.bo.Retrait;

public class RetraitDAOJdbcImpl implements RetraitDAO {
	
	private static final String INSERT_RETRAIT = "insert into retraits(no_article, rue, code_postal, ville) values(?,?,?,?)";
	private static final String DELETE_RETRAIT = "DELETE FROM retraits where no_article = ?";
	private static final String SELECT_RETRAIT = "	r.no_article as noArticle," + 
			"	r.rue" +
			"	r.code_postal as codePostal," +
			"	r.ville," +
			" FROM" + 
			"	RETRAITS r" +
			" WHERE r.no_article = ?";

	@Override
	public void insert(Retrait retrait) throws BusinessException {
		// TODO Auto-generated method stub
		if(retrait == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			
			try {
				cnx.setAutoCommit(false);
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_RETRAIT, PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, retrait.getNoArticle());
				pstmt.setString(2, retrait.getRue());
				pstmt.setString(3, retrait.getCodePostal());
				pstmt.setString(4, retrait.getVille());
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
	public Retrait select(int noArticle) throws BusinessException {
		// TODO Auto-generated method stub
		Retrait retrait = new Retrait();
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_RETRAIT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, noArticle);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
					if(rs.getInt("no_article")!=retrait.getNoArticle())
					{
						retrait = retraitBuilder(rs);
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_RETRAIT_ECHEC);
			throw businessException;
		}
		return retrait;
	}

	@Override
	public void delete(int noArticle) throws BusinessException {
		// TODO Auto-generated method stub
		try(Connection cnx = ConnectionProvider.getConnection()) {
			try {
				
				cnx.setAutoCommit(false);
				PreparedStatement pstmt = cnx.prepareStatement(DELETE_RETRAIT, PreparedStatement.RETURN_GENERATED_KEYS);
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
	
	private Retrait retraitBuilder(ResultSet rs) throws SQLException {
		Retrait retrait;
		retrait = new Retrait();
		retrait.setNoArticle(rs.getInt("noArticle"));
		retrait.setRue(rs.getString("rue"));
		retrait.setCodePostal(rs.getString("codePostal"));
		retrait.setVille(rs.getString("ville"));
		return retrait;
	}

}
