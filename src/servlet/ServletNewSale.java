package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import encheres.BusinessException;
import encheres.Exceptions.AddArticleVenduException;
import encheres.Exceptions.AddRetraitException;
import encheres.bll.ArticleVenduManager;
import encheres.bll.RetraitManager;
import encheres.bo.ArticleVendu;
import encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletNewSale
 */
@WebServlet("/NouvelleVenteValidation")
public class ServletNewSale extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArticleVenduManager articleManager;
	RetraitManager retraitManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletNewSale() {
		super();
		articleManager = new ArticleVenduManager();
		retraitManager = new RetraitManager();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		addArticleVendu(request);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/acceuil.jsp");

		JOptionPane.showMessageDialog(null, "Enchère créé");

		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		addArticleVendu(request);

		JOptionPane.showMessageDialog(null, "Enchère créé");

		response.sendRedirect(request.getContextPath() + "/Accueil");
	}

	private void addArticleVendu(HttpServletRequest request) {
		String nom = request.getParameter("name");
		String descr = request.getParameter("description");
		int prix = Integer.parseInt(request.getParameter("prix"));
		int idUser = ((Utilisateur) request.getSession().getAttribute("sessionUtilisateur")).getNoUtilisateur();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedDebut = new Date();
		Date parsedFin = new Date();
		try {
			parsedDebut = format.parse(request.getParameter("dateDebut"));
			parsedFin = format.parse(request.getParameter("dateFin"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		java.sql.Date debut = new java.sql.Date(parsedDebut.getTime());
		java.sql.Date fin = new java.sql.Date(parsedFin.getTime());

		int idCat = Integer.parseInt(request.getParameter("categorie"));

		String rue = request.getParameter("rue");
		String cp = request.getParameter("cp");
		String ville = request.getParameter("ville");

		ArticleVendu a;
		try {
			a = articleManager.ajouterArticleVendu(nom, descr, debut, fin, prix, prix, idUser, idCat);
			retraitManager.ajouterRetrait(a.getNoArticle(), rue, cp, ville);
		} catch (BusinessException e) {
			JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout veuillez contacter l'administrateur");
			e.printStackTrace();
		} catch (AddArticleVenduException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} catch (AddRetraitException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

}
