

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.Serializable;


/**
 * Servlet implementation class persistence
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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

		out.println("We are here in the Register Servlet");
		
		
		String numcards = null;
		//System.out.println("numcards = " + numcards);
		
		numcards = request.getParameter("numcards");
		//System.out.println("numcards = " + numcards);
		
		int NumCards = Integer.valueOf(numcards);
		//System.out.println("NumCards = " + NumCards);

		
		System.out.println("We are here Mike");
		 
		String user = "Hello There";
		request.setAttribute("User",user);
		
		
		
		
		
		
		String url = "/CPFP.html";
		getServletContext().getRequestDispatcher(url).forward(request, response);


		 
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
