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
import java.sql.SQLException;

/**
 * Servlet implementation class NewAdd
 */
public class NewAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc=request.getServletContext();
		PrintWriter pr=response.getWriter();
		String save=request.getParameter("save");
		String cancel=request.getParameter("cancel");
		RequestDispatcher rd=request.getRequestDispatcher("/Index.html");
		if(save!=null)
		{
			String firstname=(String)sc.getAttribute("firstname");
			String lastname=(String)sc.getAttribute("lastname");
			String empid=(String)sc.getAttribute("empid");
			String emailid=(String)sc.getAttribute("email");
			Connection con=(Connection)sc.getAttribute("oracle");
			String newfirstname=request.getParameter("newfirstname");
			String newlastname=request.getParameter("newlastname");
			String newempid=request.getParameter("newempid");
			String newemailid=request.getParameter("newemail");
			pr.println("<html><body bgcolor=grey text=yellow><center>");
			try
			{
			  PreparedStatement pst=con.prepareStatement("update employeedetails set firstname=?,lastname=?,empid=?,emailid=? where firstname=? and lastname=? and empid=? and emailid=?");
			  pst.setString(1,newfirstname);
			  pst.setString(2,newlastname);
			  pst.setString(3,newempid);
			  pst.setString(4,newemailid);
			  pst.setString(5,firstname);
			  pst.setString(6,lastname);
			  pst.setString(7,empid);
			  pst.setString(8,emailid);
			  int c=pst.executeUpdate();
			  if(c>0)
			  {
				  pr.println("New value updated successfully");
				  pr.println("</center></body></html>");
				  rd.include(request, response);
			  }
			  else
			  {
				  pr.println("Value update unsuccessfully");
				  pr.println("</center></body></html>");
				  rd.include(request, response);
			  }
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		if(cancel!=null)
		{
			pr.println("<html><body bgcolor=grey text=yellow><center>");
			pr.println("Cancelled");
		    pr.println("</center></body></html>");
		    rd.include(request, response);
		}
	}

}
