package encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import encheres.BusinessException;
import encheres.bo.ArticleVendu;

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {

	private static final String INSERT_ARTICLE_VENDU = "insert into articles_vendus(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) values(?,?,?,?,?,?,?,?)";
	private static final String DELETE_ARTICLE_VENDU = "DELETE FROM articles_vendus where no_article = ?";
	private static final String SELECT_ARTICLE_VENDU_ALL = " SELECT " + "	a.no_article as noArticle,"
			+ "	a.nom_article as nomArticle," + "	a.description," + "	a.date_debut_encheres as dateDebutEncheres,"
			+ "	a.date_fin_encheres as dateFinEncheres," + "	a.prix_initial as miseAPrix,"
			+ "	a.prix_vente as prixVente," + "	a.no_utilisateur as noUtilisateur," + "	a.no_categorie as noCategorie"
			+ " FROM" + "	ARTICLES_VENDUS a";
	private static final String SELECT_ARTICLE_VENDU_NO_ARTICLE = " SELECT " + "	a.no_article as noArticle,"
			+ "	a.nom_article as nomArticle," + "	a.description," + "	a.date_debut_encheres as dateDebutEncheres,"
			+ "	a.date_fin_encheres as dateFinEncheres," + "	a.prix_initial as miseAPrix,"
			+ "	a.prix_vente as prixVente," + "	a.no_utilisateur as noUtilisateur," + "	a.no_categorie as noCategorie"
			+ " FROM" + "	ARTICLES_VENDUS a" + " WHERE a.no_article=?";
	private static final String SELECT_ARTICLE_VENDU_NOM = " SELECT " + "	a.no_article as noArticle,"
			+ "	a.nom_article as nomArticle," + "	a.description," + "	a.date_debut_encheres as dateDebutEncheres,"
			+ "	a.date_fin_encheres as dateFinEncheres," + "	a.prix_initial as miseAPrix,"
			+ "	a.prix_vente as prixVente," + "	a.no_utilisateur as noUtilisateur," + "	a.no_categorie as noCategorie"
			+ " FROM" + "	ARTICLES_VENDUS a" + " WHERE a.nom_article=?";
	private static final String SELECT_ARTICLE_VENDU_CATEGORIE = " SELECT " + "	a.no_article as noArticle,"
			+ "	a.nom_article as nomArticle," + "	a.description," + "	a.date_debut_encheres as dateDebutEncheres,"
			+ "	a.date_fin_encheres as dateFinEncheres," + "	a.prix_initial as miseAPrix,"
			+ "	a.prix_vente as prixVente," + "	a.no_utilisateur as noUtilisateur," + "	a.no_categorie as noCategorie"
			+ " FROM" + "	ARTICLES_VENDUS a" + " WHERE a.no_categorie=?";
	private static final String SELECT_ARTICLE_VENDU_UTILISATEUR = " SELECT " + "	a.no_article as noArticle,"
			+ "	a.nom_article as nomArticle," + "	a.description," + "	a.date_debut_encheres as dateDebutEncheres,"
			+ "	a.date_fin_encheres as dateFinEncheres," + "	a.prix_initial as miseAPrix,"
			+ "	a.prix_vente as prixVente," + "	a.no_utilisateur as noUtilisateur," + "	a.no_categorie as noCategorie"
			+ " FROM" + "	ARTICLES_VENDUS a" + " WHERE a.no_utilisateur=?";

	private static final String UPDATE_PRIX = "UPDATE ARTICLES_VENDUS SET prix_vente =? WHERE no_article=?";

	@Override
	public void insert(ArticleVendu articleVendu) throws BusinessException {
		// TODO Auto-generated method stub
		if (articleVendu == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}

		try (Connection cnx = ConnectionProvider.getConnection()) {

			try {
				cnx.setAutoCommit(false);
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_ARTICLE_VENDU,
						PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, articleVendu.getNomArticle());
				pstmt.setString(2, articleVendu.getDescription());
				pstmt.setDate(3, articleVendu.getDateDebutEncheres());
				pstmt.setDate(4, articleVendu.getDateFinEncheres());
				pstmt.setInt(5, (int) articleVendu.getMiseAPrix());
				pstmt.setInt(6, (int) articleVendu.getPrixVente());
				pstmt.setInt(7, articleVendu.getNoUtilisateur());
				pstmt.setInt(8, articleVendu.getNoCategorie());
				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					articleVendu.setNoArticle(rs.getInt(1));
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
	public ArticleVendu selectByNoArticleVendu(int noArticleVendu) throws BusinessException {
		// TODO Auto-generated method stub
		ArticleVendu articleVendu = new ArticleVendu();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ARTICLE_VENDU_NO_ARTICLE,
					PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, noArticleVendu);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getInt("no_article") != articleVendu.getNoArticle()) {
					articleVendu = articleVenduBuilder(rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_VENDU_ECHEC);
			throw businessException;
		}
		return articleVendu;
	}

	@Override
	public ArticleVendu selectByNom(String nom) throws BusinessException {
		// TODO Auto-generated method stub
		ArticleVendu articleVendu = new ArticleVendu();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ARTICLE_VENDU_NOM,
					PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, nom);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getString("nom_article") != articleVendu.getNomArticle()) {
					articleVendu = articleVenduBuilder(rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_VENDU_ECHEC);
			throw businessException;
		}
		return articleVendu;
	}

	@Override
	public List<ArticleVendu> selectAll() throws BusinessException {
		// TODO Auto-generated method stub
		List<ArticleVendu> listeArticleVendu = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ARTICLE_VENDU_ALL);
			ResultSet rs = pstmt.executeQuery();
			ArticleVendu articleVenduCourant = new ArticleVendu();
			while (rs.next()) {
				if (rs.getInt("no_article") != articleVenduCourant.getNoArticle()) {
					articleVenduCourant = articleVenduBuilder(rs);
					listeArticleVendu.add(articleVenduCourant);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_VENDU_ECHEC);
			throw businessException;
		}
		return listeArticleVendu;
	}

	@Override
	public List<ArticleVendu> selectAllByCategorie(int noCategorie) throws BusinessException {
		// TODO Auto-generated method stub
		List<ArticleVendu> listeArticleVendu = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ARTICLE_VENDU_CATEGORIE);
			pstmt.setInt(1, noCategorie);
			ResultSet rs = pstmt.executeQuery();
			ArticleVendu articleVenduCourant = new ArticleVendu();
			while (rs.next()) {
				if (rs.getInt("no_article") != articleVenduCourant.getNoArticle()) {
					articleVenduCourant = articleVenduBuilder(rs);
					listeArticleVendu.add(articleVenduCourant);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_VENDU_ECHEC);
			throw businessException;
		}
		return listeArticleVendu;
	}

	@Override
	public List<ArticleVendu> selectAllByUtilisateur(int noUtilisateur) throws BusinessException {
		// TODO Auto-generated method stub
		List<ArticleVendu> listeArticleVendu = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ARTICLE_VENDU_UTILISATEUR);
			ResultSet rs = pstmt.executeQuery();
			ArticleVendu articleVenduCourant = new ArticleVendu();
			while (rs.next()) {
				if (rs.getInt("no_article") != articleVenduCourant.getNoArticle()) {
					articleVenduCourant = articleVenduBuilder(rs);
					listeArticleVendu.add(articleVenduCourant);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_VENDU_ECHEC);
			throw businessException;
		}
		return listeArticleVendu;
	}

	@Override
	public void delete(int noArticleVendu) throws BusinessException {
		// TODO Auto-generated method stub
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {

				cnx.setAutoCommit(false);
				PreparedStatement pstmt = cnx.prepareStatement(DELETE_ARTICLE_VENDU,
						PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, noArticleVendu);
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

	private ArticleVendu articleVenduBuilder(ResultSet rs) throws SQLException {
		ArticleVendu articleVendu;
		articleVendu = new ArticleVendu();
		articleVendu.setNoArticle(rs.getInt("noArticle"));
		articleVendu.setNomArticle(rs.getString("nomArticle"));
		articleVendu.setDescription(rs.getString("description"));
		articleVendu.setDateDebutEncheres(rs.getDate("dateDebutEncheres"));
		articleVendu.setDateFinEncheres(rs.getDate("dateFinEncheres"));
		articleVendu.setMiseAPrix(rs.getInt("miseAPrix"));
		articleVendu.setPrixVente(rs.getInt("prixVente"));
		articleVendu.setNoUtilisateur(rs.getInt("noUtilisateur"));
		articleVendu.setNoCategorie(rs.getInt("noCategorie"));
		return articleVendu;
	}

	@Override
	public void majPrix(int idArticle, int montant) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {

				cnx.setAutoCommit(false);
				PreparedStatement pstmt = cnx.prepareStatement(UPDATE_PRIX, PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, montant);
				pstmt.setInt(2, idArticle);
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
}
