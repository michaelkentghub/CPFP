

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RegChangeTransferServlet
 */
@WebServlet("/SaveRegChangeTransferServlet")
public class SaveRegChangeTransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveRegChangeTransferServlet() {
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

		System.out.println("We are here in RegChangeTransferServlet");

        HttpSession session = request.getSession();
		
		String email = (String)session.getAttribute("EMAIL");
		System.out.println("email passed in from the session is " + email);

		

		// Make connection
		// open MySQL db
		//System.out.println("right before myDB");
		LocalMySQL myDB = new LocalMySQL();
		//System.out.println("myDB = " + myDB);
		if (!myDB.isAvailable()) {
			System.out.println("LoginServlet line 67  failed to get DB");
			return;
		}
		//System.out.println("right after myDB");
		//System.out.println("myDB = " + myDB);

		System.out.println("connection to db successfull");

		ResultSet result; // results of executeQuery()
		ResultSet result3; // results of executeQuery()
		ResultSet result4;
		String sql; // SQL to execute
		int rowCount; // number of rows affected from executeUpdate()
		String eemail = null;
		String fname = null;
		String lname = null;
		String address = null;
		String password1 = null;
		String pword = null; // This is the password stored in the table.
		String Date1 = null;
		String Date2 = null;
		String Date3 = null;int opencount = 0;
		String date1open = null;
		String date2open = null;
		String date3open = null;

		try {
			// If customer is logging in, he has already registered
			// and the schema has been created.
			System.out.println("At beginning of first try statement");
			myDB.executeUpdate("use customer");

			/*
			 * // Saved code
			 * 
			 * //myDB.executeUpdate("use table clients");
			 * 
			 * //sql = "select * from clients"; //result =
			 * myDB.executeQuery(sql);
			 * 
			 * 
			 * //while (result.next()) { // out.println("<br>" +
			 * result.getString("email") ); // out.println("<br>" +
			 * result.getString("fname") ); // out.println("<br>" +
			 * result.getString("lname") ); // out.println("<br>" +
			 * result.getString("address") ); // out.println("<br>" +
			 * result.getString("password1") ); // out.println("<br>" +
			 * result.getString("Date1") ); // out.println("<br>" +
			 * result.getString("Date2") ); // out.println("<br>" +
			 * result.getString("Date3") ); //
			 * out.println("<br>good up until this point while"); // }
			 * 
			 * //out.println("email = " + eemail);
			 * 
			 * //sql = "select * from clients where email like 'mrkent%'";
			 * 
			 * //sql =
			 * "select * from clients where email like 'mrkent57@gmail.com%'";
			 * 
			 * 
			 * //System.out.println("Point G"); //sql =
			 * "select * from clients where email like '" + eemail + "'";
			 * //out.println("<br>good up until this point A7"); //result3 =
			 * myDB.executeQuery(sql);
			 */// Saved code

			sql = "select * from clients where email like '" + email + "'";
			System.out.println("email used in first RegChangeTransferServlet sql statement is " + email);
			
			
			result4 = myDB.executeQuery(sql);
			System.out.println("entering while statement");
			while (result4.next()) {
				pword = result4.getString("password1");
				System.out .println("password from table (pword) is " + pword);
				session.setAttribute("PWORD", pword );
				Date1 = result4.getString("Date1");
				System.out.println("Date1 from table is " + Date1);
				Date2 = result4.getString("Date2");
				System.out.println("Date2 from table is " + Date2);
				Date3 = result4.getString("Date3");
				System.out.println("Date3 from table is " + Date3);
				fname = result4.getString("fname");
				System.out.println("fname = " + fname);
				session.setAttribute("FNAME", fname );
				lname = result4.getString("lname");
				System.out.println("lname = " + lname);
				session.setAttribute("LNAME", lname );
				address = result4.getString("address");
				System.out.println("address = " + address);
				session.setAttribute("ADDRESS", address );
				System.out.println("End of while loop");
			} // while statement

			System.out.println("Just left while loop");
			
			System.out.println(" At end of try block.");
		} // end of first try block

		catch (SQLException ex) {
			System.out.println("SQL Exception LoginServlet first try/catch");
			myDB.printTrace(ex);
		}

		

				String url = "/RegisterChange.jsp";
				getServletContext().getRequestDispatcher(url).forward(request, response);
				
				


		System.out.println("We are at end of RegChangeTransferServlet doGet. ");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("We are in RegChangeTransferServlet doPost.  We should not be here.");
	}

}
