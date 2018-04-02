package by.gsu.epamlab.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dateCreate"));
    return new Tasks()
        .setUser((User)request.getSession(false).getAttribute(Constant.USER))
        .setDateCreate(new java.sql.Date(date.getTime()))
        .setHeader(request.getParameter("header"))
        .setDescription(request.getParameter("description"));

  }
}