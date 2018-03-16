package by.gsu.epamlab.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import by.gsu.epamlab.beans.Tasks;
import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.connectionDB.ConnectionSingleton;
import by.gsu.epamlab.controller.ServletUtilite;
import by.gsu.epamlab.exception.DAOException;

public class DBTasksDAO implements IDAOTaskImplementation {  
  private final Connection connection;

  public DBTasksDAO() {
    this.connection = ConnectionSingleton.getConnection();
  }

  private static String getQueryByUser(final User user) {
    if (Objects.isNull(user)) {
      throw new IllegalArgumentException("User is null");
    }
    return  "SELECT * FROM eeproject.tasks "
    + "WHERE UserId = (SELECT UserId FROM eeproject.user "
    + "WHERE login = '" + user.getLogin().trim() + "');";
  }

  @Override
  public List<Tasks>  getTasksByUser(final User user) {
    if(Objects.isNull(user)){
      throw new IllegalArgumentException("User is null");
    }
    ResultSet rs = null;
    List<Tasks> userTasks = new ArrayList<>();
    try{
      rs = connection
          .createStatement()
          .executeQuery(getQueryByUser(user));
      while(rs.next()){
        userTasks.add(ServletUtilite.getTasksByField(user, rs.getDate("dateCreate"), rs.getDate("dateModified"), 
            rs.getString("header"), rs.getString("description"), rs.getBoolean("description"))); 
      }
      return  userTasks;      
    } catch (SQLException e){
      throw new DAOException(e);
    } finally {
      try {
        ConnectionSingleton.closeResultSets(rs);
      } catch (SQLException e) {
        throw new DAOException(e);
      }
    }
  }

}