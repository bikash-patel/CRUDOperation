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
 * Servlet implementation class update
 */
public class update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public update() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc=request.getServletContext();
		PrintWriter pr=response.getWriter();
		Connection con=(Connection)sc.getAttribute("oracle");
		String firstname=(String)sc.getAttribute("firstname");
		String lastname=(String)sc.getAttribute("lastname");
		String empid=(String)sc.getAttribute("empid");
		String emailid=(String)sc.getAttribute("email");
		try
		{
			PreparedStatement pst=con.prepareStatement("select * from employeedetails where firstname=? and lastname=? and empid=? and emailid=?");
			pst.setString(1,firstname);
			pst.setString(2,lastname);
			pst.setString(3,empid);
			pst.setString(4,emailid);
			ResultSet rs=pst.executeQuery();
			pr.println("<html><body bgcolor=green text=black><center>");
			if(rs.next())
			{
				pr.println("<h1>Edit the details</h1>");
				RequestDispatcher ds=request.getRequestDispatcher("/newadd.html");
				ds.include(request, response);
			}
			else
			{
				pr.println("Wrong credntial!");
				RequestDispatcher ds=request.getRequestDispatcher("/index.html");
				ds.include(request, response);
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

}
