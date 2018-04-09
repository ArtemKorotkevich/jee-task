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
import by.gsu.epamlab.enums.SectionDayEnum;
import by.gsu.epamlab.exception.DAOException;
import by.gsu.epamlab.utilits.TasksDAOFactory;

public class TasksServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    final User user = (User) request.getSession().getAttribute(Constant.USER);
    if(user == null){
      System.out.println(user);
      ServletUtilite.jump(Constant.LOGIN_PAGE, request, response);
    } 
    System.out.println(user);
    try {
      IDAOTaskImplementation tasksSource = TasksDAOFactory.getTasksDAO("db");
      List<Tasks> tasksList = tasksSource.getTasksByUser(user, SectionDayEnum.getValueByParam(request));
      System.out.println(tasksList);
      request.setAttribute("section",SectionDayEnum.getValueByParam(request).name());
      request.setAttribute(Constant.TASKS, tasksList);
      ServletUtilite.jump(Constant.MAIN_PAGE, request, response);
    } catch (DAOException e) {
      ServletUtilite.jumpError(Constant.ERROR_KEY_TASK, Constant.MAIN_PAGE, request, response);
    }
  }
}
