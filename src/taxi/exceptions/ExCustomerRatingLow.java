package taxi.exceptions;

public class ExCustomerRatingLow extends Exception{
    public ExCustomerRatingLow(){
        super("Your rating is very low, you cannot use the service now. \r\n");
    }
}