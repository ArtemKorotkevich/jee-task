package by.gsu.epamlab.beans;

public class User {
	private  String login;
	private String Email;

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

	public User(String login, String email) {
		super();
		this.login = login;
		Email = email;
	}

	@Override
	public String toString() {
		return "User [login=" + login + ", Email=" + Email + "]";
	}
	
	
	
	
	

}
