package by.gsu.epamlab.beans;


import java.time.LocalDate;

public class Tasks {
  private User user;
  private int idtasks;
  private LocalDate dateCreate;
  private LocalDate dateModified;
  private String header;
  private String description;
  private boolean report;
  private boolean recycleBin;
  private String URL;


  public User getUser() {
    return user;
  }

  public Tasks setUser(User user) {
    this.user = user;
    return this;
  }

  public int getIdtasks() {
    return idtasks;
  }


  public Tasks setIdtasks(int idtasks) {
    this.idtasks = idtasks;
    return this;
  }
  
  public LocalDate getDateCreate() {
    return dateCreate;
  }

  public Tasks setDateCreate(LocalDate dateCreate) {
    this.dateCreate = dateCreate;
    return this;
  }

  public LocalDate getDateModified() {
    return dateModified;
  }

  public Tasks setDateModified(LocalDate dateModifait) {
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

  public boolean isRecycleBin() {
    return recycleBin;
  }


  public Tasks setRecycleBin(boolean recycle_Bin) {
    this.recycleBin = recycle_Bin;
    return this;
  }
  
  public String getURL() {
    return URL;
  }

  public Tasks setURL(String uRL) {
    URL = uRL;
    return this;
  }


  public Tasks() {
    super();

  }

  public Tasks(int idtasks, LocalDate dateCreate, LocalDate dateModified, String header, String description,
      boolean report, boolean recycle_Bin, String URL) {
    super();
    this.idtasks = idtasks;
    this.dateCreate = dateCreate;
    this.dateModified = dateModified;
    this.header = header;
    this.description = description;
    this.report = report;
    this.recycleBin = recycle_Bin;
    this.URL = URL;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Tasks [user=" + user + ", idtasks=" + idtasks + ", dateCreate=" + dateCreate
        + ", dateModified=" + dateModified + ", header=" + header + ", description=" + description
        + ", report=" + report + ", recycleBin=" + recycleBin + ", URL=" + URL + "]";
  }
}
