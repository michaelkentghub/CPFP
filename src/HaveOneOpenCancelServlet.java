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
@WebServlet("/HaveOneOpenCancelServlet")
public class HaveOneOpenCancelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HaveOneOpenCancelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		response.setContentType("text/html");
		//PrintWriter out = response.getWriter();
		System.out.println("We are here in HaveOneOpenCancelServlet");
		
		
		String one = request.getParameter("one");
		//System.out.println("one passed in from html is " + one);
		if(one == null) 
		{
			one = "zero";
		}
		
		
		String two = request.getParameter("two");
		//System.out.println("two passed in from html is " + two);
		if(two == null) 
		{
			two = "zero";
		}
		
		
		
		
		
		
		HttpSession session = request.getSession();
		
		session.setAttribute("ZEROOPEN", "no");
		session.setAttribute("ONEOPEN", "no");
		session.setAttribute("TWOOPEN", "yes");
		session.setAttribute("THREEOPEN", "no");
		
		String FB = (String)session.getAttribute("FB");
		
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
	

		
		// check if two or three dates are open
		
					if ((DATE1OPEN.equals("open")) && (DATE2OPEN.equals("open")) && (DATE3OPEN.equals("open")))
					{
						System.out.println("This is the HaveOneOpenCancelServlet.  ");	
						System.out.println("ERROR: Only one date should be open, but all three are open.");
					}	
						
					if ((DATE1OPEN.equals("open")) && (DATE2OPEN.equals("open")))
					{
						System.out.println("This is the HaveOneOpenCancelServlet.  ");	
						System.out.println("ERROR: Only one date should be open, and DATE1 and DATE2 are both open.");
					}
					
					if ((DATE1OPEN.equals("open")) && (DATE3OPEN.equals("open")))
					{
						System.out.println("This is the HaveOneOpenCancelServlet.  ");	
						System.out.println("ERROR: Only one date should be open, and DATE1 and DATE3 are both open.");
					}
					
					if ((DATE2OPEN.equals("open")) && (DATE3OPEN.equals("open")))
					{
						System.out.println("This is the HaveOneOpenCancelServlet.  ");	
						System.out.println("ERROR: Only one date should be open, and DATE2 and DATE3 are both open.");
					}
					
					
					// check if no dates are open
					if ((DATE1OPEN.equals("closed")) && (DATE2OPEN.equals("closed")) && (DATE3OPEN.equals("closed")))
					{	
						System.out.println("This is the HaveOneOpenCancelServlet.  ");	
						System.out.println("One date should be open, but no dates are open");
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
					System.out.println("<br>HaveOneOpenCancelServlet can't load JDBC driver");  // log error to console
					ex.printStackTrace();
				}
				catch (SQLException ex) 
				{
					System.out.println("<br>HaveOneOpenCancelServlet SQLException");  // log error to console
					ex.printStackTrace();
				}

				if (conn == null) 
				{
					System.out.println("HaveOneOpenCancelServlet failed to get connection");
				}
				
				
				int rowCount;
				ResultSet result;  // results of executeQuery()
				String sql;  // SQL to execute 		
				String top = null;
				String NewDate = "2000-01-01";
				String onecheck = "Cancel_1";
				String twocheck = "Cancel_2";
				
				try {
					
					//System.out.println("one = " + one);
					//System.out.println("onecheck = " + onecheck);
					if (one.equals(onecheck))
					{
						//Must move DATE2 back to DATE1 and set DATE2 to 2000-01-01
						//System.out.println("Cancel_1 was selected.");	
						
						sql = "update clients SET Date1 =   ?  where email like  ? ";
						PreparedStatement ps = conn.prepareStatement(sql);
						ps.setString(1, DATE2);
						ps.setString(2, email);
						ps.executeUpdate();
						
						sql = "update clients SET Date2 =   ?  where email like  ? ";
						PreparedStatement ps2 = conn.prepareStatement(sql);
						ps2.setString(1, NewDate);
						ps2.setString(2, email);
						ps2.executeUpdate();
						
						session.setAttribute("DATE2OPEN", "open");
						session.setAttribute("DATE2", "");
						session.setAttribute("DATE1OPEN", "closed");
						session.setAttribute("DATE1", DATE2);
						
						DATE1 = (String)session.getAttribute("DATE1");
						//System.out.println("DATE1 passed in from the session is " + DATE1);
						DATE2 = (String)session.getAttribute("DATE2");
						//System.out.println("DATE2 passed in from the session is " + DATE2);
						DATE3 = (String)session.getAttribute("DATE3");
						//System.out.println("DATE3 passed in from the session is " + DATE3);
						
						DATE1OPEN = (String)session.getAttribute("DATE1OPEN");
						//System.out.println("DATE1OPEN passed in from the session is " + DATE1OPEN);
						DATE2OPEN = (String)session.getAttribute("DATE2OPEN");
						//System.out.println("DATE2OPEN passed in from the session is " + DATE2OPEN);
						DATE3OPEN = (String)session.getAttribute("DATE3OPEN");
						//System.out.println("DATE3OPEN passed in from the session is " + DATE3OPEN);
						
						
						//System.out.println("HaveOneOpenCancelServlet user input email is " + email);
						//sql = "select * from clients where email like (?)";
						//PreparedStatement ps3 = conn.prepareStatement(sql);
						//ps3.setString(1, email);
						//result = ps3.executeQuery();
					
						
						//while (result.next()) {
							//System.out.println("Values put in the table by HaveOneOpenCancelServlet:");
							//String a = result.getString("DATE1");
							//System.out.println("DATE1 is " + a);
							//String e = result.getString("DATE2");
							//System.out.println("DATE2 is " + e);
							//String b = result.getString("DATE3");
							//System.out.println("DATE3 is " + b);
	                    //}	  while statement
						
		
						
						//System.out.println("We are at the end of the Cancel_1 if statement");
					}  // Cancel_1 if
					
					
					
					
					else if (two.equals(twocheck))
					{
						// Just cancel Date2
						
						
						//System.out.println("Cancel_2 was selected.");
						sql = "update clients SET Date2 =   ?  where email like  ? ";
						PreparedStatement ps4 = conn.prepareStatement(sql);
						ps4.setString(1, NewDate);
						ps4.setString(2, email);
						ps4.executeUpdate();
						
						
						
						
							
				
						
						
						session.setAttribute("DATE2OPEN", "open");
						session.setAttribute("DATE2", "");
						session.setAttribute("DATE1OPEN", "closed");
						//session.setAttribute("DATE1", DATE2);
						
						DATE1 = (String)session.getAttribute("DATE1");
						//System.out.println("DATE1 passed in from the session is " + DATE1);
						DATE2 = (String)session.getAttribute("DATE2");
						//System.out.println("DATE2 passed in from the session is " + DATE2);
						DATE3 = (String)session.getAttribute("DATE3");
						//System.out.println("DATE3 passed in from the session is " + DATE3);
						
						DATE1OPEN = (String)session.getAttribute("DATE1OPEN");
						//System.out.println("DATE1OPEN passed in from the session is " + DATE1OPEN);
						DATE2OPEN = (String)session.getAttribute("DATE2OPEN");
						//System.out.println("DATE2OPEN passed in from the session is " + DATE2OPEN);
						DATE3OPEN = (String)session.getAttribute("DATE3OPEN");
						//System.out.println("DATE3OPEN passed in from the session is " + DATE3OPEN);
						
						
						//System.out.println("HaveOneOpenCancelServlet user input email is " + email);
						//sql = "select * from clients where email like (?)";
						//PreparedStatement ps3 = conn.prepareStatement(sql);
						//ps3.setString(1, email);
						//result = ps3.executeQuery();
					
						
						//while (result.next()) {
							//System.out.println("Values put in the table by HaveOneOpenCancelServlet:");
							//String a = result.getString("DATE1");
							//System.out.println("DATE1 is " + a);
							//String e = result.getString("DATE2");
							//System.out.println("DATE2 is " + e);
							//String b = result.getString("DATE3");
							//System.out.println("DATE3 is " + b);
	                    //}	  while statement
						
						
						
						
						
						//System.out.println("We are at the end of the Cancel_2 if statement");
					}  // Cancel_1 if
					
					
					else
					{
						System.out.println("Something has gone wrong in HaveOneOpenCancelServlet");
						System.out.println("Neither cancel_1 or cancel_2 were selected, yet somehow we got to HaveOneOpenCancelServlet.");
						System.exit(0);  // just for debug
					}
					
					
			
					
					
					
					
					
				} // try block
				
				
				catch (SQLException ex) 
				{
					System.out.println("SQL Exception in HaveOneOpenCancelServlet.");
					ex.printStackTrace();
				}
				
				DATE1 = (String)session.getAttribute("DATE1");
				//System.out.println("DATE1 passed in from the session is " + DATE1);
				DATE2 = (String)session.getAttribute("DATE2");
				//System.out.println("DATE2 passed in from the session is " + DATE2);
				DATE3 = (String)session.getAttribute("DATE3");
				//System.out.println("DATE3 passed in from the session is " + DATE3);
				
				
				DATE1OPEN = (String)session.getAttribute("DATE1OPEN");
				//System.out.println("DATE1OPEN passed in from the session is " + DATE1OPEN);
				DATE2OPEN = (String)session.getAttribute("DATE2OPEN");
				//System.out.println("DATE2OPEN passed in from the session is " + DATE2OPEN);
				DATE3OPEN = (String)session.getAttribute("DATE3OPEN");
				//System.out.println("DATE3OPEN passed in from the session is " + DATE3OPEN);
				
				String yes = "yes";
				String notyet = "notyet";
				
				//System.out.println("Exiting the HaveOneOpenCancelServlet try block.");	
				if (FB.equals(notyet))
				{ 
				String url = "/havetwoopen.jsp";
				System.out.println("Leaving HaveOneOpenCancelServlet.   Re-directing to havetwoopen.jsp");
				getServletContext().getRequestDispatcher(url).forward(request, response);
				}
				else if (FB.equals(yes))
				{ 
				String url = "/havetwoopenFB.jsp";
				System.out.println("Leaving HaveOneOpenCancelServlet.   Re-directing to havetwoopenFB.jsp");
				getServletContext().getRequestDispatcher(url).forward(request, response);
				}
		
}	//end of doGet
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside of HaveOneOpenCancelServlet doPost block.");
		System.out.println("The code should not have reached this point.");
	}
	
}
