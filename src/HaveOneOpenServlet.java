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
@WebServlet("/HaveOneOpenServlet")
public class HaveOneOpenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HaveOneOpenServlet() {
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
		System.out.println("We are here in HaveOneOpenServlet");
		
		
		String NewDate = request.getParameter("passdate");
		System.out.println("The date passed in from jsp is " + NewDate);
		
		
		HttpSession session = request.getSession();
		
		String FB = (String)session.getAttribute("FB");
		
		session.setAttribute("ZEROOPEN", "yes");
		session.setAttribute("ONEOPEN", "no");
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
				
		
			
			// check if two or three dates are open
				
			if ((DATE1OPEN.equals("open")) && (DATE2OPEN.equals("open")) && (DATE3OPEN.equals("open")))
			{
				System.out.println("This is the HaveOneOpenServlet.  ");	
				System.out.println("ERROR: Only one date should be open, but all three are open.");
			}	
				
			if ((DATE1OPEN.equals("open")) && (DATE2OPEN.equals("open")))
			{
				System.out.println("This is the HaveOneOpenServlet.  ");	
				System.out.println("ERROR: Only one date should be open, and DATE1 and DATE2 are both open.");
			}
			
			if ((DATE1OPEN.equals("open")) && (DATE3OPEN.equals("open")))
			{
				System.out.println("This is the HaveOneOpenServlet.  ");	
				System.out.println("ERROR: Only one date should be open, and DATE1 and DATE3 are both open.");
			}
			
			if ((DATE2OPEN.equals("open")) && (DATE3OPEN.equals("open")))
			{
				System.out.println("This is the HaveOneOpenServlet.  ");	
				System.out.println("ERROR: Only one date should be open, and DATE2 and DATE3 are both open.");
			}
			
			
			// check if no dates are open
			if ((DATE1OPEN.equals("closed")) && (DATE2OPEN.equals("closed")) && (DATE3OPEN.equals("closed")))
			{	
				System.out.println("This is the HaveOneOpenServlet.  ");	
				System.out.println("One date should be open, but no dates are open");
			}
			
			
			
