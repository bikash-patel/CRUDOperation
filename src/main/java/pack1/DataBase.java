package pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class DataBase
 *
 */
public class DataBase implements ServletContextListener {
      Connection con;
    /**
     * Default constructor. 
     */
    public DataBase() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         try
         {
        	 Class.forName("oracle.jdbc.driver.OracleDriver");
        	 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","root");
        	 ServletContext sc=sce.getServletContext();
        	 sc.setAttribute("oracle",con);
        	 
         }
         catch(SQLException | ClassNotFoundException e)
         {
        	 e.printStackTrace();
         }
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	try
        {
       	 con.close();
        }
        catch(SQLException e)
        {
       	 e.printStackTrace();
        }
    }
	
}
