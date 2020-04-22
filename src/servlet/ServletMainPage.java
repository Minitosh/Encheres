package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import encheres.bll.CategorieManager;
import encheres.bo.Categorie;

/**
 * Servlet implementation class ServletMainPage
 */
@WebServlet("/Accueil")
public class ServletMainPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String MAIN_PAGE = "/WEB-INF/jsp/accueil.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletMainPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Récupération des Catégories
		try {
			CategorieManager categorieManager = new CategorieManager();
			List<Categorie> listeCategorie = null;
			
			listeCategorie = categorieManager.selectionnerToutesLesCategories();
			request.setAttribute("listeCategorie", listeCategorie);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher( MAIN_PAGE ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(request.getAttribute("nom"));
		doGet(request, response);
	}

}
