package facility_maintenance.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import facility_maintenance.data.FacilitiesDAO;
import facility_maintenance.data.UsersDAO;
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
		Facility facility = new Facility();
		
		session.setAttribute("username", username);
		
		if (action.equalsIgnoreCase("add") ) {
			session.setAttribute("types", FacilitiesDAO.getTypes(""));
			session.setAttribute("intervals", facility.getIntervals(""));
			session.setAttribute("durations", facility.getDurations(""));
			session.setAttribute("venues", facility.getVenues(""));
			
			url="/facility.jsp";
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
								request.getParameter("interval"),
								request.getParameter("duration"),
								request.getParameter("venue"),
								request.getParameter("number"));
			
			facility.validate(action, facility, errorMsgs);
			
			session.setAttribute("username", username);

			if (!errorMsgs.getErrorMsg().equals("")) {
				// if error messages
				session.setAttribute("types", FacilitiesDAO.getTypes(facility.getMaster()));
				session.setAttribute("intervals", facility.getIntervals(facility.getInterval()));
				session.setAttribute("durations", facility.getDurations(facility.getDuration()));
				session.setAttribute("venues", facility.getVenues(facility.getVenue()));
				
				session.setAttribute("facility", facility);
				session.setAttribute("errorMsgs", errorMsgs);
				url="/facility.jsp";
			}
			else {
				// if no error messages
				FacilitiesDAO.insert(facility);
				
				session.setAttribute("types", FacilitiesDAO.getTypes(""));
				session.setAttribute("intervals", facility.getIntervals(""));
				session.setAttribute("durations", facility.getDurations(""));
				session.setAttribute("venues", facility.getVenues(""));
				
				url="/facility.jsp";
			}
		}
		
		getServletContext().getRequestDispatcher(url).forward(request, response);	
	}
}