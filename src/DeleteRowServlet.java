

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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;


/**
 * Servlet implementation class persistence
 */
@WebServlet("/DeleteRowServlet")
public class DeleteRowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TEMPORARYERROR = 
			"Sorry, we are experiencing a temporary error...please retry later";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteRowServlet() {
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
		
		
		System.out.println("We are here at the beginning of DeleteRowServlet");
	
		//HttpSession session = request.getSession();
		
		
		String email = null;
		email = request.getParameter("email");
		System.out.println("email = " + email);
		
		
		// SQL Database Connection
		
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
			System.out.println("<br>Can't load JDBC driver");  // log error to console
			ex.printStackTrace();
		}
		catch (SQLException ex) 
		{
			System.out.println("<br>SQLException");  // log error to console
			ex.printStackTrace();
		}
		

		if (conn == null) 
		{
			System.out.println("DeleteRowServlet failed to get connection");
			System.exit(0);   // this is just here for debug and will be removed before releasing code.
		}
		
		//System.out.println("conn = " + conn);
		
		
		
// SQL Prepared Statements
		
				
				
				try {
				
				// insert user input from registration into new row (account) in table clients
					String sql = "delete from clients where email like ? ";
					PreparedStatement ps = conn.prepareStatement(sql);
					
					ps.setString(1, email);
					ps.executeUpdate();
							
					
					
					//System.out.println("DeleteRowServlet end of try block.");
				}  // end of try block
				
				catch (SQLException se) {
					System.out.println("In the DeleteRowServelet SQLException catch block");
					//System.exit(0);   // For debug purposes only.  Will remove in final code.  
					                  // If SQL statements fail, I don't want the program to proceed past this point.
				se.printStackTrace();
				}
				
				
				//System.out.println("Point I");
				// Have successfully registered.  Set Feedback state to "not yet".
				//session.setAttribute("FB", "notyet");
				String url = "/LoginOrRegister.html";
				System.out.println("This is the exit point in DeleteRowServlet where we go to LoginOrRegister.html");
				//System.out.println("At this point email is " + email);
				getServletContext().getRequestDispatcher(url).forward(request, response);
				
				

	//System.out.println("This is the end of DeleteRowServlet doGet block,");
		 
	}  // end of doGet
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside DeleteRowServlet doPost block,");
		System.out.println("The code should not go here.");
	}

}
