package encheres.bll;

import encheres.BusinessException;
import encheres.bo.Utilisateur;
import encheres.dal.DAOFactory;
import encheres.dal.UtilisateurDAO;

public class UtilisateurManager {

	private UtilisateurDAO utilisateurDAO;

	public UtilisateurManager() {
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

	public Utilisateur ajouterUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasee, int credit, Boolean administrateur)
			throws BusinessException {

		BusinessException businessException = new BusinessException();

		this.validerChampStr(pseudo, businessException);
		this.validerChampStr(nom, businessException);
		this.validerChampStr(prenom, businessException);
		this.validerChampStr(email, businessException);
		this.validerChampStr(rue, businessException);
		this.validerChampStr(codePostal, businessException);
		this.validerChampStr(ville, businessException);
		this.validerChampStr(motDePasee, businessException);

		Utilisateur utilisateur = null;

		if (!businessException.hasErreurs()) {

			utilisateur = new Utilisateur();
			utilisateur.setPseudo(pseudo);
			utilisateur.setNom(nom);
			utilisateur.setPrenom(prenom);
			utilisateur.setEmail(email);
			utilisateur.setTelephone(telephone);
			utilisateur.setRue(rue);
			utilisateur.setCodePostal(codePostal);
			utilisateur.setVille(ville);
			utilisateur.setMotDePasse(motDePasee);
			utilisateur.setCredit(credit);
			utilisateur.setAdministrateur(administrateur);

			this.utilisateurDAO.insert(utilisateur);
		} else {
			throw businessException;
		}
		return utilisateur;
	}

	public Utilisateur selectionnerUtilisateurParNo(int noUtilisateur) throws BusinessException {

		return this.utilisateurDAO.select(noUtilisateur);
	}

	public Utilisateur selectionnerUtilisateurParEmail(String email) throws BusinessException {

		return this.utilisateurDAO.select(email);
	}

	public void supprimerUtilisateur(int noUtilisateur) throws BusinessException {
		BusinessException businessException = new BusinessException();

		if (!businessException.hasErreurs()) {
			this.utilisateurDAO.delete(noUtilisateur);
		} else {
			throw businessException;
		}
	}

	private void validerChampStr(String champ, BusinessException businessException) {
		if (champ.length() == 0 || champ == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEUR_ERREUR);
		}
	}

	public void decrediter(float montant, int idUser) throws BusinessException {
		this.utilisateurDAO.decrediter(montant, idUser);
	}
}
