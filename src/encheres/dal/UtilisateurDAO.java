package encheres.dal;

import encheres.bo.Utilisateur;

public interface UtilisateurDAO {
	
	public void insert(Utilisateur utilisateur);
	public Utilisateur select();
	public Utilisateur select(String nom);
	public Utilisateur select(int noUtilisateur);
	public void delete(int noUtilisateur);

}
