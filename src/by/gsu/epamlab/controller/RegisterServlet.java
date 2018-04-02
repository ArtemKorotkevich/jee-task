package by.gsu.epamlab.controller;

import java.io.IOException;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.gsu.epamlab.connectionDB.ConnectionSingleton;
import by.gsu.epamlab.DAO.UserDAO;
import by.gsu.epamlab.DAO.UserDAOFactory;
import by.gsu.epamlab.beans.Constant;
import by.gsu.epamlab.beans.User;



public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init(ServletConfig config) throws ServletException {
		ConnectionSingleton.setParameterInDB("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/eeproject?autoReconnect=true&useSSL=false", "root", "root");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
request.setCharacterEncoding(Constant.CHARACTER_ENCODING);
		
		UserDAO userDAO = UserDAOFactory.getUserDAO("db");
		
		//Get new user from factory (User privilege is default)
		User newUser = UserDAOFactory.getUserFromFactory(request.getParameter(Constant.LOGIN),
				request.getParameter(Constant.EMAIL));
				
		
		if (userDAO.checkLogin(newUser.getLogin())){			
	      
		  ServletUtilite.jumpError(Constant.ERROR_KEY_WHEN_REGISTERED_USER,
				  Constant.REGISTER_PAGE, request, response);	
		  return;
		}		
		//Set new user 
		userDAO.setUser(newUser, request.getParameter(Constant.PASSWORD));		
		response.sendRedirect(Constant.LOGIN_PAGE);
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.sendRedirect(Constants.REGISTER_PAGE);
		ServletUtilite.jump(Constant.INDEX_PAGE, request, response);
	}

}