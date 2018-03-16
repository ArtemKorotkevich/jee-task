package by.gsu.epamlab.utilits;

import java.sql.Date;
import by.gsu.epamlab.DAO.DBTasksDAO;
import by.gsu.epamlab.DAO.IDAOTaskImplementation;
import by.gsu.epamlab.beans.Tasks;
import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.exception.DAOException;

public class TasksDAOFactory {
  public static IDAOTaskImplementation getTasksDAO(String type) throws DAOException{
    switch (type) {
      case "db":
        return new DBTasksDAO();
      default:
        throw new DAOException("Type of IDAOTaskImplementation is not found"); 
    }
  }

  public static Tasks getTasksFromFactory(User user, Date dateCreate,Date dateModified,String header,String description,boolean report){
    return new Tasks()
        .setUser(user)
        .setDateCreate(dateCreate)
        .setDateModified(dateModified)
        .setHeader(header)
        .setDescription(description)
        .setReport(report);
  }

}

