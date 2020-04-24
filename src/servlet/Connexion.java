package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import encheres.bll.ArticleVenduManager;
import encheres.bll.CategorieManager;
import encheres.bll.EnchereManager;
import encheres.bo.ArticleVendu;
import encheres.bo.Categorie;
import encheres.bo.Enchere;
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
		if(request.getSession().getAttribute(ATT_SESSION_USER) == null) {
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
		
		if ( form.getErreurs().isEmpty() ) {
			request.getSession().setAttribute( ATT_SESSION_USER, utilisateur );
        } else {
        	request.getSession().setAttribute( ATT_SESSION_USER, null );
        }

        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_USER, utilisateur );

        if(request.getSession().getAttribute(ATT_SESSION_USER) == null) {
			this.getServletContext().getRequestDispatcher( CONNEXION_PAGE ).forward( request, response );
		}else {
			try {
				CategorieManager categorieManager = new CategorieManager();
				EnchereManager enchereManager = new EnchereManager();
				ArticleVenduManager articleVenduManager = new ArticleVenduManager();
				
				List<Enchere> listeEnchere = null;
				List<Categorie> listeCategorie = null;
				List<ArticleVendu> listeArticleVendu = null;
				
				listeCategorie = categorieManager.selectionnerToutesLesCategories();
				listeEnchere = enchereManager.selectionnerToutesLesEnchere();
				listeArticleVendu = articleVenduManager.selectionnerTousLesArticleVendu();
				
				request.setAttribute("listeCategorie", listeCategorie);
				request.setAttribute("listeEnchere", listeEnchere);
				request.setAttribute("listeArticleVendu", listeArticleVendu);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
//			this.getServletContext().getRequestDispatcher( MAIN_PAGE ).forward( request, response );
			response.sendRedirect(request.getContextPath() + "/Accueil");
		}
	}

}
