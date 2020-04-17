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
import encheres.bo.Utilisateur;
import encheres.dal.DAOFactory;
import encheres.dal.UtilisateurDAO;

/**
 * Servlet implementation class ServletAccountRegister
 */
@WebServlet("/ServletAccountRegister")
public class ServletAccountRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UtilisateurDAO userDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAccountRegister() {
        super();
        userDAO = DAOFactory.getUtilisateurDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = (String) request.getAttribute("nom");
		response.getWriter().append("nom get : ").append(nom);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg ="";
		try {
			if(!checkPseudoEmailAlreadyExist(request.getParameter("pseudo"), request.getParameter("mail"), response,msg)) {
			
			addUser(request,response);
			
			response.getWriter().append("compte créé");
			}else {
				//TODO retour jsp enregistrement compte en ressetant les paramètre
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
				JOptionPane.showMessageDialog(null, msg);
				
				rd.forward(request, response);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			// JSP une erreur est survenue
		}
		
	}

	private boolean checkPseudoEmailAlreadyExist(String pseudo, String mail, HttpServletResponse response,String msg) throws BusinessException {
		Boolean isPseudoExist =false;
		ArrayList<ImmutablePair<String,String>> list = userDAO.getAllPseudoEmail();
		for(ImmutablePair<String,String> p : list) {
			if(p.left.equals(pseudo)) {
				msg="Ce pseudo est déjà utilisé";
				isPseudoExist=true;
			}
			if(p.right.equals(mail)) {
				msg="Un compte est déjà associé a cet email";
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
		String msg = "Compte créé avec succès";
		
		Utilisateur user = new Utilisateur(pseudo,nom,prenom,email,tel,rue,cp,ville,mdp,0,false);

		
		try {
			userDAO.insert(user);
		} catch (BusinessException e) {
			
			e.printStackTrace();
			msg = "Echec lors de la création du compte";
		}
		
		response.getWriter().append(msg);
		
	}

}
