package taxi.exceptions;

public class NoCustomerOnPathException extends Exception {
  public NoCustomerOnPathException() {
    super("There is no customers on the path.");
  }
}
