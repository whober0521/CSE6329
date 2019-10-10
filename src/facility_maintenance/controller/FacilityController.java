package facility_maintenance.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import facility_maintenance.data.FacilitiesDAO;
import facility_maintenance.model.Facility;
import facility_maintenance.model.FacilityErrorMsgs;

/**
 * Servlet implementation class FacilityController
 */
@WebServlet("/FacilityController")
public class FacilityController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FacilityController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action"), url="", username = request.getParameter("username");
		HttpSession session = request.getSession();
		
		session.setAttribute("username", username);
		
		if (action.equalsIgnoreCase("add") ) {
			session.setAttribute("types", FacilitiesDAO.getTypes());
			
			url="/facility.jsp";
		}
		else if (action.equalsIgnoreCase("search") ) {
			session.setAttribute("names", FacilitiesDAO.getNames());
			
			url="/available.jsp";
		}

		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action"), url="", username = request.getParameter("username");
		HttpSession session = request.getSession();
		FacilityErrorMsgs errorMsgs = new FacilityErrorMsgs();
		Facility facility = new Facility();

		session.removeAttribute("errorMsgs");
		
		if (action.equalsIgnoreCase("add") ) {
			facility.setFacility(request.getParameter("master"), -1,
								Integer.parseInt(request.getParameter("interval")),
								Integer.parseInt(request.getParameter("duration")),
								request.getParameter("venue"),
								request.getParameter("number"));
			
			facility.validate(action, facility, errorMsgs);
			
			session.setAttribute("username", username);
			session.setAttribute("types", FacilitiesDAO.getTypes());

			if (!errorMsgs.getErrorMsg().equals("")) {
				// if error messages
				session.setAttribute("facility", facility);
				session.setAttribute("errorMsgs", errorMsgs);
				url="/facility.jsp";
			}
			else {
				// if no error messages
				FacilitiesDAO.insert(facility);
				url="/facility.jsp";
			}
		}
		
		getServletContext().getRequestDispatcher(url).forward(request, response);	
	}

}
