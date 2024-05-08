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
 * Servlet implementation class add
 */
public class add extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public add() {
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
         ResultSet rs;
         boolean b = false;
         String firstname=(String)sc.getAttribute("firstname");
         String lastname=(String)sc.getAttribute("lastname");
         String empid=(String)sc.getAttribute("empid");
         String email=(String)sc.getAttribute("email");
         pr.println("<html><body bgcolor=yellow text=green><center>");
         RequestDispatcher rd=request.getRequestDispatcher("/Index.html");
         if(empid.length()!=8)
         {
        	 pr.println("Employee Id must contain 8 digit number");
        	 pr.println("</center></body></html>");
        	 rd.include(request, response);
         }
         try
         {
        	 PreparedStatement pst=con.prepareStatement("select * from employeedetails where empid=?");
        	 pst.setString(1, empid);
        	 rs=pst.executeQuery();
        	 b=rs.next();
         }
         catch(SQLException e)
         {
        	e.printStackTrace(); 
         }
         if(b)
         {
        	 pr.println("Employee Id already exist");
        	 pr.println("</center></body></html>");
        	 rd.include(request, response);
         }
         else
         {
        	 try {
     			PreparedStatement pst=con.prepareStatement("insert into employeedetails values(?,?,?,?)");
     			pst.setString(1,firstname);
     			pst.setString(2,lastname);
     			pst.setString(3,empid);
     			pst.setString(4,email);
     			int c=pst.executeUpdate();
     			if(c==0)
     			{
     				pr.println("Data not inserted");
     				rd.include(request, response);
     			}
     			else
     			{
     				pr.println("Data inserted successfully");
     				rd.include(request, response);
     			}
     			pr.println("</center></body></html>");
     		} catch (SQLException e) {
     			e.printStackTrace();
     		}
         }
	}

}
