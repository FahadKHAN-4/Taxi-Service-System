package taxi.exceptions;

public class ExInFeasibleOrder extends Exception {

    public ExInFeasibleOrder() {
        super("Taxi cannot be ordered. You are already at destination. \r\n");
    }
    
}
