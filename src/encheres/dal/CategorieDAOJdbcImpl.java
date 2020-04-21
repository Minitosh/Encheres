package encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import encheres.BusinessException;
import encheres.bo.Categorie;

public class CategorieDAOJdbcImpl implements CategorieDAO {

	private static final String INSERT_CATEGORIE = "insert into categories(libelle) values(?)";
	private static final String DELETE_CATEGORIE = "DELETE FROM categories where no_categorie = ?";
	private static final String SELECT_CATEGORIE_ALL = " SELECT " + "	c.no_categorie as noCategorie," + "	c.libelle"
			+ " FROM" + "	CATEGORIES c";
	private static final String SELECT_CATEGORIE_NO = " SELECT " + "	c.no_categorie as noCategorie," + "	c.libelle"
			+ " FROM" + "	CATEGORIES c" + " WHERE c.no_categorie=?";
	private static final String SELECT_CATEGORIE_LIBELLE = " SELECT " + "	c.no_categorie as noCategorie,"
			+ "	c.libelle" + " FROM" + "	CATEGORIES c" + " WHERE c.libelle=?";

	@Override
	public void insert(Categorie categorie) throws BusinessException {
		// TODO Auto-generated method stub
		if (categorie == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}

		try (Connection cnx = ConnectionProvider.getConnection()) {

			try {
				cnx.setAutoCommit(false);
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_CATEGORIE,
						PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, categorie.getLibelle());
				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					categorie.setNoCategorie(rs.getInt(1));
				}
				rs.close();
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
	public List<Categorie> selectAll() throws BusinessException {
		// TODO Auto-generated method stub
		List<Categorie> listeCategorie = new ArrayList<Categorie>();
		PreparedStatement pstmt = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			pstmt = cnx.prepareStatement(SELECT_CATEGORIE_ALL);
			ResultSet rs = pstmt.executeQuery();
			Categorie categorieCourante = new Categorie();
			while (rs.next()) {
				if (rs.getInt("nocategorie") != categorieCourante.getNoCategorie()) {
					categorieCourante = categorieBuilder(rs);
					listeCategorie.add(categorieCourante);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_CATEGORIE_ECHEC);
			throw businessException;
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listeCategorie;
	}

	@Override
	public Categorie select(int noCategorie) throws BusinessException {
		// TODO Auto-generated method stub
		Categorie categorie = new Categorie();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_CATEGORIE_NO,
					PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, noCategorie);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getInt("no_categorie") != categorie.getNoCategorie()) {
					categorie = categorieBuilder(rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_CATEGORIE_ECHEC);
			throw businessException;
		}
		return categorie;
	}

	@Override
	public Categorie select(String libelle) throws BusinessException {
		// TODO Auto-generated method stub
		Categorie categorie = new Categorie();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_CATEGORIE_LIBELLE,
					PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, libelle);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getString("libelle") != categorie.getLibelle()) {
					categorie = categorieBuilder(rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_CATEGORIE_ECHEC);
			throw businessException;
		}
		return categorie;
	}

	@Override
	public void delete(int noCategorie) throws BusinessException {
		// TODO Auto-generated method stub
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {

				cnx.setAutoCommit(false);
				PreparedStatement pstmt = cnx.prepareStatement(DELETE_CATEGORIE,
						PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, noCategorie);
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

	private Categorie categorieBuilder(ResultSet rs) throws SQLException {
		Categorie categorie;
		categorie = new Categorie();
		categorie.setNoCategorie(rs.getInt("noCategorie"));
		categorie.setLibelle(rs.getString("libelle"));
		return categorie;
	}

}
