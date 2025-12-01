package by.gsu.epamlab.DAO;

import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.exception.DAOException;

public class UserDAOFactory {
  private UserDAOFactory(){}

  public static UserDAO getUserDAO(String type)throws DAOException{
    switch (type){
      case "ram":
        return new RAMUserDAO();
      case "db":
        return new DBUserDAO();
      default:
        throw new DAOException("type of DAO is not found");
    }	
  }

  public static User getUserFromFactory(String login, String Email){
    User user = new User();
    user.setLogin(login);
    user.setEmail(Email);
    return user;
  }
}
