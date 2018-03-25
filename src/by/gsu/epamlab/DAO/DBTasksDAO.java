package by.gsu.epamlab.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import by.gsu.epamlab.beans.Tasks;
import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.connectionDB.ConnectionSingleton;
import by.gsu.epamlab.exception.DAOException;
import by.gsu.epamlab.utilits.TasksDAOFactory;

public class DBTasksDAO implements IDAOTaskImplementation {  
  private final Connection connection;

  //  public static void main(String [] args) {
  //    ConnectionSingleton.setParameterInDB("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/eeproject", "root", "root");
  //    IDAOTaskImplementation dao = new DBTasksDAO();
  //    System.out.println(dao.getTasksByUser(new User("artem", "lol")));
  //  }

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
        userTasks.add(TasksDAOFactory.getTasksFromFactory(user, rs.getDate("dateCreate"), rs.getDate("dateModified"), 
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

  @Override
  public boolean addTasks(Tasks tasks) throws DAOException {
    String InsertQeryForTask = "insert into eeproject.tasks (UserId, dateCreate ,header,description)values(?,?,?,?);";
    PreparedStatement ps = null;
    try{
      ps = ConnectionSingleton.getConnection().prepareStatement(InsertQeryForTask);
      synchronized (ps) {
        ps.setInt(1, tasks.getUser().getUserId());
        System.out.println(tasks.getDateCreate());
        ps.setDate(2, tasks.getDateCreate());
        System.out.println(tasks.getHeader());
        ps.setString(3, tasks.getHeader());
        System.out.println(tasks.getDescription());
        ps.setString(4, tasks.getDescription());
        return ps.execute();
      }
    }catch(SQLException e){
      throw new DAOException(e);

    }
  }
}
