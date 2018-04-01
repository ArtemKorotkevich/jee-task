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



public class TodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  final User user = (User) request.getSession().getAttribute(Constant.USER);
	    try{
	      IDAOTaskImplementation tasksSource = TasksDAOFactory.getTasksDAO("db");
	      List<Tasks> tasksList = tasksSource.getTasksByUser(user);
	      request.setAttribute(Constant.TASKS, tasksList);
	      ServletUtilite.jump(Constant.TODAY, request, response);
	    } catch (DAOException e) {
	      ServletUtilite.jumpError(Constant.ERROR_KEY_TASK, Constant.MAIN_PAGE, request, response);
	    }

	  }

	  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    doGet(request, response);
	  }


}