			try {
				
				//System.out.println("We are here in the try block.");
				
				
			if (DATE1OPEN.equals("open"))
			{
			System.out.println("Inside DATE1OPEN.");	
			session.setAttribute("DATE1OPEN", "closed");
			session.setAttribute("DATE1", NewDate );
			sql = "update clients SET Date1 =   ?  where email like  ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, NewDate);
			ps.setString(2, email);
			ps.executeUpdate();
			}
			
			
			if (DATE2OPEN.equals("open"))
			{
			System.out.println("Inside DATE2OPEN.");
			session.setAttribute("DATE2OPEN", "closed");
			session.setAttribute("DATE2", NewDate );
			sql = "update clients SET Date2 =   ?  where email like  ? ";
			PreparedStatement ps2 = conn.prepareStatement(sql);
			ps2.setString(1, NewDate);
			ps2.setString(2, email);
			ps2.executeUpdate();
			}
			

			if (DATE3OPEN.equals("open"))
			{
			System.out.println("Inside DATE3OPEN.");	
			session.setAttribute("DATE3OPEN", "closed");
			session.setAttribute("DATE3", NewDate );
			sql = "update clients SET Date3 =   ?  where email like  ? ";
			PreparedStatement ps3 = conn.prepareStatement(sql);
			ps3.setString(1, NewDate);
			ps3.setString(2, email);
			ps3.executeUpdate();
			}
			
			
			
			
			
			
            // check to make sure all three table dates match Session dates
			
			String date1 = null;
			String date2 = null;
			String date3 = null;
			
			
			//System.out.println("HaveOneOpenServlet user input email is " + email);
			sql = "select * from clients where email like (?)";
			PreparedStatement ps4 = conn.prepareStatement(sql);
			ps4.setString(1, email);
			result = ps4.executeQuery();
			
			while (result.next()) {
				//System.out.println("Values put in the table by HaveOneOpenServlet:");
				date1 = result.getString("DATE1");
				//System.out.println("DATE1 is " + date1);
				date2 = result.getString("DATE2");
				//System.out.println("DATE2 is " + date2);
				date3 = result.getString("DATE3");
				//System.out.println("DATE3 is " + date3);
            }	  //while statement
			

			
			if (DATE1OPEN.equals("open"))	
			{	
				System.out.println("DATE1OPEN was open");
				if (!NewDate.equals(date1))
				{
					System.out.println("This is the HaveOneOpenServlet.");
					System.out.println("Date1 was open.");
					System.out.println("Date1 was not inserted into the table.");
				}
			    
				if (!DATE2.equals(date2))
				{
					System.out.println("This is the HaveOneOpenServlet.");
					System.out.println("Date1 was open.");
					System.out.println("Date2 table value does not match Date2 session value.");
				}
				
				if (!DATE3.equals(date3))
				{
					System.out.println("This is the HaveOneOpenServlet.");
					System.out.println("Date1 was open.");
					System.out.println("Date3 table value does not match Date3 session value.");
				}
				
				
			}
			
			

			
			if (DATE2OPEN.equals("open"))	
			{	
				System.out.println("DATE2OPEN was open");
				if (!NewDate.equals(date2))
				{
					System.out.println("This is the HaveOneOpenServlet.");
					System.out.println("Date2 was open.");
					System.out.println("Date2 was not inserted into the table.");
				}
			    
				if (!DATE1.equals(date1))
				{
					System.out.println("This is the HaveOneOpenServlet.");
					System.out.println("Date2 was open.");
					System.out.println("Date1 table value does not match Date1 session value.");
				}
				
				if (!DATE3.equals(date3))
				{
					System.out.println("This is the HaveOneOpenServlet.");
					System.out.println("Date2 was open.");
					System.out.println("Date3 table value does not match Date3 session value.");
				}
				
				
			}
				

			
			
			if (DATE3OPEN.equals("open"))	
			{	
				System.out.println("DATE3OPEN was open");
				if (!NewDate.equals(date3))
				{
					System.out.println("This is the HaveOneOpenServlet.");
					System.out.println("Date3 was open.");
					System.out.println("Date3 was not inserted into the table.");
				}
			    
				if (!DATE1.equals(date1))
				{
					System.out.println("This is the HaveOneOpenServlet.");
					System.out.println("Date3 was open.");
					System.out.println("Date1 table value does not match Date1 session value.");
				}
				
				if (!DATE2.equals(date2))
				{
					System.out.println("This is the HaveOneOpenServlet.");
					System.out.println("Date3 was open.");
					System.out.println("Date2 table value does not match Date2 session value.");
				}
				
			}
			
			
			
			//System.out.println("If we reached this point, it worked.");
			
			
			session.setAttribute("DATE1", date1 );
			session.setAttribute("DATE2", date2 );
			session.setAttribute("DATE3", date3 );	
			
			
	
			
			
		}  // end of try block
			
		
		
			
		catch (SQLException ex) 
		{
			System.out.println("SQL Exception in HaveOneOpenServlet.");
			ex.printStackTrace();
		}
		
			//System.out.println("End of HaveOneOpenServlet doGet block.");
			//System.out.println("The code should not have reached this point.");
			
			String notyet = "notyet";
			String yes = "yes";
			
			if (FB.equals(notyet))
			{ 
			String url = "/havezeroopen.jsp";
			System.out.println("Leaving HaveOneOpenCancelServlet.   Re-directing to havezeroopen.jsp");
			getServletContext().getRequestDispatcher(url).forward(request, response);
			}
			else if (FB.equals(yes))
			{ 
			String url = "/havezeroopenFB.jsp";
			System.out.println("Leaving HaveOneOpenCancelServlet.   Re-directing to havezeroopenFB.jsp");
			getServletContext().getRequestDispatcher(url).forward(request, response);
			}
		
}	//end of doGet
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside of HaveOneOpenServlet doPost block.");
		System.out.println("The code should not have reached this point.");
	}
	
}
