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
		MAR mar = new MAR();
		
		session.setAttribute("username", username);
		session.removeAttribute("MAR");
		session.removeAttribute("errorMsgs");
		
		if (action.equalsIgnoreCase("report") ) {
			session.setAttribute("facilities", FacilitiesDAO.getNames(""));
			url="/report.jsp";
		}
		else if (action.equalsIgnoreCase("unassigned") ) {
			session.setAttribute("MARs", MARsDAO.getUnassigned());
			url="/MARsManager.jsp";
		}
		else if (action.equalsIgnoreCase("assigned") ) {
			session.setAttribute("types", FacilitiesDAO.getTypes("MR"));
			session.setAttribute("names", FacilitiesDAO.getNames(""));
			session.setAttribute("repairers", UsersDAO.getRepairers(""));
			session.setAttribute("today", mar.getDate());
			session.setAttribute("now", mar.getTime(""));
			
			url="/assigned.jsp";
		}
		else if (action.equalsIgnoreCase("available") ) {
			session.setAttribute("names", FacilitiesDAO.getNames("MR1"));
			session.setAttribute("today", mar.getDate());
			session.setAttribute("now", mar.getTime(""));
			
			url="/available.jsp";
		}
		else if (action.equalsIgnoreCase("MARManager") ) {
			mar = MARsDAO.getMAR(request.getParameter("idx"));
			
			session.setAttribute("MAR", mar);
			session.setAttribute("urgencies", mar.getUrgencies(mar.getUrgency()));
			session.setAttribute("repairers", UsersDAO.getRepairers(mar.getRepairer()));
			session.setAttribute("estimates", mar.getEstimates(mar.getEstimate()));
			
			url="/MARManager.jsp";
		}
		else if (action.equalsIgnoreCase("reserved") ) {
			session.setAttribute("today", mar.getDate());
			session.setAttribute("now", mar.getTime(""));
			
			url="/reserved.jsp";
		}
		else if (action.equalsIgnoreCase("request") ) {
			session.setAttribute("names", FacilitiesDAO.getNames(""));
			session.setAttribute("today", mar.getDate());
			session.setAttribute("now", mar.getTime(""));
			
			url="/request.jsp";
		}
		else if (action.equalsIgnoreCase("reserve") ) {
			MARsDAO.reserve(
					request.getParameter("idx"),
					request.getParameter("repairdate"),
					request.getParameter("starttime"),
					request.getParameter("endtime"));
			
			url="/repairer.jsp";
		}
		else if (action.equalsIgnoreCase("cancel") ) {
			MARsDAO.delete(request.getParameter("idx"));
			
			session.setAttribute("today", mar.getDate());
			session.setAttribute("now", mar.getTime(""));
			
			url="/reserved.jsp";
		}
		else {
			//"MARRepairer"
			mar = MARsDAO.getMAR(request.getParameter("idx"));
			
			session.setAttribute("MAR", mar);
			session.setAttribute("estimates", mar.getEstimates(mar.getEstimate()));
			
			url="/MARRepairer.jsp";
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
					reporter, "", "", "", "", "", "", "", "", "");
			
			mar.validate(action, mar, errorMsgs);
			
			session.setAttribute("MAR", mar);

			if (!errorMsgs.getErrorMsg().equals("")) {
				// if error messages				
				session.setAttribute("username", reporter);
				session.setAttribute("errorMsgs", errorMsgs);
				session.setAttribute("facilities", FacilitiesDAO.getNames(mar.getFacilityname()));
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
			MAR old = MARsDAO.getMAR(request.getParameter("idx"));
			mar.setMAR(
					old.getIdx(), 
					old.getFacilitytype(), 
					old.getFacilityname(),
					old.getDescription(),
					request.getParameter("urgency"),
					old.getReporter(),
					old.getReportdate(), "",
					request.getParameter("repairer"),
					old.getAssigndate(), "",
					request.getParameter("estimate"), "", "", "");
			
			mar.validate(action, mar, errorMsgs);
			
			session.setAttribute("MAR", mar);
			session.setAttribute("username", request.getParameter("username"));

			if (!errorMsgs.getErrorMsg().equals("")) {
				// if error messages
				session.setAttribute("errorMsgs", errorMsgs);
				session.setAttribute("urgencies", mar.getUrgencies(mar.getUrgency()));
				session.setAttribute("repairers", UsersDAO.getRepairers(mar.getRepairer()));
				session.setAttribute("estimates", mar.getEstimates(mar.getEstimate()));
				
				url="/MARManager.jsp";
			}
			else {
				// if no error messages
				MARsDAO.assign(mar);

				url="/manager.jsp";	  
			}
		}
		else if (action.equalsIgnoreCase("assigned") ) {
			mar.setMAR(
					request.getParameter("idx"),
					request.getParameter("facilitytype"),
					request.getParameter("facilityname"), "", "", "", "", "",
					request.getParameter("repairer"),
					request.getParameter("assigndate"),
					request.getParameter("assigntime"), "", "", "", "");
			
			session.setAttribute("username", request.getParameter("username"));
			
			session.setAttribute("MARs", MARsDAO.getAssigned(mar));
			url="/MARsManager.jsp";
		}
		else if (action.equalsIgnoreCase("available") ) {
			mar.setMAR("", "", 
					request.getParameter("facilityname"),
					"", "", "", "", "", "", "", "", "",
					request.getParameter("repairdate"),
					request.getParameter("starttime"), "");
			
			session.setAttribute("username", request.getParameter("username"));
			session.setAttribute("facility", FacilitiesDAO.getDetail(mar.getFacilityname()));
			session.setAttribute("MARs", MARsDAO.getDateTime(mar.getFacilityname(), mar.getRepairdate(), mar.getStarttime()));
			
			url="/availables.jsp";	  
		}
		else if (action.equalsIgnoreCase("reserved") ) {
			String username = request.getParameter("username");
			
			mar.setMAR("", "", "" , "", "", "", "", "", username,
					request.getParameter("assigndate"),
					request.getParameter("assigntime"), "", "", "", "");
			
			session.setAttribute("username", username);
			session.setAttribute("MARs", MARsDAO.getAssigned(mar));
			url="/MARsRepairer.jsp";
		}
		else if (action.equalsIgnoreCase("estimate") ) {
			mar.setMAR(request.getParameter("idx"), "", "" , "", "", "", "", "", "", "", "", 
					request.getParameter("estimate"), "", "", "");
			
			MARsDAO.estimate(mar);
			
			mar = MARsDAO.getMAR(request.getParameter("idx"));
			
			session.setAttribute("MAR", mar);
			session.setAttribute("estimates", mar.getEstimates(mar.getEstimate()));
			session.setAttribute("username", request.getParameter("username"));
			
			url="/MARRepairer.jsp";
		}
		else {
			//"request"
			String date = request.getParameter("repairdate");
			
			mar.setMAR("", "", 
					request.getParameter("facilityname"),
					"", "", "", "", "",
					request.getParameter("repairer"), "", "", "",
					request.getParameter("repairdate"),
					request.getParameter("starttime"), "");
			
			mar.validate(action, mar, errorMsgs);

			session.setAttribute("username", mar.getRepairer());

			if (!errorMsgs.getErrorMsg().equals("")) {
				// if error messages
				session.setAttribute("errorMsgs", errorMsgs);
				session.setAttribute("MAR", mar);
				session.setAttribute("names", FacilitiesDAO.getNames(mar.getFacilityname()));
				session.setAttribute("today", date);
				session.setAttribute("now", mar.getTime(mar.getStarttime()));
				
				url="/request.jsp";
			}
			else {
				// if no error messages
				session.setAttribute("facility", mar.getFacilityname());
				session.setAttribute("MARs", MARsDAO.getDateTime(mar.getFacilityname(), mar.getRepairdate(), mar.getStarttime()));
	
				url="/reserve.jsp";	  
			}
		}
		
		getServletContext().getRequestDispatcher(url).forward(request, response);		
	}
}