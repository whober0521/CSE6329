package facility_maintenance.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import facility_maintenance.data.UsersDAO;
import facility_maintenance.model.*;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String action = request.getParameter("action"), url="";
		User user = new User();
		
		user.setUser(
				request.getParameter("username"),
				request.getParameter("pwd"),
				request.getParameter("role"),
				request.getParameter("utaid"),
				request.getParameter("fname"),
				request.getParameter("lname"),
				request.getParameter("email"),
				request.getParameter("phone"),
				request.getParameter("address"),
				request.getParameter("city"),
				request.getParameter("state"));  
		
		//	company.validateCompany(action,company,CerrorMsgs);
		
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		
		UsersDAO.insert(user);
		
		getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);	
	}

}
