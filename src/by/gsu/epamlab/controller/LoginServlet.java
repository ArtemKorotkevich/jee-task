package by.gsu.epamlab.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.gsu.epamlab.DAO.UserDAO;
import by.gsu.epamlab.DAO.UserDAOFactory;
import by.gsu.epamlab.beans.Constant;
import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.connectionDB.ConnectionSingleton;
import by.gsu.epamlab.exception.PasswordIncorrectException;
import by.gsu.epamlab.exception.UserIncorrectException;



public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void init(ServletConfig config) throws ServletException {
		ConnectionSingleton.setParameterInDB("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/eeproject", "root", "root");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletUtilite.jump(Constant.INDEX_PAGE, request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String pass = request.getParameter("pass");
		
		UserDAO userDAO = UserDAOFactory.getUserDAO("db");
		
		try{
			System.out.println(login + pass);
			User user = userDAO.getUser(login, pass);
			
			HttpSession session = request.getSession();
			System.out.println(user.getLogin());
			session.setAttribute(Constant.USER, user);
			ServletUtilite.jump(Constant.MAIN_PAGE, request, response);
		}catch(UserIncorrectException e){
			ServletUtilite.jumpError(Constant.ERROR_KEY_WHEN_REGISTERED_USER,Constant.LOGIN_PAGE,request, response);
			
		}catch (PasswordIncorrectException e) {
			ServletUtilite.jumpError(Constant.ERROR_KEY_WHEN_REGISTERED_USER, Constant.LOGIN_PAGE, request, response);
		}
		
	}

}
