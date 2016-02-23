import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Most of this code came from Peter Isburgh.
 * It separates MySQL database concerns from servlet business logic.
 *
 */
public class LocalMySQL {

	private Connection conn;  // database Connection
	private String myDB = "jdbc:mysql://localhost:3306";  // local connection string
	private String user = "root";  // local user
	private String pswd = "dangelacath57"; // local MySQL root passwd


	public LocalMySQL() {
		//System.out.println("right before openDB");
		this.conn = openDB();  // if needed, user can alter userid/password and call openDB()
		//System.out.println("right after openDB");
	}
	
	// @return true if DB is available
			boolean isAvailable(){
				if (conn != null) {
					return true;
				}
				else {
					return false;
				}	
			}
			
			Connection connString() {
				return conn;
			}
			
	

	
	public ResultSet executeQuery(String query) throws SQLException {
		PreparedStatement ps = this.conn.prepareStatement(query);
		ResultSet users = ps.executeQuery();
		System.out.println("using executeQuery prepared statement");
		return users;
	}


	
	
	public int executeUpdate(String query) throws SQLException {
		PreparedStatement ps = this.conn.prepareStatement(query);
		int count = ps.executeUpdate();
		System.out.println("using executeUpdate prepared statement");
		return count;
	}


	
	
	
	
	public Connection openDB() {
		Connection conn = null;

		try {
			//System.out.println("We are in openDB1 try block");
			//System.out.println("myDB user password " + myDB + " " + user + " " + pswd);
			Class.forName("com.mysql.jdbc.Driver");
			//System.out.println("We are in openDB2 try block");
			conn = DriverManager.getConnection(myDB, user, pswd);
			//System.out.println("We are in openDB3 try block");
		}
		catch (ClassNotFoundException ex) {
			System.out.println("<br>Can't load JDBC driver");  // log error to console
		}
		catch (SQLException ex) {
			System.out.println("<br>SQLException printTrace");  // log error to console
			printTrace(ex);
		}

		return conn;  
	}

	
	public void printTrace(SQLException ex) {
		for (Throwable t : ex) {
			t.printStackTrace(System.out);  // stack trace to console
		}
	}

}
