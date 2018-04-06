package by.gsu.epamlab.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import by.gsu.epamlab.beans.Constant;
import by.gsu.epamlab.utilits.TasksDAOFactory;


public class DeleteTasks extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  try{
	    TasksDAOFactory
	    .getTasksDAO("db")
	    .deleteTaks(getTasksIds(request));
	    response.sendRedirect(Constant.MAIN_PAGE);
	  }catch(ParseException e){
	    e.printStackTrace();
	  }
	}
	
	private static List<Integer> getTasksIds(HttpServletRequest request) throws ParseException{
	    List<Integer> ids = new ArrayList<>();
	    Enumeration<String> params = request.getParameterNames();
	    while (params.hasMoreElements()) {
	      String param = params.nextElement();
	      if (param.startsWith("task-")) {
	        ids.add(Integer.parseInt(param.split("-")[1]));	        
	      }
	    }
	    return ids;
	}

}
