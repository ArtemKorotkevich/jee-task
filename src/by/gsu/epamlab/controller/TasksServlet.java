package by.gsu.epamlab.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.gsu.epamlab.DAO.IDAOTaskImplementation;
import by.gsu.epamlab.beans.Constant;
import by.gsu.epamlab.beans.Tasks;
import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.exception.DAOException;
import by.gsu.epamlab.utilits.TasksDAOFactory;


public class TasksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    final User user = (User) request.getSession().getAttribute(Constant.USER);
	    if(user == null){
	      ServletUtilite.jump(Constant.LOGIN_PAGE, request, response);
	    }  
	    try {
          IDAOTaskImplementation tasksSource = TasksDAOFactory.getTasksDAO("db");
          List<Tasks> tasksList = tasksSource.getTasksByUser(user);
          request.setAttribute(Constant.TASKS, tasksList);
          ServletUtilite.jump(Constant.MAIN_PAGE, request, response);
        } catch (DAOException e) {
          // Need to make error page
          ServletUtilite.jumpError(Constant.ERROR_KEY_WHEN_REGISTERED_USER, Constant.INDEX_PAGE, request, response);
        }
	}



}
