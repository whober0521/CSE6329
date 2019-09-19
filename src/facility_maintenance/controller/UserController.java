package facility_maintenance.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			
			user.validate(user,errorMsgs);
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
//
//		else 
//		  if (action.equalsIgnoreCase("searchCompany") ) {
//			String companyname = request.getParameter("company_name");   
//			String companyID = request.getParameter("idcompany");
//			session.removeAttribute("errorMsgs");
//			company.setCompany(companyID,companyname,"","");
//			company.validateCompany(action,company, CerrorMsgs);
//
//			ArrayList<Company> companyInDB = new ArrayList<Company>();
//			if (CerrorMsgs.getErrorMsg().equals("")) {
//				if (!companyID.equals(""))
//					companyInDB=CompanyDAO.searchCompany(companyID);
//				else
//					companyInDB=CompanyDAO.searchCompanies(companyname);
//
//				session.setAttribute("COMPANIES", companyInDB);
//				session.removeAttribute("company");
//				url="/companySearchResults.jsp";
//			}
//			else {
//				session.setAttribute("company", company);
//				session.setAttribute("errorMsgs", CerrorMsgs);
//				url="/searchCompany.jsp";				
//			}
//		}
//		else { //action=listSpecificCompany
//			ArrayList<Company> companyInDB = new ArrayList<Company>();
//			Company selectedCompany = new Company();
//			if (request.getParameter("radioCompany")!=null) {
//				selectedCompanyIndex = Integer.parseInt(request.getParameter("radioCompany")) - 1;
//				companyInDB=CompanyDAO.listCompanies(); 
//				selectedCompany.setCompany(	companyInDB.get(selectedCompanyIndex).getIdcompany(), companyInDB.get(selectedCompanyIndex).getCompany_name(), 
//						companyInDB.get(selectedCompanyIndex).getPhone(), companyInDB.get(selectedCompanyIndex).getEmail());
//				session.setAttribute("COMPANIES", selectedCompany);
//				url="/ListSpecificCompany.jsp";					
//			}
//			else { // determine if Submit button was clicked without selecting a company
//				if (request.getParameter("ListSelectedCompanyButton")!=null) {
//					String errorMsgs =  "Please select a company";
//					session.setAttribute("errorMsgs",errorMsgs);
//					url="/listCompany.jsp";					
//				}
//				else { //view button was used instead of radio button
//					companyInDB=CompanyDAO.searchCompany(request.getParameter("id"));
//					selectedCompany.setCompany(	companyInDB.get(0).getIdcompany(), companyInDB.get(0).getCompany_name(), 
//							companyInDB.get(0).getPhone(), companyInDB.get(0).getEmail());
//					session.setAttribute("COMPANIES", selectedCompany);
//					url="/ListSpecificCompany.jsp";					
//				}
//			}
//		}
		
		getServletContext().getRequestDispatcher(url).forward(request, response);		
	}
}