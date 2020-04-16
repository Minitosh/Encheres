package encheres.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import encheres.bo.Utilisateur;
import encheres.forms.ConnexionForm;

@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public static final String ATT_USER         = "utilisateur";
    public static final String ATT_FORM         = "form";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String CONNEXION_PAGE = "/WEB-INF/jsp/connexion.jsp";
    public static final String MAIN_PAGE = "/WEB-INF/jsp/accueil.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connexion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if(session.getAttribute(ATT_SESSION_USER) == null) {
			this.getServletContext().getRequestDispatcher( CONNEXION_PAGE ).forward( request, response );
		}else {
			this.getServletContext().getRequestDispatcher( MAIN_PAGE ).forward( request, response );
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
		ConnexionForm form = new ConnexionForm();
		
		Utilisateur utilisateur = form.connecterUtilisateur(request);
		
		HttpSession session = request.getSession();
		
		if ( form.getErreurs().isEmpty() ) {
            session.setAttribute( ATT_SESSION_USER, utilisateur );
        } else {
            session.setAttribute( ATT_SESSION_USER, null );
        }

        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_USER, utilisateur );

        this.getServletContext().getRequestDispatcher( MAIN_PAGE ).forward( request, response );
	}

}
