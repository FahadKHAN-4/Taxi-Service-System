package taxi.exceptions;

public class ExDestinationFar extends Exception {

    public ExDestinationFar() {
        super("The destination is too far away. Please select a closer destination. \r\n");
    }
    
}
