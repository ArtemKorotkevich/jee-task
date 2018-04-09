package by.gsu.epamlab.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import by.gsu.epamlab.beans.Tasks;
import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.connectionDB.ConnectionSingleton;
import by.gsu.epamlab.enums.SectionDayEnum;
import by.gsu.epamlab.exception.DAOException;
import by.gsu.epamlab.utilits.TasksDAOFactory;

public class DBTasksDAO implements IDAOTaskImplementation {  
  private final Connection connection;
  private static final Object LOCK = new Object();

  public DBTasksDAO() {
    this.connection = ConnectionSingleton.getConnection();
  }

  @Override
  public List<Tasks>  getTasksByUser(final User user, SectionDayEnum sectionDayEnum) {
    if(Objects.isNull(user)){
      throw new IllegalArgumentException("User is null");
    }
    ResultSet rs = null;
    List<Tasks> userTasks = new ArrayList<>();
    try{
      rs = connection
          .createStatement()
          .executeQuery(sectionDayEnum.getQuerery(user));
      while(rs.next()){
        userTasks.add(TasksDAOFactory.getTasksFromFactory(user, rs.getInt("idtasks"), rs.getDate("dateCreate").toLocalDate(), rs.getDate("dateModified").toLocalDate(), 
            rs.getString("header"), rs.getString("description"), rs.getBoolean("report"), rs.getBoolean("recycle_Bin"))); 
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
    String InsertQeryForTask = "insert into eeproject.tasks (UserId, dateCreate, dateModified,"
        + " header, description)values(?,?,?,?,?);";
    PreparedStatement ps = null;
    try{
      ps = ConnectionSingleton.getConnection().prepareStatement(InsertQeryForTask);
      synchronized (LOCK) {
        System.out.println(tasks.getUser().getUserId());
        ps.setInt(1, tasks.getUser().getUserId());
        System.out.println(tasks.getDateCreate());
        ps.setDate(2, Date.valueOf(tasks.getDateCreate()));
        System.out.println(tasks.getDateModified());
        ps.setDate(3, Date.valueOf(tasks.getDateModified()));
        System.out.println(tasks.getHeader());
        ps.setString(4, tasks.getHeader());
        System.out.println(tasks.getDescription());
        ps.setString(5, tasks.getDescription());
        return ps.execute();
      }
    }catch(SQLException e){
      throw new DAOException(e);

    }
  }

  @Override
  public void deleteTaks(List<Integer> list) throws DAOException {
    String  deleteQeryForTasks = "UPDATE eeproject.tasks SET recycle_Bin = 1 WHERE idtasks = ?;";
    PreparedStatement ps = null;
    try{
      ps = ConnectionSingleton.getConnection().prepareStatement(deleteQeryForTasks);
      for(int ids : list){
        synchronized (LOCK) {
          ps.setInt(1, ids);
          ps.execute();
        }
      }
    }catch(SQLException e){
      throw new DAOException(e);
    }
  }

  @Override
  public void executedTasks(List<Integer> list) throws DAOException {
    String  deleteQeryForTasks = "UPDATE eeproject.tasks SET report = 1 WHERE idtasks = ?;";
    PreparedStatement ps = null;
    try{
      ps = ConnectionSingleton.getConnection().prepareStatement(deleteQeryForTasks);
      for(int ids : list){
        synchronized (LOCK) {
          ps.setInt(1, ids);
          ps.execute();
        }
      }
    }catch(SQLException e){
      throw new DAOException(e);

    }
  }

  @Override
  public void deleteTaskInDB(List<Integer> list) throws DAOException {
    String  deleteQeryForTasks = "DELETE FROM `eeproject`.`tasks` WHERE `idtasks`=?;";
    PreparedStatement ps = null;
    try{
      ps = ConnectionSingleton.getConnection().prepareStatement(deleteQeryForTasks);
      for(int ids : list){
        synchronized (LOCK) {
          ps.setInt(1, ids);
          ps.execute();
        }
      }
    }catch(SQLException e){
      throw new DAOException(e);

    }
  }
}
