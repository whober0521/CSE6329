package facility_maintenance.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import facility_maintenance.data.MARsDAO;
import facility_maintenance.model.MAR;
import facility_maintenance.model.MARErrorMsgs;
import facility_maintenance.model.User;

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
		String action = request.getParameter("action"), url="";
		HttpSession session = request.getSession();
		
		session.removeAttribute("MAR");
		session.removeAttribute("errorMsgs");
		
		if (action.equalsIgnoreCase("report") ) {
			url="/report.jsp";
		}
		else if (action.equalsIgnoreCase("search_fm") ) {
			url="/MARUnassigned.jsp";
		}
		
		getServletContext().getRequestDispatcher(url).forward(request, response);
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
			User user = new User();
			session.setAttribute("user", user);

			mar.setMAR("-1", "",
					request.getParameter("facilityname"),
					request.getParameter("urgency"),
					request.getParameter("description"),
					user.getUsername(), "", "", "");
			
			mar.validate(action, mar, errorMsgs);
			
			session.setAttribute("MAR", mar);

			if (!errorMsgs.getErrorMsg().equals("")) {
				// if error messages				
				session.setAttribute("errorMsgs", errorMsgs);
				url="/report.jsp";
			}
			else {
				// if no error messages
				MARsDAO.insert(mar);
				url="/user.jsp";
			}
		}
		else if (action.equalsIgnoreCase("search_fm") ) {
			mar.setMAR(
					request.getParameter("idx"),
					request.getParameter("facilitytype"),
					request.getParameter("facilityname"), "", "", "",
					request.getParameter("reportdate"),
					request.getParameter("reporttime"),
					request.getParameter("repairer"));
			
			mar.validate(action, mar, errorMsgs);

			if (!errorMsgs.getErrorMsg().equals("")) {
				// if error messages
				session.setAttribute("MAR", mar);
				session.setAttribute("errorMsgs", errorMsgs);
				url="/MARUnassignedSearch.jsp";
			}
			else {
				// if no error messages
				session.setAttribute("MARs", MARsDAO.getUnassigned(mar));
				url="/MARUnassigned.jsp";
			}
		}
		else if (action.equalsIgnoreCase("assign") ) {
			mar.setMAR(
					request.getParameter("idx"), "", "", "", "", "", "", "",
					request.getParameter("repairer"));
			
			mar.validate(action, mar, errorMsgs);

			if (!errorMsgs.getErrorMsg().equals("")) {
				// if error messages
				session.setAttribute("MARs", MARsDAO.getUnassigned(mar));
				session.setAttribute("errorMsgs", errorMsgs);
				url="/MARUnassigned.jsp";
			}
			else {
				// if no error messages
				MARsDAO.assign(mar);
				url="/MARUnassignedSearch.jsp";
			}
		}

		getServletContext().getRequestDispatcher(url).forward(request, response);		
	}
}