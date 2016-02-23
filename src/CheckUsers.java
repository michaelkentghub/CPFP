

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Servlet implementation class persistence
 */
@WebServlet("/CheckUsers")
public class CheckUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TEMPORARYERROR = 
			"Sorry, we are experiencing a temporary error...please retry later";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckUsers() {
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
		
		
		System.out.println("We are checking user accounts");
	
		
	
		
		// Make connection
		// open MySQL db
				//System.out.println("right before myDB");
				LocalMySQL myDB = new LocalMySQL();
				//System.out.println("myDB = " + myDB);
				if (! myDB.isAvailable()) {
					System.out.println("CPFPSQLServlet line 95  failed to get DB");
					return;
				}
				//System.out.println("right after myDB");
		
				ResultSet result;  // results of executeQuery() 
				String sql;  // SQL to execute 
				int rowCount;  // number of rows affected from executeUpdate()
				
				
				
		
				try {
					
					
					myDB.executeUpdate("use customer");
					
					// Print out all table values.
					
					sql = "select * from clients";
					
					result = myDB.executeQuery(sql);
					
					out.println("--------------------------------");
					while (result.next()) {
						String a = result.getString("email");
						out.println("<br>email     " + a);
						String e = result.getString("password1");
						out.println("<br>password  " + e);
						String b = result.getString("fname");
						out.println("<br>fname     " + b);
						String c = result.getString("lname");
						out.println("<br>lname     " + c);
						String d = result.getString("address");
						out.println("<br>address   " + d);
						String f = result.getString("Date1");
						out.println("<br>Date1     " + f);
						String g = result.getString("Date2");
						out.println("<br>Date2     " + g);
						String h = result.getString("Date3");
						out.println("<br>Date3     " + h);
						out.println("<br>--------------------------------");
					}
					
				}  // end of try block
				
				catch (SQLException ex) {
					System.out.println("In the CheckUsers SQLException catch block");
					myDB.printTrace(ex);
				}
				

	System.out.println("This is the end of CheckUsers doGet block,");
	System.out.println("CheckUsers program completed.");
	//System.exit(0);
		 
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside CPFPSQLServlet doPost block,");
		System.out.println("The code should not go here.");
	}

}

