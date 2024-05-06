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

/**
 * Servlet implementation class verify
 */
public class verify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public verify() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String add=request.getParameter("add");
		String remove=request.getParameter("remove");
		String update=request.getParameter("update");
		ServletContext sc=request.getServletContext();
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		String empid=request.getParameter("empid");
		String email=request.getParameter("email");
		sc.setAttribute("firstname",firstname);
		sc.setAttribute("lastname",lastname);
		sc.setAttribute("empid",empid);
		sc.setAttribute("email",email);
		if(add!=null)
		{
			RequestDispatcher rd=request.getRequestDispatcher("/add");
		    rd.include(request, response);
		}
		else if(remove!=null)
		{
			RequestDispatcher rd=request.getRequestDispatcher("/remove");
		    rd.include(request, response);
		}
		else if(update!=null)
		{
			RequestDispatcher rd=request.getRequestDispatcher("/update");
		    rd.include(request, response);
		}
	}

}
