package by.gsu.epamlab.exception;

public class PasswordIncorrectException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public PasswordIncorrectException(String login){
    super("Incorrect passwor for user: " + login);
  }
}
