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
@WebServlet("/HaveZeroOpenCancelServlet")
public class HaveZeroOpenCancelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HaveZeroOpenCancelServlet() {
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
		System.out.println("We are here in HaveZeroOpenCancelServlet");
		
		
		String one = request.getParameter("one");
		System.out.println("one passed in from html is " + one);
		if(one == null) 
		{
			one = "zero";
		}
		
		String two = request.getParameter("two");
		System.out.println("two passed in from html is " + two);
		if(two == null) 
		{
			two = "zero";
		}
		
		String three = request.getParameter("three");
		System.out.println("three passed in from html is " + three);
		if(three == null) 
		{
			three = "zero";
		}
		
		
		
		
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
	
		
		if ((DATE1OPEN.equals("open")) || (DATE2OPEN.equals("open")) || (DATE3OPEN.equals("open")))
		{
			System.out.println("This is the HaveZeroOpenCancelServlet.  ");	
			System.out.println("ERROR: No dates should be open, but one or more is open.");
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
			System.out.println("<br>HaveZeroOpenCancelServlet can't load JDBC driver");  // log error to console
			ex.printStackTrace();
		}
		catch (SQLException ex) 
		{
			System.out.println("<br>HaveZeroOpenCancelServlet SQLException");  // log error to console
			ex.printStackTrace();
		}

		if (conn == null) 
		{
			System.out.println("HaveZeroOpenCancelServlet failed to get connection");
		}
				
				
				int rowCount;
				ResultSet result;  // results of executeQuery()
				String sql;  // SQL to execute 		
				String top = null;
				String NewDate = "2000-01-01";
				String onecheck = "Cancel_1";
				String twocheck = "Cancel_2";
				String threecheck = "Cancel_3";
				
				try {
					
					
					
					System.out.println("one = " + one);
					System.out.println("onecheck = " + onecheck);
					System.out.println("two = " + two);
					System.out.println("twocheck = " + twocheck);
					System.out.println("three = " + three);
					System.out.println("threecheck = " + threecheck);
					
					
					
					if (one.equals(onecheck))
					{
						//Must move DATE2 back to DATE1, then move DATE3 back to DATE2 and set DATE3 to 2000-01-01
						System.out.println("Cancel_1 was selected.");	
						
						sql = "update clients SET Date1 =   ?  where email like  ? ";
						PreparedStatement ps2 = conn.prepareStatement(sql);
						ps2.setString(1, DATE2);
						ps2.setString(2, email);
						ps2.executeUpdate();
						
						sql = "update clients SET DATE2 =   ?  where email like  ? ";
						PreparedStatement ps3 = conn.prepareStatement(sql);
						ps3.setString(1, DATE3);
						ps3.setString(2, email);
						ps3.executeUpdate();
						
						sql = "update clients SET DATE3 =   ?  where email like  ? ";
						PreparedStatement ps4 = conn.prepareStatement(sql);
						ps4.setString(1, NewDate);
						ps4.setString(2, email);
						ps4.executeUpdate();
						
						session.setAttribute("DATE3OPEN", "open");
						session.setAttribute("DATE3", "" );
						
						session.setAttribute("DATE2OPEN", "closed");
						session.setAttribute("DATE2", DATE3);
						
						session.setAttribute("DATE1OPEN", "closed");
						session.setAttribute("DATE1", DATE2);
						
						System.out.println("We are at the end of the Cancel_1 if statement");
					}  // Cancel_1 if
					
					
					
				
					else if (two.equals(twocheck))
					{
						// Move Date3 to Date2, then reset Date3
						System.out.println("Cancel_2 was selected.");
						
						sql = "update clients SET Date2 =   ?  where email like  ? ";
						PreparedStatement ps2 = conn.prepareStatement(sql);
						ps2.setString(1, DATE3);
						ps2.setString(2, email);
						ps2.executeUpdate();
						
						sql = "update clients SET Date3 =   ?  where email like  ? ";
						PreparedStatement ps3 = conn.prepareStatement(sql);
						ps3.setString(1, NewDate);
						ps3.setString(2, email);
						ps3.executeUpdate();
						

						session.setAttribute("DATE1OPEN", "closed");
						session.setAttribute("DATE1", DATE1);
						
						session.setAttribute("DATE2OPEN", "closed");
						session.setAttribute("DATE2", DATE3);
						
						session.setAttribute("DATE3OPEN", "open");
						session.setAttribute("DATE3", "");
						
						System.out.println("We are at the end of the Cancel_2 if statement");
						
					}  // Cancel_2 if
					
					
					
					
					
					
					
					else if (three.equals(threecheck))
					{
						// Just cancel Date3
						System.out.println("Cancel_3 was selected.");	
						
						sql = "update clients SET Date3 =   ?  where email like  ? ";
						PreparedStatement ps2 = conn.prepareStatement(sql);
						ps2.setString(1, NewDate);
						ps2.setString(2, email);
						ps2.executeUpdate();
						
						session.setAttribute("DATE3OPEN", "open");
						session.setAttribute("DATE3", "");
						
						
					}  // Cancel_3 if
					
					
					
					else
					{
						System.out.println("Something has gone wrong in HaveZeroOpenCancelServlet");
						System.out.println("Neither cancel_1 or cancel_2 or cancel_3 were selected, yet somehow we got here?!");
					}
					
					
				
					
					
				} // try block
				
				
				catch (SQLException ex) 
				{
					System.out.println("SQL Exception in HaveZeroOpenCancelServlet.");
					ex.printStackTrace();
				}
				
				
				
				System.out.println("Exiting the HaveZeroOpenCancelServlet try block.");	
				
			
				
				
				String notyet = "notyet";
				String yes = "yes";
				
				if (FB.equals(notyet))
				{ 
				String url = "/haveoneopen.jsp";
				System.out.println("Leaving HaveZeroOpenCancelServlet.   Re-directing to haveoneopen.jsp");
				getServletContext().getRequestDispatcher(url).forward(request, response);
				}
				else if (FB.equals(yes))
				{ 
				String url = "/haveoneopenFB.jsp";
				System.out.println("Leaving HaveZeroOpenCancelServlet.   Re-directing to haveoneopenFB.jsp");
				getServletContext().getRequestDispatcher(url).forward(request, response);
				}
				
		
}	//end of doGet
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside of HaveZeroOpenCancelServlet doPost block.");
		System.out.println("The code should not have reached this point.");
	}
	
}
