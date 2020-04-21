package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import encheres.BusinessException;
import encheres.bll.CategorieManager;
import encheres.bo.Categorie;
import encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletPageNewSale
 */
@WebServlet("/NouvelleVente")
public class ServletPageNewSale extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static CategorieManager categorieManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletPageNewSale() {
		super();
		categorieManager = new CategorieManager();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/nouvelleVente.jsp");
		try {
			request.setAttribute("categories", getAllCategories());
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		if (!request.getSession().getAttribute("sessionUtilisateur").equals("")) {
			request.setAttribute("rue",
					((Utilisateur) request.getSession().getAttribute("sessionUtilisateur")).getRue());
			request.setAttribute("cp",
					((Utilisateur) request.getSession().getAttribute("sessionUtilisateur")).getCodePostal());
			request.setAttribute("ville",
					((Utilisateur) request.getSession().getAttribute("sessionUtilisateur")).getVille());
		}
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private ArrayList<Categorie> getAllCategories() throws BusinessException {
		ArrayList<Categorie> listCat = new ArrayList<Categorie>();

		listCat.addAll(categorieManager.selectionnerToutesLesCategories());
		return listCat;
	}
}
