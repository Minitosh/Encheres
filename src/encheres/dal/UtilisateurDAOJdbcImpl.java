package encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.lang3.tuple.ImmutablePair;

import encheres.BusinessException;
import encheres.bo.Utilisateur;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	private static final String INSERT_UTILISATEUR = "insert into utilisateurs(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) values(?,?,?,?,?,?,?,?,?,?,?)";
	private static final String DELETE_UTILISATEUR = "DELETE FROM utilisateurs where no_utilisateur = ?";

	private static final String SELECT_UTILISATEUR_NO = " SELECT " + "	u.no_utilisateur as noUtilisateur,"
			+ "	u.pseudo," + "	u.nom," + "	u.prenom," + "	u.email," + "	u.telephone," + "	u.rue,"
			+ "	u.code_postal as codePostal," + "	u.ville," + "	u.mot_de_passe as motDePasse," + "	u.credit,"
			+ "	u.administrateur" + " FROM" + " UTILISATEURS u " + "WHERE u.no_utilisateur=?";

	private static final String SELECT_PSEUDO_EMAIL = " SELECT " + " pseudo," + " email" + " FROM" + " UTILISATEURS";

	private static final String SELECT_UTILISATEUR_EMAIL = " SELECT " + " no_utilisateur as noUtilisateur," + "	pseudo,"
			+ " nom," + " prenom," + "	email," + " telephone," + "	rue," + "	code_postal as codePostal," + "	ville,"
			+ "	mot_de_passe as motDePasse," + " credit," + "	administrateur" + " FROM" + " UTILISATEURS"
			+ " WHERE email = ?";

	private static final String UPDATE_CREDIT = "UPDATE UTILISATEURS SET credit =? WHERE no_utilisateur=?";

	@Override
	public void insert(Utilisateur utilisateur) throws BusinessException {
		// TODO Auto-generated method stub
		if (utilisateur == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}

		try (Connection cnx = ConnectionProvider.getConnection()) {

			try {
				cnx.setAutoCommit(false);
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_UTILISATEUR,
						PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, utilisateur.getPseudo());
				pstmt.setString(2, utilisateur.getNom());
				pstmt.setString(3, utilisateur.getPrenom());
				pstmt.setString(4, utilisateur.getEmail());
				pstmt.setString(5, utilisateur.getTelephone());
				pstmt.setString(6, utilisateur.getRue());
				pstmt.setString(7, utilisateur.getCodePostal());
				pstmt.setString(8, utilisateur.getVille());
				pstmt.setString(9, utilisateur.getMotDePasse());
				pstmt.setFloat(10, utilisateur.getCredit());
				pstmt.setBoolean(11, utilisateur.getAdministrateur());
				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					utilisateur.setNoUtilisateur(rs.getInt(1));
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
	public Utilisateur select(int noUtilisateur) throws BusinessException {
		Utilisateur utilisateur = new Utilisateur();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_UTILISATEUR_NO,
					PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, noUtilisateur);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getInt("no_utilisateur") != utilisateur.getNoUtilisateur()) {
					utilisateur = utilisateurBuilder(rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_UTILISATEUR_ECHEC);
			throw businessException;
		}
		return utilisateur;
	}

	@Override
	public Utilisateur select(String email) throws BusinessException {
		Utilisateur utilisateur = new Utilisateur();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_UTILISATEUR_EMAIL,
					PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getString("email") != utilisateur.getEmail()) {
					utilisateur = utilisateurBuilder(rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_UTILISATEUR_ECHEC);
			throw businessException;
		}
		return utilisateur;
	}

	@Override
	public void delete(int noUtilisateur) throws BusinessException {
		// TODO Auto-generated method stub
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {

				cnx.setAutoCommit(false);
				PreparedStatement pstmt = cnx.prepareStatement(DELETE_UTILISATEUR,
						PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, noUtilisateur);
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

	private Utilisateur utilisateurBuilder(ResultSet rs) throws SQLException {
		Utilisateur utilisateur;
		utilisateur = new Utilisateur();
		utilisateur.setNoUtilisateur(rs.getInt("noUtilisateur"));
		utilisateur.setPseudo(rs.getString("pseudo"));
		utilisateur.setNom(rs.getString("nom"));
		utilisateur.setPrenom(rs.getString("prenom"));
		utilisateur.setEmail(rs.getString("email"));
		utilisateur.setTelephone(rs.getString("telephone"));
		utilisateur.setRue(rs.getString("rue"));
		utilisateur.setCodePostal(rs.getString("codePostal"));
		utilisateur.setVille(rs.getString("ville"));
		utilisateur.setMotDePasse(rs.getString("motDePasse"));
		utilisateur.setCredit(rs.getInt("credit"));
		utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
		return utilisateur;
	}

	@Override
	public ArrayList<ImmutablePair<String, String>> getAllPseudoEmail() throws BusinessException {
		ArrayList<ImmutablePair<String, String>> list = new ArrayList<ImmutablePair<String, String>>();
		PreparedStatement pstmt = null;

		try (Connection cnx = ConnectionProvider.getConnection()) {

			cnx.setAutoCommit(false);
			pstmt = cnx.prepareStatement(SELECT_PSEUDO_EMAIL);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new ImmutablePair<>(rs.getString("pseudo"), rs.getString("email")));
			}
			pstmt.close();
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_UTILISATEUR_ECHEC);
			throw businessException;
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public void decrediter(float montant, int idUser) throws BusinessException {

		Utilisateur user = this.select(idUser);

		try (Connection cnx = ConnectionProvider.getConnection()) {

			cnx.setAutoCommit(false);
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_CREDIT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setFloat(1, (user.getCredit() - montant));
			pstmt.setInt(2, idUser);
			pstmt.executeUpdate();
			pstmt.close();
			cnx.commit();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
