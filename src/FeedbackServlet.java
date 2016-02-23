import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.NullPointerException;


/**
 * Servlet implementation class persistence
 */
@WebServlet("/FeedbackServlet")
public class FeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedbackServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		System.out.println("We are here in FeedbackServlet");
		
		
		String feedback = request.getParameter("feedback");
		System.out.println("at the beginning of FeedbackServlet, feedback = " + feedback);
		System.out.println("feedback from the jsp is " + feedback);
		String sql;
		int rowCount;
		
		
		
		HttpSession session = request.getSession();
		
		session.setAttribute("FEEDBACK", feedback);
		String yes = "yes";
		session.setAttribute("FB", yes);
		String email = (String)session.getAttribute("EMAIL");
		
		// Make connection
				// open MySQL db
				//System.out.println("right before myDB");
				LocalMySQL myDB = new LocalMySQL();
				//System.out.println("myDB = " + myDB);
				if (!myDB.isAvailable()) {
					System.out.println("LoginServlet line 67  failed to get DB");
					return;
				}
				
				try 
				{
					//System.out.println("At beginning of first try statement");
					myDB.executeUpdate("use customer");
					sql = "update clients SET feedback =  '" + feedback + "' where email like '" + email + "'";		
					rowCount = myDB.executeUpdate(sql);
			
					
					
					sql = "select * from clients where email like '" + email + "'";
					ResultSet result2 = myDB.executeQuery(sql);
	
					while (result2.next()) {
						System.out.println("Feedback put in the table by FeedbackServlet:");
						String a = result2.getString("email");
						System.out.println("email is " + a);
						String b = result2.getString("feedback");
						System.out.println("feedback is " + b);
					}
					
					
					
					
					
				}
				catch (SQLException ex) 
				{
					System.out.println("In the FeedbackServelet SQLException catch block");
					myDB.printTrace(ex);
				}
				
				
				String ZEROOPEN = (String)session.getAttribute("ZEROOPEN");
				String ONEOPEN = (String)session.getAttribute("ONEOPEN");
				String TWOOPEN = (String)session.getAttribute("TWOOPEN");
				String THREEOPEN = (String)session.getAttribute("THREEOPEN");
				System.out.println("ZEROOPEN = " + ZEROOPEN);
				System.out.println("ONEOPEN = " + ONEOPEN);
				System.out.println("TWOOPEN = " + TWOOPEN);
				System.out.println("THREEOPEN = " + THREEOPEN);
				
				if (ZEROOPEN.equals(yes))
				{ 
				String url = "/havezeroopenFB.jsp";
				System.out.println("FeedbackServlet.   Re-directing to havezeroopenFB.jsp");
				getServletContext().getRequestDispatcher(url).forward(request, response);
				}
				
				else if (ONEOPEN.equals(yes))
				{ 
				String url = "/haveoneopenFB.jsp";
				System.out.println("FeedbackServlet.   Re-directing to haveoneopenFB.jsp");
				getServletContext().getRequestDispatcher(url).forward(request, response);
				}
				
				else if (TWOOPEN.equals(yes))
				{ 
				String url = "/havetwoopenFB.jsp";
				System.out.println("FeedbackServlet.   Re-directing to havetwoopenFB.jsp");
				getServletContext().getRequestDispatcher(url).forward(request, response);
				}
				
				else if (THREEOPEN.equals(yes))
				{ 
				String url = "/havethreeopenFB.jsp";
				System.out.println("FeedbackServlet.   Re-directing to havethreeopenFB.jsp");
				getServletContext().getRequestDispatcher(url).forward(request, response);
				}
				
				else
				{
					System.out.println("There is a problem with the NUMBEROPEN attributes");
				}
				
		
}	//end of doGet
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside of FeedbackServlet doPost block.");
		System.out.println("The code should not have reached this point.");
	}
	
}
