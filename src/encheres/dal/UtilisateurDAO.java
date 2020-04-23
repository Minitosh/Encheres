package encheres.dal;

import java.util.ArrayList;

import org.apache.commons.lang3.tuple.ImmutablePair;

import encheres.BusinessException;
import encheres.bo.Utilisateur;

public interface UtilisateurDAO {

	public void insert(Utilisateur utilisateur) throws BusinessException;

	public Utilisateur select(int noUtilisateur) throws BusinessException;

	public Utilisateur select(String email) throws BusinessException;

	public void delete(int noUtilisateur) throws BusinessException;

	public ArrayList<ImmutablePair<String, String>> getAllPseudoEmail() throws BusinessException;

	public void decrediter(float montant, int idUser) throws BusinessException;

}
