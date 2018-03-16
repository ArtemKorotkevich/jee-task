package by.gsu.epamlab.DAO;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.exception.DAOException;
import by.gsu.epamlab.exception.PasswordIncorrectException;
import by.gsu.epamlab.exception.UserIncorrectException;

public class RAMUserDAO implements UserDAO {

	private static class InternalUser{
		private User user;
		private String pass;
	
		public User getUser() {
			return user;
		}		
		public void setUser(User user) {
			this.user = user;
		}
		
		public String getPass() {
			return pass;
		}
		
		public void setPass(String pass) {
			this.pass = pass;
		}

		public InternalUser() {
			super();
			
		}

		public InternalUser(User user, String pass) {
			super();
			this.user = user;
			this.pass = pass;
		}
	}
	
	private Map<String,InternalUser> userList = new ConcurrentHashMap<>();
	
	public RAMUserDAO (){
		super();
		userList.put("admin", new InternalUser(new User("admin", "admin@gmail.com"), "admin"));
		userList.put("guest", new InternalUser(new User("guest", "guest@gmail.com"), "guest"));
	}
	
	@Override
	public User getUser(String login, String pass) throws DAOException {
		if(!checkLogin(login)){
			throw new UserIncorrectException(login);
		}
		InternalUser user = userList.get(login);
		
		try{
			if(checkPass(login, pass, user)){
				return user.getUser();
			}else {
				throw new PasswordIncorrectException(pass);
			}
		}catch(NullPointerException e){
			throw new DAOException(e);
		}
	}

	@Override
	public boolean setUser(User user, String pass) throws DAOException {
		String login = user.getLogin();
		if(checkLogin(login)){
			throw new UserIncorrectException(login);
		}
		userList.put(login, new InternalUser(user,pass));
		return true;
	}

	@Override
	public boolean checkLogin(String login) {
		return userList.containsKey(login);			
	}
	
	private boolean checkPass(String login, String pass, InternalUser user){
		if(!checkLogin(login)){
			throw new UserIncorrectException(login);
		}
		try{
			return user.getPass().equals(pass);
		}catch(Exception e){
			throw  new DAOException(e);
		}
	}
}
