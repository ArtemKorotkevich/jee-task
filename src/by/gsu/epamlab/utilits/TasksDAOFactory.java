package by.gsu.epamlab.utilits;

import java.sql.Date;
import by.gsu.epamlab.DAO.DBTasksDAO;
import by.gsu.epamlab.DAO.IDAOTaskImplementation;
import by.gsu.epamlab.beans.Tasks;
import by.gsu.epamlab.exception.DAOException;

public class TasksDAOFactory {
  public static IDAOTaskImplementation getTasksDAO(String type) throws DAOException{
    switch (type) {
      case "db":
        return new DBTasksDAO();
      default:
        throw new DAOException("type of DAO is not found"); 
    }
  }


  public static Tasks getTasksFromFactory(Date dateCreate,Date dateModified,String header,String description,boolean report){
    Tasks tasks = new Tasks();
    tasks.setDateCreate(dateCreate);
    tasks.setDateModified(dateModified);
    tasks.setHeader(header);
    tasks.setDescription(description);
    tasks.setReport(report);
    return tasks;

  }

}

