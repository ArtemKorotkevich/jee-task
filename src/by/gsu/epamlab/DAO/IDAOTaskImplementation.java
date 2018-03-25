package by.gsu.epamlab.DAO;

import java.util.List;
import by.gsu.epamlab.beans.Tasks;
import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.exception.DAOException;

public interface IDAOTaskImplementation {
  public List<Tasks>  getTasksByUser(User user)throws DAOException;
  public boolean addTasks(Tasks tasks) throws DAOException;
  
}
