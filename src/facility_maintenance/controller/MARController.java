package facility_maintenance.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import facility_maintenance.data.UsersDAO;
import facility_maintenance.model.MAR;
import facility_maintenance.model.MARErrorMsgs;

/**
 * Servlet implementation class MARController
 */
@WebServlet("/MARController")
public class MARController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MARController() {
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
		String action = request.getParameter("action"), url="";
		HttpSession session = request.getSession();
		MARErrorMsgs errorMsgs = new MARErrorMsgs();
		MAR mar = new MAR();
		
		session.removeAttribute("errorMsgs");
		
		if (action.equalsIgnoreCase("report") ) {
			mar.setMAR(-1,
					request.getParameter("facility"),
					request.getParameter("urgency"),
					request.getParameter("description"));
			
			mar.validate(action, mar, errorMsgs);
			
			session.setAttribute("MAR", mar);

			if (!errorMsgs.getErrorMsg().equals("")) {
				// if error messages				
				session.setAttribute("errorMsgs", errorMsgs);
				url="/report.jsp";
			}
			else {
				// if no error messages
//				UsersDAO.insert(user);
//				url="/index.jsp";
			}
		}

		getServletContext().getRequestDispatcher(url).forward(request, response);		
	}
}