package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import encheres.BusinessException;
import encheres.bll.ArticleVenduManager;
import encheres.bll.EnchereManager;
import encheres.bll.UtilisateurManager;
import encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletDeleteAccount
 */
@WebServlet("/Suppression")
public class ServletDeleteAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ERROR_ENCHERE = "Impossible de supprimer votre compte une enchère est en cours ou le delais de 7 jours n'est pas respecté";
	private static final String ERROR_VENTE = "Impossible de supprimer votre compte une vente est en cours ou le delais de 7 jours n'est pas respecté";
	private static final String NO_ERROR = "Votre compte a bien été supprimé";
	public static final String ATT_SESSION_USER = "sessionUtilisateur";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletDeleteAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idUser = ((Utilisateur) request.getSession().getAttribute("sessionUtilisateur")).getNoUtilisateur();
		EnchereManager enchere = new EnchereManager();
		ArticleVenduManager article = new ArticleVenduManager();
		UtilisateurManager user = new UtilisateurManager();

		try {
			if (!enchere.selectionnerEnchereParNoUtilisateur(idUser).isEmpty()) {

				JOptionPane.showMessageDialog(null, ERROR_ENCHERE);

				response.sendRedirect(request.getContextPath() + "/Accueil");

			} else {
				if (!article.selectionnerTousLesArticleVenduParNoUtilisateur(idUser).isEmpty()) {

					JOptionPane.showMessageDialog(null, ERROR_VENTE);

					response.sendRedirect(request.getContextPath() + "/Accueil");
				} else {
					user.supprimerUtilisateur(idUser);

					JOptionPane.showMessageDialog(null, NO_ERROR);
					request.getSession().setAttribute(ATT_SESSION_USER, null);

					response.sendRedirect(request.getContextPath() + "/Accueil");

				}
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
