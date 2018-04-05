package by.gsu.epamlab.controller;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.gsu.epamlab.beans.Constant;
import by.gsu.epamlab.beans.Tasks;
import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.connectionDB.ConnectionSingleton;
import by.gsu.epamlab.exception.DAOException;
import by.gsu.epamlab.utilits.TasksDAOFactory;




public class AadTasksServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public void init(ServletConfig config) throws ServletException {
    ConnectionSingleton.setParameterInDB("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/eeproject?autoReconnect=true&useSSL=false", "root", "root");
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try{
      TasksDAOFactory
      .getTasksDAO("db")
      .addTasks(getNewTasks(request));
      response.sendRedirect(Constant.MAIN_PAGE);
    }catch(DAOException e){
      ServletUtilite.jumpError(Constant.ERROR_KEY_WHEN_REGISTERED_USER, Constant.MAIN_PAGE,
          request, response); 
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }
 
  private  static  Tasks getNewTasks(HttpServletRequest request) throws ParseException{ 
         
    return new Tasks()
        .setUser((User)request.getSession(false).getAttribute(Constant.USER))
        .setDateCreate(LocalDate.parse(request.getParameter("dateCreate")))
        .setDateModified(LocalDate.parse(request.getParameter("dateCreate")))
        .setHeader(request.getParameter("header"))
        .setDescription(request.getParameter("description"));

  }
}