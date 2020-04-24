package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import encheres.bll.UtilisateurManager;
import encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletAccountUpdate
 */
@WebServlet("/ServletAccountUpdate")
public class ServletAccountUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_SESSION_USER = "sessionUtilisateur";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletAccountUpdate() {
		super();
		// TODO Auto-generated constructor stub
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
		UtilisateurManager userManager = new UtilisateurManager();
		String pseudo = (String) request.getParameter("pseudo");
		String nom = (String) request.getParameter("nom");
		String prenom = (String) request.getParameter("prenom");
		String email = (String) request.getParameter("mail");
		String tel = (String) request.getParameter("tel");
		String rue = (String) request.getParameter("rue");
		String cp = (String) request.getParameter("cp");
		String ville = (String) request.getParameter("ville");
		String mdp = (String) request.getParameter("mdp");
		int idUser = Integer.parseInt(request.getParameter("idUser"));

		Utilisateur user = new Utilisateur(idUser, pseudo, nom, prenom, email, tel, rue, cp, ville, mdp, 0, false);

		userManager.modifierUtilisateur(user);

		request.getSession().setAttribute(ATT_SESSION_USER, user);

		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/accueil.jsp").forward(request, response);

	}
}
