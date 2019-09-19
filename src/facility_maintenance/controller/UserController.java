package facility_maintenance.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import company_management.controller.ArrayList;
import company_management.data.CompanyDAO;
import company_management.model.Company;
import facility_maintenance.model.UserErrorMsgs;
import facility_maintenance.model.User;
import facility_maintenance.data.UsersDAO;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public UserController() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		session.removeAttribute("user");
		session.removeAttribute("errorMsgs");
		
		getServletContext().getRequestDispatcher("/signup.jsp").forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action"), url="";
		HttpSession session = request.getSession();
		User user = new User();
		UserErrorMsgs errorMsgs = new UserErrorMsgs();
//		int selectedCompanyIndex;
		session.removeAttribute("errorMsgs");
		
		if (action.equalsIgnoreCase("register") ) {
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
			
			user.validate(action, user, errorMsgs);
			session.setAttribute("user", user);

			if (!errorMsgs.getErrorMsg().equals("")) {
				// if error messages				
				session.setAttribute("errorMsgs", errorMsgs);
				url="/signup.jsp";
			}
			else {
				// if no error messages
				UsersDAO.insert(user);
				url="/index.jsp";
			}
		}
		else if (action.equalsIgnoreCase("login") ) {
			String username = request.getParameter("username");
			user.setUser(username, request.getParameter("password"),
					"","","","","","","","","");
			user.validate(action, user, errorMsgs);
			
			if (errorMsgs.getErrorMsg().equals("")) {
				user = UsersDAO.getUser(username);
				session.setAttribute("user", user);
				
				switch(user.getRole()) {
				  case "U":
					  url="/companySearchResults.jsp";
				    break;
				  case "F":
					  url="/companySearchResults.jsp";
				    break;
				  case "A":
					  url="/companySearchResults.jsp";
				    break;
				  case "R":
					  url="/companySearchResults.jsp";
				    break;
				  default:			
				}
			}
			else {
				session.setAttribute("user", user);
				session.setAttribute("errorMsgs", errorMsgs);
				url="/index.jsp";				
			}
		}

		getServletContext().getRequestDispatcher(url).forward(request, response);		
	}
}