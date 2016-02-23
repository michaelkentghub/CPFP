

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Servlet implementation class persistence
 */
@WebServlet("/CreateServlet")
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TEMPORARYERROR = 
			"Sorry, we are experiencing a temporary error...please retry later";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServlet() {
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
		
		
		System.out.println("We are here in CreateServlet");
		out.println("Here we are in CreateServlet");
	
		

		
		// Make connection using LocalMySQL.java to set up customer database and table client
		// since there is no need for these to be prepared Statments
		// open MySQL db
				//out.println("right before myDB");
				LocalMySQL myDB = new LocalMySQL();
				//out.println("myDB = " + myDB);
				if (! myDB.isAvailable()) {
					out.println("CPFPSQLServlet line 95  failed to get DB");
					return;
				}
				out.println("right after myDB");
		
				ResultSet result;  // results of executeQuery() 
				String sql;  // SQL to execute 
				int rowCount;  // number of rows affected from executeUpdate()
				
				
				
				
		
				try {
					myDB.executeUpdate("drop database if exists customer");
					myDB.executeUpdate("create database customer");
					
					myDB.executeUpdate("use customer");
					
					myDB.executeUpdate("drop table if exists clients");
					
					myDB.executeUpdate("create table clients" 
							+ "(id int auto_increment primary key,"
							+ "email varchar(50),"
							+ "fname varchar(30),"
							+ "lname varchar(30),"
							+ "address varchar(30),"
							+ "password1 varchar(30),"
							+ "Date1 varchar(20),"
							+ "Date2 varchar(20),"
							+ "Date3 varchar(20),"
							+ "feedback varchar(20000),"
							+ "fb varchar(20))");
					
					
					
					
					// SQL Database Connection for Prepared Statements
					
					String myPSDB = "jdbc:mysql://localhost:3306/customer";  // local connection string
					String user = "root";  // local user
					String pswd = "dangelacath57"; // local MySQL root passwd
					
					Connection conn = null;

					//make connection
					try 
					{
						Class.forName("com.mysql.jdbc.Driver");
						conn = DriverManager.getConnection(myPSDB, user, pswd);
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
					}
					
					//System.out.println("conn = " + conn);
					
	
										
					// insert (seed) some data 
					
					
				    String email = "mrkent57@gmail.com";
					String fname = "Michael";
					String lname = "Kent";
					String address = "8420 Smith Drive.";
					String password1 = "dk2020";
					String Date1 = "2000-01-01";
					String Date2 = "2000-01-01";
					String Date3 = "2000-01-01";
					String feedback = "No feedback entered yet";
					String fb = "notyet";
;					
					sql = "insert into clients (email, fname, lname, address, password1, Date1, Date2, Date3, feedback, fb) values  (?,?,?,?,?,?,?,?,?,?)";
					PreparedStatement ps2 = conn.prepareStatement(sql);
					
					ps2.setString(1, email);
					ps2.setString(2, fname);
					ps2.setString(3, lname);
					ps2.setString(4, address);
					ps2.setString(5, password1);
					ps2.setString(6, Date1);
					ps2.setString(7, Date2);
					ps2.setString(8, Date3);
					ps2.setString(9, feedback);
					ps2.setString(10, fb);
					ps2.executeUpdate();
					
					
					// Make connection using LocalMySQL.java to set up customer database and table client
					// Using LocalMySQL.java myDB since there is no need for these to be prepared Statements.
					
					
					
					sql = "select * from clients where email like 'mrkent57@gmail.com%'";
					result = myDB.executeQuery(sql);
					
					while (result.next()) {
						out.println("<br>" + result.getString("email") );
						out.println("<br>" + result.getString("fname") );
						out.println("<br>" + result.getString("lname") );
						out.println("<br>" + result.getString("address") );
						out.println("<br>" + result.getString("password1") );
						out.println("<br>" + result.getString("Date1") );
						out.println("<br>" + result.getString("Date2") );
						out.println("<br>" + result.getString("Date3") );
						out.println("<br>" + result.getString("feedback") );
						out.println("<br>" + result.getString("fb") );
						//out.println("<br>good up until this point while");
					}
					
					
					
				
					
					
				}
				
				catch (SQLException ex) {
					out.println("SQL Exception");
					myDB.printTrace(ex);
				}
				
				out.println("<br>good up until this point F");
				
				
				String url = "/LoginOrRegister.html";
				getServletContext().getRequestDispatcher(url).forward(request, response);		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
