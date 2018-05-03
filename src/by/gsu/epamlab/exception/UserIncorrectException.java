package by.gsu.epamlab.exception;

public class UserIncorrectException extends RuntimeException{
  private static final long serialVersionUID = 1L;

  public UserIncorrectException(String user){
    super("user not found: " + user);
  }
}
