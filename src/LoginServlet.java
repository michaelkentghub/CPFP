import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.NullPointerException;

/**
 * Servlet implementation class persistence
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		
		session.setAttribute("ZEROOPEN", "no");
		session.setAttribute("ONEOPEN", "no");
		session.setAttribute("TWOOPEN", "no");
		session.setAttribute("THREEOPEN", "no");

		System.out.println("We are here in LoginServlet");
		out.println("We are here in LoginServlet");

		String email = null;
		email = request.getParameter("email");
		System.out.println("email passed in from html is " + email);
		out.println("email passed in from html is " + email);

		String password = null;
		password = request.getParameter("password");
		System.out.println("password passed in from html is  " + password);
		out.println("password passed in from html is  " + password);

		String feedback = null;
		String fb = null;

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

			

			sql = "select * from clients where email like '" + email + "'";
			System.out.println("email used in first LoginServlet sql statement is " + email);
			System.out.println("password used in first LoginServlet sql statement is " + password);
			out.println("email used in first LoginServlet sql statement is " + email);
			out.println("password used in first LoginServlet sql statement is " + password);
			
			result4 = myDB.executeQuery(sql);
			System.out.println("entering while statement");
			out.println("entering while statement");
			while (result4.next()) {
				eemail = result4.getString("email");
				System.out.println("LoginServlet result4 email from table (eemail) is " + eemail);
				out.println("LoginServlet result4 email from table (eemail) is " + eemail);
				
				pword = result4.getString("password1");
				System.out.println("LoginServlet result4 password from table (pword) is " + pword);
				out.println("LoginServlet result4 password from table (pword) is " + pword);
				
				Date1 = result4.getString("Date1");
				System.out.println("LoginServlet result4 Date1 from table is " + Date1);
				out.println("LoginServlet result4 Date1 from table is " + Date1);
				
				Date2 = result4.getString("Date2");
				System.out.println("LoginServlet result4 Date2 from table is " + Date2);
				out.println("LoginServlet result4 Date2 from table is " + Date2);
				
				Date3 = result4.getString("Date3");
				System.out.println("LoginServlet result4 Date3 from table is " + Date3);
				out.println("LoginServlet result4 Date3 from table is " + Date3);

				fname = result4.getString("fname");
				System.out.println("fname = " + fname);
				out.println("fname = " + fname);
				session.setAttribute("FNAME", fname);
				
				lname = result4.getString("lname");
				System.out.println("LNAME = " + lname);
				out.println("LNAME = " + lname);
				session.setAttribute("LNAME", lname);
				
				address = result4.getString("address");
				System.out.println("Address = " + address);
				System.out.println("Address = " + address);
				session.setAttribute("ADDRESS", address);
				
				feedback = result4.getString("feedback");
				System.out.println("feedback = " + feedback);
				out.println("feedback = " + feedback);
				
				fb = result4.getString("fb");
				System.out.println("fb = " + fb);
				out.println("fb = " + fb);
				
				System.out.println("End of while loop");
				out.println("End of while loop");
			} // while statement

			System.out.println("Just left while loop");
			out.println("Just left while loop");
			
			
			
			
			
			if (eemail == null)
			{
				System.out.println("LoginServlet eemail = null");
				out.println("LoginServlet eemail = null");
			}
			

			System.out.println(" At end of first try block.");
			out.println(" At end of first try block.");
		} // end of first try block

		catch (SQLException ex) {
			System.out.println("SQL Exception LoginServlet first try/catch");
			out.println("SQL Exception LoginServlet first try/catch");
			myDB.printTrace(ex);
		}

		String top = null;
		String loginerror;
		
		//eemail = null;
		System.out.println("eemail = " + eemail);
		out.println("eemail = " + eemail);
		if(eemail == null)
		{
			System.out.println("email not found ");
			System.out.println("Re-directing to LoginorRegisterBademail.html");
			out.println("email not found ");
			out.println("Re-directing to LoginorRegisterBademail.html");
			String url = "/LoginorRegisterBademail.html";
			getServletContext().getRequestDispatcher(url).forward(request, response);	
		}
		
		try {

			System.out.println("Beginning of second try block.");
			System.out.println("email (from user) at comparison is " + email);
			System.out.println("eemail (from table) at comparison is " + eemail);
			System.out.println("password (from user) at comparison is " + password);
			System.out.println("pword (from table) at comparison is " + pword);
			out.println("Beginning of second try block.");
			out.println("email (from user) at comparison is " + email);
			out.println("eemail (from table) at comparison is " + eemail);
			out.println("password (from user) at comparison is " + password);
			out.println("pword (from table) at comparison is " + pword);

			if (!email.equals(eemail)) {
				System.out.println("email and eemail do not match ");
				System.out.println("Re-directing to regbademail.html");
				out.println("email and eemail do not match ");
				out.println("Re-directing to regbademail.html");
				String url = "/regbademail.html";
				getServletContext().getRequestDispatcher(url).forward(request, response);
			}

			if (!pword.equals(password)) {
				System.out.println("password and pword don't match");
				System.out.println("Re-directing to password.html");
				out.println("password and pword don't match");
				out.println("Re-directing to password.html");
				String url = "/password.html";
				getServletContext().getRequestDispatcher(url).forward(request, response);
			}

			if (pword.equals(password)) {
				System.out.println("Password DOES match pword");
				out.println("Password DOES match pword");
				session.setAttribute("PWORD", password);
				System.out.println("In the comparison being done in LoginServlet, the dates from the table are " + Date1 + Date2 + Date3);
				System.out.println("In the comparison being done in LoginServlet, the dates from the table are " + Date1 + Date2 + Date3);
				String dat = "";
				

			
                
                
                	session.setAttribute("FB", fb );
                	System.out.println("setAttribute FB is " + fb);
                	out.println("setAttribute FB is " + fb);
                	session.setAttribute("FEEDBACK", feedback );
                	System.out.println("setAttribute FEEDBACK is " + feedback);
                	out.println("setAttribute FEEDBACK is " + feedback);
                	
                	System.out.println("Date1 is " + Date1);
                	System.out.println("Date2 is " + Date2);
                	System.out.println("Date3 is " + Date3);
                	out.println("Date1 is " + Date1);
                	out.println("Date2 is " + Date2);
                	out.println("Date3 is " + Date3);
				

				if (Date1.equals("2000-01-01")) {
					opencount++;
					date1open = "open";
					session.setAttribute("DATE1OPEN", date1open);
					session.setAttribute("DATE1", "");
					System.out.println("Date1 is NOT an appointment.");
					out.println("Date1 is NOT an appointment.");

				} else {
					date1open = "closed";
					session.setAttribute("DATE1OPEN", date1open);
					session.setAttribute("DATE1", Date1);
					System.out.println("Date1 IS an appointment.");
					System.out.println("Date1 is " + Date1);
					out.println("Date1 IS an appointment.");
					out.println("Date1 is " + Date1);
				}
				

				if (Date2.equals("2000-01-01")) {
					opencount++;
					date2open = "open";
					session.setAttribute("DATE2OPEN", date2open);
					session.setAttribute("DATE2", "");
					System.out.println("Date2 is NOT an appointment.");
					out.println("Date2 is NOT an appointment.");
				} else {
					date2open = "closed";
					session.setAttribute("DATE2OPEN", date2open);
					session.setAttribute("DATE2", Date2);
					System.out.println("Date2 IS an appointment.");
					System.out.println("Date2 is " + Date2);
					out.println("Date2 IS an appointment.");
					out.println("Date2 is " + Date2);
				}

				
				if (Date3.equals("2000-01-01")) {
					date3open = "open";
					session.setAttribute("DATE3OPEN", date3open);
					opencount++;
					session.setAttribute("DATE3", "");
					System.out.println("Date3 is NOT an appointment.");
					out.println("Date3 is NOT an appointment.");
				} else {
					date3open = "closed";
					session.setAttribute("DATE3OPEN", date3open);
					session.setAttribute("DATE3", Date3);
					System.out.println("Date3 IS an appointment.");
					System.out.println("Date3 is " + Date3);
					out.println("Date3 IS an appointment.");
					out.println("Date3 is " + Date3);
				}

				System.out.println("Do we make it to here?");
				out.println("Do we make it to here?");
				session.setAttribute("EMAIL", email); 
				System.out.println("LoginServlet set email attribute");
				System.out.println("End of LoginServlet session values");
				out.println("LoginServlet set email attribute");
				out.println("End of LoginServlet session values");
				
				String DATE1 = (String)session.getAttribute("DATE1");
				System.out.println("DATE1 passed in from the session is " + DATE1);
				out.println("DATE1 passed in from the session is " + DATE1);
				String DATE2 = (String)session.getAttribute("DATE2");
				System.out.println("DATE2 passed in from the session is " + DATE2);
				out.println("DATE2 passed in from the session is " + DATE2);
				String DATE3 = (String)session.getAttribute("DATE3");
				System.out.println("DATE3 passed in from the session is " + DATE3);
				out.println("DATE3 passed in from the session is " + DATE3);
				
				String DATE1OPEN = (String)session.getAttribute("DATE1OPEN");
				System.out.println("DATE1OPEN passed in from the session is " + DATE1OPEN);
				out.println("DATE1OPEN passed in from the session is " + DATE1OPEN);
				String DATE2OPEN = (String)session.getAttribute("DATE2OPEN");
				System.out.println("DATE2OPEN passed in from the session is " + DATE2OPEN);
				out.println("DATE2OPEN passed in from the session is " + DATE2OPEN);
				String DATE3OPEN = (String)session.getAttribute("DATE3OPEN");
				System.out.println("DATE3OPEN passed in from the session is " + DATE3OPEN);
				out.println("DATE3OPEN passed in from the session is " + DATE3OPEN);
				
				
				
                String notyet = "notyet";
                String yes = "yes";
                
				if (fb.equals(notyet))
				{
					System.out.println("fb = notyet");
					out.println("fb = notyet");
					if (opencount == 0) {
						session.setAttribute("ZEROOPEN", "yes");
						System.out.println("The maximum of three appts has been made");
						System.out.println("<br>Leaving LoginServlet to go to havezerooopen.jsp");
						out.println("The maximum of three appts has been made");
						out.println("<br>Leaving LoginServlet to go to havezerooopen.jsp");
						String url = "/havezeroopen.jsp";
						getServletContext().getRequestDispatcher(url).forward(request, response);
					}
					else if (opencount == 1) {
						session.setAttribute("ONEOPEN", "yes");
						System.out.println("Two appts have been made");
						System.out.println("<br>Leaving LoginServlet to go to haveoneopen.jsp");
						out.println("Two appts have been made");
						out.println("<br>Leaving LoginServlet to go to haveoneopen.jsp");
						String url = "/haveoneopen.jsp";
						getServletContext().getRequestDispatcher(url).forward(request, response);
					}

					else if (opencount == 2) {
						session.setAttribute("TWOOPEN", "yes");
						System.out.println("One appt has been made");
						System.out.println("<br>Leaving LoginServlet to go to havetwoopen.jsp");
						out.println("One appt has been made");
						out.println("<br>Leaving LoginServlet to go to havetwoopen.jsp");
						String url = "/havetwoopen.jsp";
						getServletContext().getRequestDispatcher(url).forward(request, response);
					}

					else if (opencount == 3) {
						session.setAttribute("THREEOPEN", "yes");
						System.out.println("no appts have been made");
						System.out.println("<br>Leaving LoginServlet to go to havethreeopen.jsp");
						out.println("no appts have been made");
						out.println("<br>Leaving LoginServlet to go to havethreeopen.jsp");
						String url = "/havethreeopen.jsp";
						getServletContext().getRequestDispatcher(url).forward(request, response);
					}

					else {
						System.out.println("fb = notyet.  Something has gone wrong with opencount in LoginServlet.");
						out.println("fb = notyet.  Something has gone wrong with opencount in LoginServlet.");
						System.exit(0);
					}
				}  // if (fb == notyet)
				
				
				else if (fb.equals(yes))
				{
					System.out.println("fb = yes");
					out.println("fb = yes");
					if (opencount == 0) {
						session.setAttribute("ZEROOPEN", "yes");
						System.out.println("The maximum of three appts has been made");
						System.out.println("<br>Leaving LoginServlet to go to havezerooopenFB.jsp");
						out.println("The maximum of three appts has been made");
						out.println("<br>Leaving LoginServlet to go to havezerooopenFB.jsp");
						String url = "/havezeroopenFB.jsp";
						getServletContext().getRequestDispatcher(url).forward(request, response);
					}
					else if (opencount == 1) {
						session.setAttribute("ONEOPEN", "yes");
						System.out.println("Two appts have been made");
						System.out.println("<br>Leaving LoginServlet to go to haveoneopenFB.jsp");
						out.println("Two appts have been made");
						out.println("<br>Leaving LoginServlet to go to haveoneopenFB.jsp");
						String url = "/haveoneopenFB.jsp";
						getServletContext().getRequestDispatcher(url).forward(request, response);
					}

					else if (opencount == 2) {
						session.setAttribute("TWOOPEN", "yes");
						System.out.println("One appt has been made");
						System.out.println("<br>Leaving LoginServlet to go to havetwoopenFB.jsp");
						out.println("One appt has been made");
						out.println("<br>Leaving LoginServlet to go to havetwoopenFB.jsp");
						String url = "/havetwoopenFB.jsp";
						getServletContext().getRequestDispatcher(url).forward(request, response);
					}

					else if (opencount == 3) {
						session.setAttribute("THREEOPEN", "yes");
						System.out.println("no appts have been made");
						System.out.println("<br>Leaving LoginServlet to go to havethreeopenFB.jsp");
						out.println("no appts have been made");
						out.println("<br>Leaving LoginServlet to go to havethreeopenFB.jsp");
						String url = "/havethreeopenFB.jsp";
						getServletContext().getRequestDispatcher(url).forward(request, response);
					}

					else {
						System.out.println("fb = yes.  Something has gone wrong with opencount in LoginServlet.");
						out.println("fb = yes.  Something has gone wrong with opencount in LoginServlet.");
						System.exit(0);
					}
				}  // else if (fb == yes)
				
				else {
					System.out.println("Something has gone wrong with fb in LoginServlet.");
					out.println("Something has gone wrong with fb in LoginServlet.");
				}
				
				
				
				
				
				

			} // if (pword.equals(password))

			
			System.out.println("End of second try statement.");
			out.println("End of second try statement.");
			
		}

		catch (java.lang.NullPointerException ex) {
			System.out.println("Caught exception in second try/catch in LoginServlet ");
			out.println("Caught exception in second try/catch in LoginServlet ");
		}

		System.out.println("We are at end of LoginServlet doGet. ");
		out.println("We are at end of LoginServlet doGet. ");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("We are in LoginServlet doPost.  We should not be here.");
	}

}
