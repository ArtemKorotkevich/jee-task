package by.gsu.epamlab.connectionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import by.gsu.epamlab.exception.DAOException;

public class ConnectionSingleton {

  private static boolean isInit = false;
  private static ConnectionSingleton  connectionToDB = null;
  private Connection connection = null;

  public static void setParameterInDB(String driverClass, String dbURL, String dbUser, String dbPass){
    if (!isInit){
      connectionToDB = new ConnectionSingleton(driverClass, dbURL, dbUser, dbPass);
      isInit = true;
    } 
  }

  public static Connection getConnection(){
    return connectionToDB.connection;
  }

  private ConnectionSingleton(String driverClass, String dbURL, String dbUser, String dbPass){
    if (isInit){
      return;
    }
    try {
      Class.forName(driverClass);
      try {
        connection = (Connection) DriverManager.getConnection(dbURL, dbUser, dbPass);
      } catch (SQLException e) {
        throw new DAOException("Error when connect to database!", e);
      }
    } catch (ClassNotFoundException e) {
      throw new DAOException("Connector for JDBC not found!");
    }
  }

  public static void closeConnections(Connection ... connections)
      throws SQLException{
    for (Connection connection : connections) {
      if (connection != null){
        connection.close();
      }
    }
  }

  public static void closeStatements(Statement ... stats)
      throws SQLException{
    for (Statement stat : stats) {
      if (stat != null)
      {
        stat.close();
      }
    }
  }

  public static void closeResultSets(ResultSet ... results) throws SQLException{
    for (ResultSet res : results) {
      if (res != null)
      {
        res.close();
      }
    }
  }

  public void destroyConnectionToDB(){
    if (isInit) {
      try {
        closeConnections(connection);
        connectionToDB = null;
      } catch (SQLException e) {
        System.err.println("Error when destroy connection!");
      }
    }
  }
}