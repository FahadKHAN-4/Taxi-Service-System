package taxi.exceptions;

public class NotEnoughBalanceException extends Exception{
    public NotEnoughBalanceException (String str){
        super(str);
    }
}
