package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ServletRegister
 */
@WebServlet("/ServletRegister")
public class ServletRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
		testConnexion(response);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
private void testConnexion(HttpServletResponse response) {
	try {
		Context context=new InitialContext();
		//Rechercher la dataSource
		DataSource dataSource = 
				(DataSource)context.lookup("java:comp/env/jdbc/pool_cnx");

		//Demander une connexion. La méthode getConnection 
		//met la demande en attente 
		//tant qu'il n'y a pas de connexions disponibles dans le pool.
		Connection cnx = dataSource.getConnection();
		System.out.print("La connexion est "+ (cnx.isClosed()?"fermée":"ouverte")+".");
		//Libérer la connexion lorsque on en a plus besoin.
		cnx.close();//La connexion n'est pas fermée mais remise dans le pool. 
	} catch(NamingException | SQLException e) {
		e.printStackTrace();
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		System.out.print("Une erreur est survenue lors de l'utilisation "
				+ "de la base de données:" +e.getMessage());
		
	}
}
}
