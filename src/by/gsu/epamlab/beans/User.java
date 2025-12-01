package by.gsu.epamlab.beans;

public class User {
    private int UserId;
	private  String login;
	private String Email;

	  public int getUserId() {
	    return UserId;
	  }

	  public void setUserId(int userId) {
	    UserId = userId;
	  }
	    	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return Email;
	}
	
	public void setEmail(String email) {
		Email = email;
	}

	public User() {
		super();
		
	}

	public User(int UserId, String login, String email) {
		super();
		this.UserId = UserId;
		this.login = login;
		Email = email;
	}


  @Override
  public String toString() {
    return "User [UserId=" + UserId + ", login=" + login + ", Email=" + Email + "]";
  }

	


	
	
	
	

}
