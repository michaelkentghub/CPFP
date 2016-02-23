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
@WebServlet("/HaveTwoOpenCancelServlet")
public class HaveTwoOpenCancelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HaveTwoOpenCancelServlet() {
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
		System.out.println("We are here in HaveTwoOpenCancelServlet");
		
		
		HttpSession session = request.getSession();
		
		String FB = (String)session.getAttribute("FB");
		
		session.setAttribute("ZEROOPEN", "no");
		session.setAttribute("ONEOPEN", "no");
		session.setAttribute("TWOOPEN", "no");
		session.setAttribute("THREEOPEN", "yes");
		
		String email = (String)session.getAttribute("EMAIL");
		//System.out.println("email passed in from the session is " + email);
		
	
		String DATE1 = (String)session.getAttribute("DATE1");
		//System.out.println("DATE1 passed in from the session is " + DATE1);
		String DATE2 = (String)session.getAttribute("DATE2");
		//System.out.println("DATE2 passed in from the session is " + DATE2);
		String DATE3 = (String)session.getAttribute("DATE3");
		//System.out.println("DATE3 passed in from the session is " + DATE3);
		
		
		String DATE1OPEN = (String)session.getAttribute("DATE1OPEN");
		//System.out.println("DATE1OPEN passed in from the session is " + DATE1OPEN);
		String DATE2OPEN = (String)session.getAttribute("DATE2OPEN");
		//System.out.println("DATE2OPEN passed in from the session is " + DATE2OPEN);
		String DATE3OPEN = (String)session.getAttribute("DATE3OPEN");
		//System.out.println("DATE3OPEN passed in from the session is " + DATE3OPEN);
	
		
		
		// verify that two and only two dates are open
		
		if ((DATE1OPEN.equals("open")) && (DATE2OPEN.equals("open")) && (DATE3OPEN.equals("open")))
		{
			System.out.println("This is the HaveTwOpenCancelServlet.  ");	
			System.out.println("ERROR: Two dates should be open, but all three are open.");
		}	
			
		if ((DATE1OPEN.equals("closed")) && (DATE2OPEN.equals("closed")) && (DATE3OPEN.equals("closed")))
		{
			System.out.println("This is the HaveTwOpenCancelServlet.  ");	
			System.out.println("ERROR: Two dates should be open, but there are no dates open.");
		}
		
		if ((DATE1OPEN.equals("open")) && (DATE2OPEN.equals("closed")) && (DATE3OPEN.equals("closed")))
		{
			System.out.println("This is the HaveTwoOpenCancelServlet.  ");	
			System.out.println("ERROR: Two dates should be open, but only one is open.");
		}
		
		if ((DATE1OPEN.equals("closed")) && (DATE2OPEN.equals("open")) && (DATE3OPEN.equals("closed")))
		{
			System.out.println("This is the HaveTwoOpenCancelServlet.  ");	
			System.out.println("ERROR: Two dates should be open, but only one is open.");
		}
		
