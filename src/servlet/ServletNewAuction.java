package servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import encheres.BusinessException;
import encheres.Exceptions.AddEnchereException;
import encheres.bll.ArticleVenduManager;
import encheres.bll.EnchereManager;
import encheres.bll.UtilisateurManager;
import encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletNewAuction
 */
@WebServlet("/ServletNewAuction")
public class ServletNewAuction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EnchereManager enchereManager;
	private UtilisateurManager userManager;
	private ArticleVenduManager articleManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletNewAuction() {
		super();
		enchereManager = new EnchereManager();
		userManager = new UtilisateurManager();
		articleManager = new ArticleVenduManager();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utilisateur user = ((Utilisateur) request.getSession().getAttribute("sessionUtilisateur"));
		int idArticle = Integer.parseInt(request.getParameter("idArticle"));

		int montant = Integer.parseInt(request.getParameter("montant"));

		if (user.getCredit() < montant) {

			RequestDispatcher rd = request.getRequestDispatcher(request.getContextPath());

			JOptionPane.showMessageDialog(null, "Vous n'avez pas un solde suffisant");

			rd.forward(request, response);

		} else {

			try {
				enchereManager.ajouterEnchere(idArticle, user.getNoUtilisateur(),
						new Date(new java.util.Date().getTime()), montant);
				userManager.decrediter(montant, user.getNoUtilisateur());
				articleManager.majPrix(idArticle, montant);
			} catch (BusinessException e) {
				e.printStackTrace();
			} catch (AddEnchereException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				e.printStackTrace();
			}
		}
	}

}
