

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
@WebServlet("/CPFPSQLServlet")
public class CPFPSQLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TEMPORARYERROR = 
			"Sorry, we are experiencing a temporary error...please retry later";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CPFPSQLServlet() {
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
		
		
		System.out.println("-----------We are here at the beginning of CPFPSQLServlet");
		out.println("---------------------We are here at the beginning of CPFPSQLServlet");
	
		HttpSession session = request.getSession();
		
		
		
		
		String fname = null;
		fname = request.getParameter("fname");
		out.println("fname = " + fname);
		System.out.println("fname = " + fname);
		session.setAttribute("FNAME", fname);
		String FNAMETEST = (String)session.getAttribute("FNAME");
		out.println("FNAMETEST = " + FNAMETEST);
		out.println("FNAMETEST = " + FNAMETEST);
		
		String lname = null;
		lname = request.getParameter("lname");
		out.println("lname = " + lname);
		System.out.println("lname = " + lname);
		session.setAttribute("LNAME", lname);
		String LNAMETEST = (String)session.getAttribute("LNAME");
		out.println("LNAMETEST = " + LNAMETEST);
		
		String address = null;
		address = request.getParameter("address");
		out.println("address = " + address);
		session.setAttribute("ADDRESS", address);
		String ADDRESSTEST = (String)session.getAttribute("ADDRESS");
		out.println("ADDRESSTEST = " + ADDRESSTEST);
		
		String email = null;
		email = request.getParameter("email");
		out.println("email = " + email);
		session.setAttribute("EMAIL", email);
		String EMAILTEST = (String)session.getAttribute("EMAIL");
		out.println("EMAILTEST = " + EMAILTEST);
		
		String password1 = null;
		password1 = request.getParameter("password1");
		out.println("password1 = " + password1);
		session.setAttribute("PASSWORD1", password1);
		String PASSWORD1 = (String)session.getAttribute("PASSWORD1");
		out.println("PASSWORD1 = " + PASSWORD1);
		
		String password2 = null;
		password2 = request.getParameter("password2");
		out.println("password2 = " + password2);
		session.setAttribute("PASSWORD2", password2);
		String PASSWORD2 = (String)session.getAttribute("PASSWORD2");
		out.println("PASSWORD2 = " + PASSWORD2);
		
		String passdate1 = null;
		passdate1 = request.getParameter("passdate1");
		out.println("passdate1 = " + passdate1);
		session.setAttribute("PASSDATE1", passdate1);
		String PASSDATE1 = (String)session.getAttribute("PASSDATE1");
		out.println("PASSDATE1 = " + PASSDATE1);
		
		String passdate2 = null;
		passdate2 = request.getParameter("passdate2");
		out.println("passdate2 = " + passdate2);
		session.setAttribute("PASSDATE2", passdate2);
		String PASSDATE2 = (String)session.getAttribute("PASSDATE2");
		out.println("PASSDATE2 = " + PASSDATE2);
		
		String passdate3 = null;
		passdate3 = request.getParameter("passdate3");
		out.println("passdate3 = " + passdate3);
		session.setAttribute("PASSDATE3", passdate3);
		String PASSDATE3 = (String)session.getAttribute("PASSDATE3");
		out.println("PASSDATE3 = " + PASSDATE3);
		
		
		out.println("This concludes the printout of all the user values passed into CPFPSQLServlet");
		
		
		
		String Date = "2000-01-01";   // default value before customer schedules an appt.
		
		
		
		
		if (!email.contains("@") || !email.contains("."))
		{
			out.println("bad email = " + email);
			out.println("Re-directing to regbademail.html");
			String url = "/regbademail.html";
			getServletContext().getRequestDispatcher(url).forward(request, response);
		}
		
		
		if (!(password1.equals(password2)))
		{
			out.println("Passwords do not match: " + password1 + " " + password2);
	    	out.println("Re-directing to RegisterPasswordsDoNotMatch.jsp");
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
			out.println("<br>Can't load JDBC driver");  // log error to console
			ex.printStackTrace();
		}
		catch (SQLException ex) 
		{
			out.println("<br>SQLException");  // log error to console
			ex.printStackTrace();
		}

		if (conn == null) 
		{
			out.println("CPFPSQLServlet failed to get connection");
		}
		
		out.println("conn = " + conn);
		
		
		
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
						out.println("Values put in the table by CPFPSQLServlet:");
						String a = result.getString("email");
						out.println("email is " + a);
						String e = result.getString("password1");
						out.println("password is " + e);
						String b = result.getString("fname");
						out.println("fname is " + b);
						String c = result.getString("lname");
						out.println("lname is " + c);
						String d = result.getString("address");
						out.println("address is " + d);
						String f = result.getString("Date1");
						out.println("Date1 is " + f);
						String g = result.getString("Date2");
						out.println("Date2 is " + g);
						String h = result.getString("Date3");
						out.println("Date3 is " + h);
						String i = result.getString("feedback");
						out.println("feedback is " + i);
						String j = result.getString("fb");
						out.println("fb is " + j);
					}
					
					
					
				}  // end of try block
				
				catch (SQLException se) {
					out.println("In the CPFPSQLServelet SQLException catch block");
					//System.exit(0);   // For debug purposes only.  Will remove in final code.  
					                  // If SQL statements fail, I don't want the program to proceed past this point.
				se.printStackTrace();
				}
				
				
				//System.out.println("Point I");
				// Have successfully registered.  Set Feedback state to "not yet".
				//session.setAttribute("FB", "notyet");
				String url = "/regsuccesslogin.html";
				out.println("This is the exit point in CPFPSQLServlet where we branch to regsuccesslogin");
				System.out.println("At this point email is " + email);
				getServletContext().getRequestDispatcher(url).forward(request, response);
				
				

	
		 
	}  // end of doGet
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside CPFPSQLServlet doPost block,");
		System.out.println("The code should not go here.");
	}

}

