package by.gsu.epamlab.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.gsu.epamlab.beans.Constant;

public class LogOutServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  public LogOutServlet(){
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getSession().invalidate();
    response.sendRedirect(Constant.LOGIN_PAGE);
  }

}
