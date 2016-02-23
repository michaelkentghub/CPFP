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
@WebServlet("/HaveThreeOpenServlett")
public class HaveThreeOpenServlett extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HaveThreeOpenServlett() {
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
		System.out.println("We are here in HaveThreeOpenServlet");
		
		
		
		String NewDate = request.getParameter("passdate");
		System.out.println("The date passed in from jsp is " + NewDate);
		ResultSet result4;
		
		HttpSession session = request.getSession();
		
		String FB = (String)session.getAttribute("FB");
		
		session.setAttribute("ZEROOPEN", "no");
		session.setAttribute("ONEOPEN", "no");
		session.setAttribute("TWOOPEN", "yes");
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
					System.out.println("<br>HaveThreeOpenServlet can't load JDBC driver");  // log error to console
					ex.printStackTrace();
				}
				catch (SQLException ex) 
				{
					System.out.println("<br>HaveThreeOpenServlet SQLException");  // log error to console
					ex.printStackTrace();
				}

				if (conn == null) 
				{
					System.out.println("HaveThreeOpenServlet failed to get connection");
				}
				
				
				int rowCount;
				ResultSet result;  // results of executeQuery()
				String sql;  // SQL to execute 		
				String top = null;
				
		
			
			// make sure that all three dates are open
			if (!( (DATE1OPEN.equals("open")) && (DATE2OPEN.equals("open")) && (DATE3OPEN.equals("open")) ))
			{
				System.out.println("ERROR in HaveThreeOpenServlet: Not all three dates are open.");
				System.out.println("System exiting");
			}
			
			
			//System.out.println("At this point we have verified that all three are open.");
			
		
			
			try {
				
				//System.out.println("We are here in the try block.");
				//System.out.println("Since all are open, we'll write to Date1");
				
			
				
				sql = "update clients SET Date1 =   ?  where email like  ? ";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, NewDate);
				ps.setString(2, email);
				ps.executeUpdate();
			
				
			
			// DATE1OPEN must now be set to closed
			session.setAttribute("DATE1OPEN", "closed");
			
			
			// check to make sure all three dates are correct
			
			String date1 = null;
			String date2 = null;
			String date3 = null;
		
						System.out.println("HaveThreeOpenServlet user input email is " + email);
						sql = "select * from clients where email like (?)";
						PreparedStatement ps2 = conn.prepareStatement(sql);
						ps2.setString(1, email);
						result = ps2.executeQuery();
						
						while (result.next()) {
							System.out.println("Values put in the table by HaveThreeOpenCancelServlet:");
							date1 = result.getString("DATE1");
							System.out.println("DATE1 is " + date1);
							date2 = result.getString("DATE2");
							System.out.println("DATE2 is " + date2);
							date3 = result.getString("DATE3");
							System.out.println("DATE3 is " + date3);
			            }	//while statement
						
			System.out.println("NewDate (from user) = " + NewDate);
			System.out.println("Verifying that all three dates in the table are correct.");
			System.out.println("date1 (from table = " + date1);
			System.out.println("date2 (from table)  = " + date2);
			System.out.println("date3 (from table) = " + date3);
			   		
			//push the 3 dates and date_opens onto the session
			session.setAttribute("DATE1", date1);
			session.setAttribute("DATE2", "");
			session.setAttribute("DATE3", "");
			session.setAttribute("DATE1OPEN", "closed");
			session.setAttribute("DATE2OPEN", "open");
			session.setAttribute("DATE3OPEN", "open");
			
			

		
				if (NewDate.equals(date1)) 
				{
					System.out.println("This is the HaveThreeOpenServlet.");
					System.out.println("NewDate was successfully written to Date1.");
				}
				
				else
				{
					System.out.println("This is the HaveThreeOpenServlet.");
					System.out.println("NewDate was NOT successfully written to Date1.");
				}
			    

			
			//System.out.println("If we reached this point in HaveThreeOpenServlet, it worked.");
			
			
			
			
			
			
			
		}  // end of try block
			
		
		
			
		catch (SQLException ex) 
		{
			System.out.println("SQL Exception in HaveThreeOpenServlet.");
			ex.printStackTrace();
		}
		
			//System.out.println("End of HaveThreeOpenServlet doGet block.");
		
			String yes = "yes";
			String notyet = "notyet";
			
			
			if (FB.equals(notyet))
			{ 
			String url = "/havetwoopen.jsp";
			System.out.println("Leaving HaveThreeOpenCancelServlett.   Re-directing to havetwoopen.jsp");
			getServletContext().getRequestDispatcher(url).forward(request, response);
			}
			else if (FB.equals(yes))
			{ 
			String url = "/havetwoopenFB.jsp";
			System.out.println("Leaving HaveThreeOpenCancelServlett.   Re-directing to havetwoopenFB.jsp");
			getServletContext().getRequestDispatcher(url).forward(request, response);
			}
			
		
}	//end of doGet
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside of HaveThreeOpenServlet doPost block.");
		System.out.println("The code should not have reached this point.");
	}
	
}