		if ((DATE1OPEN.equals("closed")) && (DATE2OPEN.equals("closed")) && (DATE3OPEN.equals("open")))
		{
			System.out.println("This is the HaveTwoOpenCancelServlet.  ");	
			System.out.println("ERROR: Two dates should be open, but only one is open.");
		}
		
		
		
		
		
		

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
			System.out.println("<br>HaveTwoOpenCancelServlet can't load JDBC driver");  // log error to console
			ex.printStackTrace();
		}
		catch (SQLException ex) 
		{
			System.out.println("<br>HaveTwoOpenCancelServlet SQLException");  // log error to console
			ex.printStackTrace();
		}

		if (conn == null) 
		{
			System.out.println("HaveTwoOpenCancelServlet failed to get connection");
		}
		
		
		
				
				int rowCount;
				ResultSet result;  // results of executeQuery()
				String sql;  // SQL to execute 		
				String top = null;
				String NewDate = "2000-01-01";
				
				try {
					
					if (DATE1OPEN.equals("closed"))
					{
						session.setAttribute("DATE1OPEN", "open");
						session.setAttribute("DATE1", "");
						//System.out.println("Inside DATE1OPEN.");	
						sql = "update clients SET Date1 =   ?  where email like  ? ";
						PreparedStatement ps = conn.prepareStatement(sql);
						ps.setString(1, NewDate);
						ps.setString(2, email);
						ps.executeUpdate();
					}
					
					else if (DATE2OPEN.equals("closed"))
					{
						session.setAttribute("DATE2OPEN", "open");
						session.setAttribute("DATE2", "");
						//System.out.println("Inside DATE2OPEN.");	
						sql = "update clients SET Date2 =   ?  where email like  ? ";
						PreparedStatement ps = conn.prepareStatement(sql);
						ps.setString(1, NewDate);
						ps.setString(2, email);
						ps.executeUpdate();
					}
					
					else if (DATE3OPEN.equals("closed"))
					{
						session.setAttribute("DATE3OPEN", "open");
						session.setAttribute("DATE3", "");
						//System.out.println("Inside DATE3OPEN.");	
						sql = "update clients SET Date3 =   ?  where email like  ? ";
						PreparedStatement ps = conn.prepareStatement(sql);
						ps.setString(1, NewDate);
						ps.setString(2, email);
						ps.executeUpdate();
					}
					
					//System.out.println("HaveTwoOpenCancelServlet check to make sure all three dates are correct");
					
					String date1 = null;
					String date2 = null;
					String date3 = null;
					
					//System.out.println("HaveOneOpenCancelServlet user input email is " + email);
					sql = "select * from clients where email like (?)";
					PreparedStatement ps3 = conn.prepareStatement(sql);
					ps3.setString(1, email);
					result = ps3.executeQuery();
				
					
					while (result.next()) {
						//System.out.println("Values put in the table by HaveOneOpenCancelServlet:");
						date1 = result.getString("DATE1");
						//System.out.println("DATE1 is " + date1);
						date2 = result.getString("DATE2");
						//System.out.println("DATE2 is " + date2);
						date3 = result.getString("DATE3");
						//System.out.println("DATE3 is " + date3);
                    }	  //while statement
					
					
					
					
					//System.out.println("Verify that we leave HaveTwoOpenCancelServlet with the correct session values");
					
				    String a = (String)session.getAttribute("DATE1");
					//System.out.println("DATE1 (from session) = " + a);
					a = (String)session.getAttribute("DATE2");
					//System.out.println("DATE2 (from session) = " + a);
					a = (String)session.getAttribute("DATE3");
					//System.out.println("DATE3 (from session) = " + a);
					
					a = (String)session.getAttribute("DATE1OPEN");
					//System.out.println("DATE1OPEN (from session) = " + a);
					a = (String)session.getAttribute("DATE2OPEN");
					//System.out.println("DATE2OPEN (from session) = " + a);
					a = (String)session.getAttribute("DATE3OPEN");
					//System.out.println("DATE3OPEN (from session) = " + a);
					
					
					
					
					
					
					
					
						
				} // try block
				
				
				catch (SQLException ex) 
				{
					System.out.println("SQL Exception in HaveTwoOpenCancelServlet.");
					ex.printStackTrace();
				}
				
				System.out.println("Exiting the HaveTwoOpenCancelServlet try block.");	
				
				
				
				
			    String notyet = "notyet";
			    String yes = "yes";
			    
				if (FB.equals(notyet))
				{ 
				String url = "/havethreeopen.jsp";
				System.out.println("Leaving HaveTwoOpenCancelServlet.   Re-directing to havethreeopen.jsp");
				getServletContext().getRequestDispatcher(url).forward(request, response);
				}
				else if (FB.equals(yes))
				{ 
				String url = "/havethreeopenFB.jsp";
				System.out.println("Leaving HaveTwoOpenCancelServlet.   Re-directing to havethreeopenFB.jsp");
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

