package taxi.exceptions;

public class ExCustomerFar extends Exception {

    public ExCustomerFar() {
        super("The customer 2 is very far away to share a taxi. Please use another service. \r\n");
    }
    
}
