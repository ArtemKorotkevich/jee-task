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

  public Tasks setUser(User user) {
    this.user = user;
    return this;
  }
  
  public Date getDateCreate() {
    return dateCreate;
  }
 
  public Tasks setDateCreate(Date dateCreate) {
    this.dateCreate = dateCreate;
    return this;
  }

  public Date getDateModified() {
    return dateModified;
  }

  public Tasks setDateModified(Date dateModifait) {
    this.dateModified = dateModifait;
    return this;
  }

  public String getHeader() {
    return header;
  }

  public Tasks setHeader(String hader) {
    this.header = hader;
    return this;
  }

  public String getDescription() {
    return description;
  }
  
  public Tasks setDescription(String description) {
    this.description = description;
    return this;
  }

  public boolean isReport() {
    return report;
  }
  
  public Tasks setReport(boolean report) {
    this.report = report;
    return this;
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
