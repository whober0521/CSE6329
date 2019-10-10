package facility_maintenance.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import facility_maintenance.data.*;
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
		String action = request.getParameter("action"), url="", username = request.getParameter("username");
		HttpSession session = request.getSession();
		
		session.setAttribute("username", username);
		session.removeAttribute("MAR");
		session.removeAttribute("errorMsgs");
		
		if (action.equalsIgnoreCase("report") ) {
			session.setAttribute("facilities", FacilitiesDAO.getNames());
			url="/report.jsp";
		}
		else if (action.equalsIgnoreCase("unassigned") ) {
			session.setAttribute("MARs", MARsDAO.getUnassigned());
			url="/MARsManager.jsp";
		}
		else if (action.equalsIgnoreCase("assigned") ) {
			session.setAttribute("types", FacilitiesDAO.getTypes());
			session.setAttribute("names", FacilitiesDAO.getNames());
			session.setAttribute("repairers", UsersDAO.getRepairers());
			url="/assigned.jsp";
		}
		else if (action.equalsIgnoreCase("MARManager") ) {
			session.setAttribute("MAR", MARsDAO.getMAR(request.getParameter("idx")));
			session.setAttribute("repairers", UsersDAO.getRepairers());
			url="/MARManager.jsp";
		}
		else if (action.equalsIgnoreCase("reserved") ) {
			url="/reserved.jsp";
		}
		else if (action.equalsIgnoreCase("cancel") ) {
			MARsDAO.delete(request.getParameter("idx"));
			url="/reserved.jsp";
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
			String reporter = request.getParameter("reporter");
			
			mar.setMAR("-1", "",
					request.getParameter("facility"),
					request.getParameter("description"), "",
					reporter, "", "", "", "", "", "");
			
			mar.validate(action, mar, errorMsgs);
			
			session.setAttribute("MAR", mar);

			if (!errorMsgs.getErrorMsg().equals("")) {
				// if error messages				
				session.setAttribute("username", reporter);
				session.setAttribute("errorMsgs", errorMsgs);
				session.setAttribute("facilities", FacilitiesDAO.getNames());
				url="/report.jsp";
			}
			else {
				// if no error messages
				MARsDAO.insert(mar);
				session.setAttribute("username", reporter);
				url="/user.jsp";
			}
		}
		else if (action.equalsIgnoreCase("assign") ) {
			mar.setMAR(
					request.getParameter("idx"), "", "", "",
					request.getParameter("urgency"), "", "", "",
					request.getParameter("repairer"), "", "", 
					request.getParameter("estimate"));
			
			MARsDAO.assign(mar);
			session.setAttribute("MARs", MARsDAO.getUnassigned());
			url="/MARsManager.jsp";
		}
		else if (action.equalsIgnoreCase("assigned") ) {
			mar.setMAR(
					request.getParameter("idx"),
					request.getParameter("facilitytype"),
					request.getParameter("facilityname"), "", "", "", "", "",
					request.getParameter("repairer"),
					request.getParameter("assigndate"),
					request.getParameter("assigntime"), "");
			session.setAttribute("username", request.getParameter("username"));
			
			session.setAttribute("MARs", MARsDAO.getAssigned(mar));
			url="/MARsManager.jsp";
		}
		else if (action.equalsIgnoreCase("reserved") ) {
			String username = request.getParameter("username");
			
			mar.setMAR("", "", "" , "", "", "", "", "", username,
					request.getParameter("assigndate"),
					request.getParameter("assigntime"), "");
			
			session.setAttribute("username", username);
			session.setAttribute("MARs", MARsDAO.getAssigned(mar));
			url="/MARsRepairer.jsp";
		}
		
//		else if (action.equalsIgnoreCase("search_r") ) {
//			mar.setMAR("", "", "", "", "",
//					request.getParameter("reportdate"),
//					request.getParameter("reporttime"),
//					request.getParameter("repairer"), "", "");
//			
//			mar.validate(action, mar, errorMsgs);
//
//			if (!errorMsgs.getErrorMsg().equals("")) {
//				// if error messages
//				session.setAttribute("MAR", mar);
//				session.setAttribute("errorMsgs", errorMsgs);
//				url="/MARAssignedSearch.jsp";
//			}
//			else {
//				// if no error messages
//				session.setAttribute("MARs", MARsDAO.getAssigned(mar));
//				url="/MARAssigned.jsp";
//			}
//		}

		getServletContext().getRequestDispatcher(url).forward(request, response);		
	}
}