package taxi.exceptions;

public class ExTaxiFar extends Exception{

    public ExTaxiFar() {
        super("There is no taxi near. \r\n");
    }
    
}
