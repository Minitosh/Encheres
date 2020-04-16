package encheres.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import encheres.BusinessException;
import encheres.bll.UtilisateurManager;
import encheres.bo.Utilisateur;


public final class ConnexionForm {
    private static final String CHAMP_EMAIL = "email";
    private static final String CHAMP_PASS = "motDePasse";

    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public Utilisateur connecterUtilisateur( HttpServletRequest request ) {
    	
        String email = getValeurChamp( request, CHAMP_EMAIL );
        String motDePasse = getValeurChamp( request, CHAMP_PASS );

        Utilisateur utilisateurTest = new Utilisateur();
        UtilisateurManager manager = new UtilisateurManager();
        
		try {
			utilisateurTest = manager.selectionnerUtilisateurParEmail(email);
		} catch (BusinessException e) {
		}
		

        try {
            validationEmail( email, utilisateurTest );
        } catch ( Exception e ) {
            setErreur( CHAMP_EMAIL, e.getMessage() );
        }

        try {
            validationMotDePasse( motDePasse, utilisateurTest );
        } catch ( Exception e ) {
            setErreur( CHAMP_PASS, e.getMessage() );
        }

        return utilisateurTest;
    }

    private void validationEmail( String email, Utilisateur utilisateur ) throws Exception {
    	if(utilisateur.getEmail() != null) {
    		if ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" )) {
                throw new Exception( "Merci de saisir une adresse mail valide." );
            }
    	} else {
        		throw new Exception( "Adresse Mail inconnue." );
        }
    }

    private void validationMotDePasse( String motDePasse, Utilisateur utilisateur ) throws Exception {
        if ( motDePasse != null ) {
        	if(!motDePasse.contentEquals(utilisateur.getMotDePasse())) {
        		throw new Exception( "Mot de Passe incorrect." );
        	}
        } else {
        	{
        		throw new Exception( "Merci de saisir votre mot de passe." );
        	}
        }
    }

    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}
