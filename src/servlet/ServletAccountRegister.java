package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import org.apache.commons.lang3.tuple.ImmutablePair;

import encheres.BusinessException;
import encheres.bll.UtilisateurManager;
import encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletAccountRegister
 */
@WebServlet("/ServletAccountRegister")
public class ServletAccountRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UtilisateurManager userDAO;
	private String msg;
	private boolean isPseudoDuplicate;
	private boolean isEmailDuplicate;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletAccountRegister() {
		super();
		userDAO = new UtilisateurManager();
		isPseudoDuplicate = false;
		isEmailDuplicate = false;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nom = (String) request.getAttribute("nom");
		response.getWriter().append("nom get : ").append(nom);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			if (!checkPseudoEmailAlreadyExist(request.getParameter("pseudo"), request.getParameter("mail"), response)) {

				addUser(request, response);

				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp");

				JOptionPane.showMessageDialog(null, "compte créé");

				rd.forward(request, response);
			} else {
				// TODO retour jsp enregistrement compte en ressetant les paramètre
				request.setAttribute("isPseudoDuplicate", isPseudoDuplicate);
				request.setAttribute("isEmailDuplicate", isEmailDuplicate);

				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");

				JOptionPane.showMessageDialog(null, msg);

				rd.forward(request, response);
			}
		} catch (BusinessException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp");

			JOptionPane.showMessageDialog(null, "compte créé");

			rd.forward(request, response);
		}

	}

	private boolean checkPseudoEmailAlreadyExist(String pseudo, String mail, HttpServletResponse response)
			throws BusinessException {
		isEmailDuplicate = false;
		isPseudoDuplicate = false;
		Boolean isPseudoExist = false;
		ArrayList<ImmutablePair<String, String>> list = userDAO.selectionnerTousLesEmailsPseudo();
		for (ImmutablePair<String, String> p : list) {
			if (p.left.equals(pseudo)) {
				msg = "Ce pseudo est déjà utilisé";
				isPseudoExist = true;
				isPseudoDuplicate = true;
			}
			if (p.right.equals(mail)) {
				msg = "Un compte est déjà associé a cet email";
				isEmailDuplicate = true;
				return true;

			}
		}

		return isPseudoExist;
	}

	private void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String pseudo = (String) request.getParameter("pseudo");
		String nom = (String) request.getParameter("nom");
		String prenom = (String) request.getParameter("prenom");
		String email = (String) request.getParameter("mail");
		String tel = (String) request.getParameter("tel");
		String rue = (String) request.getParameter("rue");
		String cp = (String) request.getParameter("cp");
		String ville = (String) request.getParameter("ville");
		String mdp = (String) request.getParameter("mdp");

		Utilisateur user = new Utilisateur(pseudo, nom, prenom, email, tel, rue, cp, ville, mdp, 0, false);

		try {
			userDAO.ajouterUtilisateur(user.getPseudo(), user.getNom(), user.getPrenom(), user.getEmail(),
					user.getTelephone(), user.getRue(), user.getCodePostal(), user.getVille(), user.getMotDePasse(),
					user.getCredit(), user.getAdministrateur());
		} catch (BusinessException e) {

			e.printStackTrace();
			msg = "Echec lors de la création du compte";
		}

		response.getWriter().append(msg);

	}

}
