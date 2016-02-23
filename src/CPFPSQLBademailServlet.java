

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
@WebServlet("/CPFPSQLBademailServlet")
public class CPFPSQLBademailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TEMPORARYERROR = 
			"Sorry, we are experiencing a temporary error...please retry later";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CPFPSQLBademailServlet() {
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
		
		
		System.out.println("We are here at the beginning of CPFPSQLServlet");
	
		HttpSession session = request.getSession();
		
		
		
		
		String fname = null;
		fname = request.getParameter("fname");
		session.setAttribute("FNAME", fname);
		//System.out.println("fname = " + fname);
		
		String lname = null;
		lname = request.getParameter("lname");
		session.setAttribute("LNAME", lname);
		//System.out.println("lname = " + lname);

		String address = null;
		address = request.getParameter("address");
		session.setAttribute("ADDRESS", address);
		//System.out.println("address = " + address);

		String email = null;
		email = request.getParameter("email");
		session.setAttribute("EMAIL", email);
		//System.out.println("email = " + email);

		String password1 = null;
		password1 = request.getParameter("password1");
		//System.out.println("password1 = " + password1);

		String password2 = null;
		password2 = request.getParameter("password2");
		//System.out.println("password2 = " + password2);
		
		String passdate1 = (String)session.getAttribute("DATE1");
		String passdate2 = (String)session.getAttribute("DATE2");
		String passdate3 = (String)session.getAttribute("DATE3");
		
		
		
		
		
		//System.out.println("This concludes the printout of all the user values passed into CPFPSQLServlet");
		
		
		
		String Date = "2000-01-01";   // default value before customer schedules an appt.
		
		
		
		
		if (!email.contains("@") || !email.contains("."))
		{
			System.out.println("bad email = " + email);
			System.out.println("Re-directing to regbademail.html");
			String url = "/regbademail.html";
			getServletContext().getRequestDispatcher(url).forward(request, response);
		}
		
		
		if (!(password1.equals(password2)))
		{
			System.out.println("Passwords do not match: " + password1 + " " + password2);
	    	System.out.println("Re-directing to RegisterPasswordsDoNotMatch.jsp");
	    	String url = "/RegisterPasswordsDoNotMatch.jsp";
			getServletContext().getRequestDispatcher(url).forward(request, response);
		} 
	

		
		
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
			System.out.println("CPFPSQLServlet failed to get connection");
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
					String sql = "insert into clients (email, fname, lname,address, password1, Date1, Date2, Date3, feedback, fb) values  (?,?,?,?,?,?,?,?,?,?)";
					PreparedStatement ps2 = conn.prepareStatement(sql);
					
					ps2.setString(1, email);
					ps2.setString(2, fname);
					ps2.setString(3, lname);
					ps2.setString(4, address);
					ps2.setString(5, password1);
					ps2.setString(6, passdate1);
					ps2.setString(7, passdate2);
					ps2.setString(8, passdate3);
					ps2.setString(9, feedback);
					ps2.setString(10, fb);
					ps2.executeUpdate();
							
					
					
				
					// In case we want to print out the table values for this email.
					
					System.out.println("CPFPSQLServlet user input email is " + email);
					sql = "select * from clients where email like (?)";
					PreparedStatement ps3 = conn.prepareStatement(sql);
					ps3.setString(1, email);
					result = ps3.executeQuery();
				
					
					while (result.next()) {
						System.out.println("Values put in the table by CPFPSQLServlet:");
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
					}
					
					
					//sql = "delete from clients where fname like 'John%'";
					
					//System.out.println("CPFPSQLServlet end of try block.");
				}  // end of try block
				
				catch (SQLException se) {
					System.out.println("In the CPFPSQLServelet SQLException catch block");
					//System.exit(0);   // For debug purposes only.  Will remove in final code.  
					                  // If SQL statements fail, I don't want the program to proceed past this point.
				se.printStackTrace();
				}
				
				
				//System.out.println("Point I");
				// Have successfully registered.  Set Feedback state to "not yet".
				//session.setAttribute("FB", "notyet");
				String url = "/regsuccesslogin.html";
				System.out.println("This is the exit point in CPFPSQLServlet where we branch to regsuccesslogin");
				//System.out.println("At this point email is " + email);
				getServletContext().getRequestDispatcher(url).forward(request, response);
				
				

	//System.out.println("This is the end of CPFPSQLServlet doGet block,");
	
	
	// check to see if account with this email already exists
	//System.out.println("email = " + email);
	//sql = "select * from clients where email like '" + email + "'";
	//result2 = myDB.executeQuery(sql);
	//System.out.println("result2 = " + result2);
	//while (result2.next()) 
	//{
	//	String y = result2.getString("email");
	//	if (y == null)
	//		{System.out.println("result is null");}
	//}
	//System.exit(0);
	
	
	//sql = "insert into clients (email, fname, lname,address, password1, Date1, Date2, Date3) values " 
	//		+ " ('" + email + "', '" + fname + "', '" + lname + "', '" + address + "', '" + password1 + "', '" + passdate1 + "', '" + passdate2 + "', '" + passdate3 + "')"
	//		;
	//rowCount = myDB.executeUpdate(sql);
		 
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside CPFPSQLServlet doPost block,");
		System.out.println("The code should not go here.");
	}

}

