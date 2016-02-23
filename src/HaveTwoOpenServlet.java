import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.NullPointerException;


/**
 * Servlet implementation class persistence
 */
@WebServlet("/HaveTwoOpenServlet")
public class HaveTwoOpenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HaveTwoOpenServlet() {
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
		System.out.println("We are here in HaveTwoOpenServlet");
		
		
		String NewDate = request.getParameter("passdate");
		System.out.println("The date passed in from jsp is " + NewDate);
		
		
		HttpSession session = request.getSession();
		
		String FB = (String)session.getAttribute("FB");
		
		session.setAttribute("ZEROOPEN", "no");
		session.setAttribute("ONEOPEN", "yes");
		session.setAttribute("TWOOPEN", "no");
		session.setAttribute("THREEOPEN", "no");
		
		String email = (String)session.getAttribute("EMAIL");
		System.out.println("email passed in from the session is " + email);
		
		String DATE1 = (String)session.getAttribute("DATE1");
		System.out.println("DATE1 passed in from the session is " + DATE1);
		String DATE2 = (String)session.getAttribute("DATE2");
		System.out.println("DATE2 passed in from the session is " + DATE2);
		String DATE3 = (String)session.getAttribute("DATE3");
		System.out.println("DATE3 passed in from the session is " + DATE3);
		
		
		String DATE1OPEN = (String)session.getAttribute("DATE1OPEN");
		System.out.println("DATE1OPEN passed in from the session is " + DATE1OPEN);
		String DATE2OPEN = (String)session.getAttribute("DATE2OPEN");
		System.out.println("DATE2OPEN passed in from the session is " + DATE2OPEN);
		String DATE3OPEN = (String)session.getAttribute("DATE3OPEN");
		System.out.println("DATE3OPEN passed in from the session is " + DATE3OPEN);
	
		String date1 = null;
		String date2 = null;
		String date3 = null;
		
		
		// Make SQL Database Connection
		
				String myDB = "jdbc:mysql://localhost:3306/customer";  // local connection string
				String user = "root";  // local user
				String pswd = "dangelacath57"; // local MySQL root passwd
				
				Connection conn = null;

				//make connection
				try 
				{
					Class.forName("com.mysql.jdbc.Driver");
					conn = DriverManager.getConnection(myDB, user, pswd);
				}
				catch (ClassNotFoundException ex) 
				{
					System.out.println("<br>HaveOneOpenServlet can't load JDBC driver");  // log error to console
					ex.printStackTrace();
				}
				catch (SQLException ex) 
				{
					System.out.println("<br>HaveOneOpenServlet SQLException");  // log error to console
					ex.printStackTrace();
				}

				if (conn == null) 
				{
					System.out.println("HaveOneOpenServlet failed to get connection");
				}
				
		
		

				
				int rowCount;
				ResultSet result;  // results of executeQuery()
				String sql;  // SQL to execute 		
				String top = null;
				
		
			
			// make sure that two and only two dates are open
			if ((DATE1OPEN.equals("open")) && (DATE2OPEN.equals("open")) && (DATE3OPEN.equals("open")))
			{
				System.out.println("ERROR: Only two dates should be open, but all three are open.");
			}
			
			if ((DATE1OPEN.equals("closed")) && (DATE2OPEN.equals("closed")) && (DATE3OPEN.equals("open")))
			{
				System.out.println("ERROR: Two dates should be open, but only DATE3 is open.");
			}
			
			if ((DATE1OPEN.equals("closed")) && (DATE2OPEN.equals("open")) && (DATE3OPEN.equals("closed")))
			{
				System.out.println("ERROR: Two dates should be open, but only DATE2 is open.");
			}
			
			if ((DATE1OPEN.equals("open")) && (DATE2OPEN.equals("closed")) && (DATE3OPEN.equals("closed")))
			{
				System.out.println("ERROR: Two dates should be open, but only DATE1 is open.");
			}
			
			if ((DATE1OPEN.equals("closed")) && (DATE2OPEN.equals("closed")) && (DATE3OPEN.equals("closed")))
			{
				System.out.println("ERROR: Two dates should be open, but none of them are open.");
			}
			
			
			
			
			System.out.println("At this point we have verified that two and only two appts are open.");
			
		
			
			try {
				
				System.out.println("We are here in the try block.");
	
				
			if (DATE1OPEN.equals("open"))
			{
			System.out.println("Inside DATE1OPEN.");
			System.out.println("Setting Session value of DATE1OPEN to closed");
			System.out.println("Setting Session value of DATE1 to NewDate");
				session.setAttribute("DATE1OPEN", "closed");
				session.setAttribute("DATE1", NewDate);
				System.out.println("Writing new date from user to Date1");
				sql = "update clients SET Date1 =   ?  where email like  ? ";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, NewDate);
				ps.setString(2, email);
				ps.executeUpdate();
			}
			
			
			else if (DATE2OPEN.equals("open"))
			{
			System.out.println("Inside DATE2OPEN.");
			System.out.println("Setting Session value of DATE2OPEN to closed");
			System.out.println("Setting Session value of DATE2 to NewDate");
				session.setAttribute("DATE2OPEN", "closed");
				session.setAttribute("DATE2", NewDate);
				System.out.println("Writing new date from user to Date2");
				sql = "update clients SET Date2 =   ?  where email like  ? ";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, NewDate);
				ps.setString(2, email);
				ps.executeUpdate();
			}
			

			
			else if (DATE3OPEN.equals("open"))
			{
			System.out.println("Inside DATE3OPEN.");
			System.out.println("Setting Session value of DATE3OPEN to closed");
			System.out.println("Setting Session value of DATE3 to NewDate");
				session.setAttribute("DATE3OPEN", "closed");
				session.setAttribute("DATE3", NewDate);
				System.out.println("Writing new date from user to Date3");
				sql = "update clients SET Date3 =   ?  where email like  ? ";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, NewDate);
				ps.setString(2, email);
				ps.executeUpdate();
			}
			
			
			
			else
			{
				System.out.println("Something went wrong: none of the dates are open.");
			}
			
			
			
           
			
			
			
			System.out.println("If we reached this point in HaveTwoOpenServlet, the date was written correctly to the table.");
			
			
			
				
			//System.out.println("Exiting the HaveTwoOpenServlet try block.");	
			
			
			
			
		}  // end of try block
			
		
		
			
		catch (SQLException ex) 
		{
			System.out.println("SQL Exception in HaveTwoOpenServlet.");
			ex.printStackTrace();
		}
		
			System.out.println("End of HaveTwoOpenServlet doGet block.");
			System.out.println("The code should not have reached this point.");
			
			
			String notyet = "notyet";
			String yes = "yes";
			
			if (FB.equals(notyet))
			{ 
			String url = "/haveoneopen.jsp";
			System.out.println("Leaving HaveTwoOpenServlet.   Re-directing to haveoneopen.jsp");
			getServletContext().getRequestDispatcher(url).forward(request, response);
			}
			else if (FB.equals(yes))
			{ 
			String url = "/haveoneopenFB.jsp";
			System.out.println("Leaving HaveTwoOpenServlet.   Re-directing to haveoneopenFB.jsp");
			getServletContext().getRequestDispatcher(url).forward(request, response);
			}
			
			
			
			
			
			
			
		
}	//end of doGet
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside of HaveTwoOpenServlet doPost block.");
		System.out.println("The code should not have reached this point.");
	}
	
}

