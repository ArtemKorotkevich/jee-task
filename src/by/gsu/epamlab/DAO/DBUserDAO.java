package by.gsu.epamlab.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.connectionDB.ConnectionSingleton;
import by.gsu.epamlab.controller.ServletUtilite;
import by.gsu.epamlab.exception.DAOException;
import by.gsu.epamlab.exception.PasswordIncorrectException;
import by.gsu.epamlab.exception.UserIncorrectException;

public class DBUserDAO implements UserDAO {

  @Override
  public User getUser(String login, String pass) throws DAOException {
    Connection connection = ConnectionSingleton.getConnection();
    String query = "select * from eeproject.user where login=? ;";
    if(checkPass(login, pass)){
      try{
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, login);
        ResultSet rs = statement.executeQuery();
        if(rs.next()){
          User user = new User();
          user.setUserId(rs.getInt(1));
          user.setLogin(rs.getString(2));
          user.setEmail(rs.getString(3));
          return user;
        }else{
          throw new NullPointerException("User not faund! ");
        }
      }catch(SQLException e){
        throw new DAOException(e);
      }
    }else{
      throw new PasswordIncorrectException(login);
    }
  }

  @Override
  public boolean setUser(User user, String pass){
    String InsertQeryForUser = "insert into eeproject.user"
        +"(`login`, `Email`, `pass`) "
        +"values(?,?,?);";
    PreparedStatement ps = null;
    try{
      ps = ConnectionSingleton.getConnection().prepareStatement(InsertQeryForUser);
      synchronized (ps) {
        System.out.println(user.getLogin());
        ps.setString(1,user.getLogin() );
        System.out.println(user.getEmail());
        ps.setString(2, user.getEmail());
        System.out.println(ServletUtilite.getHashMD5(pass));
        ps.setString(3, ServletUtilite.getHashMD5(pass));
        return ps.execute();
      }
    }catch(SQLException e){
      throw new DAOException(e);
    }
  }

  @Override
  public boolean checkLogin(String login) {
    String checkQuery = "SELECT 'true' FROM eeproject.user WHERE login = \"" + login.trim() + "\";";
    Statement stat = null;
    ResultSet res = null;	
    try {
      boolean result;
      stat = (Statement) ConnectionSingleton.getConnection().createStatement();
      synchronized (this) {
        res = stat.executeQuery(checkQuery);
        result = res.next() ? true : false;
      }
      ConnectionSingleton.closeResultSets(res);
      ConnectionSingleton.closeStatements(stat);
      return result;
    } catch (SQLException e) {
      throw new DAOException(e);
    } 
  }

  private boolean checkPass(String login, String pass){
    if (checkLogin(login)){
      try {
        boolean result;
        Statement stat = null;
        ResultSet res = null;

        stat = (Statement) ConnectionSingleton.getConnection().createStatement();
        res = stat.executeQuery("SELECT pass FROM eeproject.user WHERE login = \"" + login.trim() + "\";");

        String passFromDB;
        if (res.next())
        {
          passFromDB = res.getString(1);

        } else 
        {
          return false;
        }

        ConnectionSingleton.closeResultSets(res);			
        String hashPass = ServletUtilite.getHashMD5(pass);
        System.out.println(hashPass + " " + passFromDB);
        //Check password
        result = (hashPass.equals(passFromDB.trim())) ? true : false;
        ConnectionSingleton.closeResultSets(res);
        ConnectionSingleton.closeStatements(stat);
        return result;
      } catch (SQLException e) {
        throw new DAOException(e);
      }

    } else {
      throw new UserIncorrectException(login);
    }
  }
}
