package pack1;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class remove
 */
public class remove extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public remove() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pr=response.getWriter();
		boolean b=false;
		ServletContext sc=request.getServletContext();
		Connection con=(Connection)sc.getAttribute("oracle");
		String firstname=(String) sc.getAttribute("firstname");
		String lastname=(String) sc.getAttribute("lastname");
		String empid=(String) sc.getAttribute("empid");
		String email=(String) sc.getAttribute("email");
		pr.println("<html><body><center>");
		try {
			PreparedStatement pst=con.prepareStatement("select * from employeedetails where firstname=? and lastname=? and empid=? and emailid=?");
			pst.setString(1,firstname);
			pst.setString(2,lastname);
			pst.setString(3,empid);
			pst.setString(4,email);
			ResultSet rs=pst.executeQuery();
			b=rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(b)
		{
			try {
				PreparedStatement pst=con.prepareStatement("delete from employeedetails where firstname=? and lastname=? and empid=? and emailid=?");
				pst.setString(1,firstname);
				pst.setString(2,lastname);
				pst.setString(3,empid);
				pst.setString(4,email);
				int c=pst.executeUpdate();
				if(c>0)
				{
					pr.println("Deleted Successfully");
				}
				else
				{
					pr.println("Deleted Unuccessfully");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			pr.println("Employee not exist");	
		}
		RequestDispatcher rd=request.getRequestDispatcher("/Index.html");
		rd.include(request,response);
		pr.println("<html><body><center>");
	}

}
