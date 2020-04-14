package encheres.dal;

import encheres.bo.Utilisateur;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {
	
	private static final String INSERT_UTILISATEUR = "insert into utilisateurs(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) values(?,?,?,?,?,?,?,?,?,?,?)";

	@Override
	public void insert(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Utilisateur select() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur select(String nom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur select(int noUtilisateur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int noUtilisateur) {
		// TODO Auto-generated method stub
		
	}

	
}
