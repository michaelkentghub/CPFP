

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
@WebServlet("/CPFPSQLChangeServlet2")
public class CPFPSQLChangeServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TEMPORARYERROR = 
			"Sorry, we are experiencing a temporary error...please retry later";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CPFPSQLChangeServlet2() {
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
		
		
		System.out.println("We are here at the beginning of CPFPSQLChangeServlet2");
	
		HttpSession session = request.getSession();
		
		String email = (String)session.getAttribute("EMAIL");
		
		
		String fname = null;
		fname = request.getParameter("fname");
		System.out.println("fname = " + fname);
		
		String lname = null;
		lname = request.getParameter("lname");
		System.out.println("lname = " + lname);

		String address = null;
		address = request.getParameter("address");
		System.out.println("address = " + address);

		String password1 = null;
		password1 = request.getParameter("password1");
		System.out.println("password1 = " + password1);

		String password2 = null;
		password2 = request.getParameter("password2");
		System.out.println("password2 = " + password2);
		
		
		//System.out.println("This concludes the printout of all the user values passed into CPFPSQLChangeServlet");
		
		
	

		
		
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
			System.out.println("CPFPSQLChangeServlet2 failed to get connection");
			System.exit(0);   // this is just here for debug and will be removed before releasing code.
		}
		
		//System.out.println("conn = " + conn);
		
		
		
// SQL Prepared Statements
		
				ResultSet result;  // results of executeQuery()
				ResultSet result2;
				int rowCount;  // number of rows affected from executeUpdate()
				String feedback = "No feedback entered yet";
				String fb = "notyet";
				
				
				try {
				
				// insert user input from registration into new row (account) in table clients
					
					
					if (!password1.equals(password2))
					{
				    	System.out.println("Passwords do not match: " + password1 + " " + password2);
				    	System.out.println("Re-directing to RegisterChangePasswordsDoNotMatch.jsp");
				    	String url = "/RegisterChangePasswordsDoNotMatch.jsp";
						getServletContext().getRequestDispatcher(url).forward(request, response);
					} 
					
					if (password1.equals(password2))
					{
					
					String sql = "update clients SET fname =   ?  where email like  ? ";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, fname);
					ps.setString(2, email);
					ps.executeUpdate();
					
					sql = "update clients SET lname =   ?  where email like  ? ";
					PreparedStatement ps2 = conn.prepareStatement(sql);
					ps2.setString(1, lname);
					ps2.setString(2, email);
					ps2.executeUpdate();
					
					sql = "update clients SET address =   ?  where email like  ? ";
					PreparedStatement ps3 = conn.prepareStatement(sql);
					ps3.setString(1, address);
					ps3.setString(2, email);
					ps3.executeUpdate();
					
					sql = "update clients SET password1 =   ?  where email like  ? ";
					PreparedStatement ps4 = conn.prepareStatement(sql);
					ps4.setString(1, password1);
					ps4.setString(2, email);
					ps4.executeUpdate();
					
					
					

				
					// In case we want to print out the table values for this email.
					
					System.out.println("CPFPSQLServlet2 user input email is " + email);
					sql = "select * from clients where email like (?)";
					PreparedStatement ps6 = conn.prepareStatement(sql);
					ps6.setString(1, email);
					result = ps6.executeQuery();
				
					
					while (result.next()) {
						System.out.println("Row values after changes made by CPFPSQLServlet2:");
						String a = result.getString("email");
						System.out.println("email is " + a);
						String e = result.getString("password1");
						System.out.println("password is " + e);
						String b = result.getString("fname");
						System.out.println("fname is " + b);
						String c = result.getString("lname");
						System.out.println("lname is " + c);
						String d = result.getString("address");
						System.out.println("address is " + d);
						String f = result.getString("Date1");
						System.out.println("Date1 is " + f);
						String g = result.getString("Date2");
						System.out.println("Date2 is " + g);
						String h = result.getString("Date3");
						System.out.println("Date3 is " + h);
						String i = result.getString("feedback");
						System.out.println("feedback is " + i);
						String j = result.getString("fb");
						System.out.println("fb is " + i);
					} // while loop
					
					
					//sql = "delete from clients where fname like 'John%'";
					
					System.out.println("CPFPSQLServlet2 end of try block.");
					
					
					String url = "/regchangesuccesslogin.html";
					System.out.println("This is the exit point in CPFPSQLChangeServlet2 where we branch to regchangesuccesslogin");
					//System.out.println("At this point email is " + email);
					getServletContext().getRequestDispatcher(url).forward(request, response);
					
					}  // if password	
				}  // end of try block
				
				catch (SQLException se) {
					System.out.println("In the CPFPSQLChangeServelet2 SQLException catch block");
					//System.exit(0);   // For debug purposes only.  Will remove in final code.  
					                  // If SQL statements fail, I don't want the program to proceed past this point.
				se.printStackTrace();
				}
		 
	}   //  end of doGet
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside CPFPSQLServlet2 doPost block,");
		System.out.println("The code should not go here.");
	}

}


