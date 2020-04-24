package servlet;

import java.io.IOException;
import java.util.ArrayList;
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
		
		try {
			CategorieManager categorieManager = new CategorieManager();
			EnchereManager enchereManager = new EnchereManager();
			ArticleVenduManager articleVenduManager = new ArticleVenduManager();
			
			List<Enchere> listeEnchere = new ArrayList<Enchere>();
			List<Categorie> listeCategorie = new ArrayList<Categorie>();
			List<ArticleVendu> listeArticleVendu = new ArrayList<ArticleVendu>();
			
			if(request.getParameter("Categorie") != null && request.getParameter("Nom") != null && !request.getParameter("Categorie").equals("") && !request.getParameter("Nom").equals("")) {
				Categorie categorie = categorieManager.selectionnerCategorieParLibelle(request.getParameter("Categorie"));
				listeArticleVendu = articleVenduManager.selectionnerTousLesArticleVenduParNoCategorie(categorie.getNoCategorie());
				for(ArticleVendu a : listeArticleVendu) {
					if(a.getNomArticle().contains(request.getParameter("Nom"))) {
						listeEnchere.add(enchereManager.selectionnerEnchereParNoArticle(a.getNoArticle()));
					}
				}
			}else {
				if(request.getParameter("Categorie") != null && !request.getParameter("Categorie").equals("")) {
					Categorie categorie = categorieManager.selectionnerCategorieParLibelle(request.getParameter("Categorie"));
					listeArticleVendu = articleVenduManager.selectionnerTousLesArticleVenduParNoCategorie(categorie.getNoCategorie());
					for(ArticleVendu a : listeArticleVendu) {
						listeEnchere.add(enchereManager.selectionnerEnchereParNoArticle(a.getNoArticle()));
					}
				}
				if(request.getParameter("Nom") != null && !request.getParameter("Nom").equals("")) {
					
					listeArticleVendu = articleVenduManager.selectionnerTousLesArticleVendu();
					for(ArticleVendu a : listeArticleVendu) {
						if(a.getNomArticle().contains(request.getParameter("Nom"))) {
							listeEnchere.add(enchereManager.selectionnerEnchereParNoArticle(a.getNoArticle()));
						}
					}
				}
			}
			
			if(request.getParameter("Categorie") == null && request.getParameter("Nom") == null) {

				listeEnchere = enchereManager.selectionnerToutesLesEnchere();
				listeArticleVendu = articleVenduManager.selectionnerTousLesArticleVendu();
			}else {
				if(request.getParameter("Categorie").equals("") && request.getParameter("Nom").equals("")) {

					listeEnchere = enchereManager.selectionnerToutesLesEnchere();
					listeArticleVendu = articleVenduManager.selectionnerTousLesArticleVendu();
				}
			}
			
			if(request.getParameter("isNullNom") != null) {
				listeEnchere = enchereManager.selectionnerToutesLesEnchere();
				listeArticleVendu = articleVenduManager.selectionnerTousLesArticleVendu();
			}
			
			if(request.getParameter("isNullCategorie") != null) {
				listeEnchere = enchereManager.selectionnerToutesLesEnchere();
				listeArticleVendu = articleVenduManager.selectionnerTousLesArticleVendu();
			}
			
			listeCategorie = categorieManager.selectionnerToutesLesCategories();
			
			request.setAttribute("listeCategorie", listeCategorie);
			request.setAttribute("listeArticleVendu", listeArticleVendu);
			request.setAttribute("listeEnchere", listeEnchere);
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
		
		doGet(request, response);
	}

}
