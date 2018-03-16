package by.gsu.epamlab.beans;

import java.sql.Date;

public class Tasks {
  private User user;
  private Date dateCreate;
  private Date dateModified;
  private String header;
  private String description;
  private boolean report;
 

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
  
  public Date getDateCreate() {
    return dateCreate;
  }
 
  public void setDateCreate(Date dateCreate) {
    this.dateCreate = dateCreate;
  }

  public Date getDateModified() {
    return dateModified;
  }

  public void setDateModified(Date dateModifait) {
    this.dateModified = dateModifait;
  }

  public String getHeader() {
    return header;
  }

  public void setHeader(String hader) {
    this.header = hader;
  }

  public String getDescription() {
    return description;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isReport() {
    return report;
  }
  
  public void setReport(boolean report) {
    this.report = report;
  }

  public Tasks() {
    super();
  
  }

  public Tasks(Date dateCreate, Date dateModifait, String hader, String description,
      boolean report) {
    super();
    this.dateCreate = dateCreate;
    this.dateModified = dateModifait;
    this.header = hader;
    this.description = description;
    this.report = report;
  }

  
  @Override
  public String toString() {
    return "Tasks [dateCreate=" + dateCreate + ", dateModifait=" + dateModified + ", hader=" + header
        + ", description=" + description + ", report=" + report + "]";
  }


  
  
  
  
  
  

}
