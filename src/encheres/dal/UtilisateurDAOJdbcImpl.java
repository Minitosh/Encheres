package encheres.dal;

import encheres.BusinessException;
import encheres.bo.Utilisateur;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Connection;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {
	
	private static final String INSERT_UTILISATEUR = "insert into utilisateurs(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) values(?,?,?,?,?,?,?,?,?,?,?)";

	@Override
	public void insert(Utilisateur utilisateur) throws BusinessException {
		// TODO Auto-generated method stub
		if(utilisateur == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			cnx.setAutoCommit(false);
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_UTILISATEUR, PreparedStatement.RETURN_GENERATED_KEYS);
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
			if(rs.next())
			{
				utilisateur.setNoUtilisateur(rs.getInt(1));
			}
			rs.close();
			pstmt.close();
			pstmt = cnx.prepareStatement(INSERT_UTILISATEUR, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.close();
			cnx.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public Utilisateur select() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur select(String nom) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur select(int noUtilisateur) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int noUtilisateur) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	
}
