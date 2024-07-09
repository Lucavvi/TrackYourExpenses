package form;

/**
 * Exception
 * @author Angelo Ripamonti, Luca Avveduto
 * @version 1.0
 */
public class AccessException extends RuntimeException{

    /**
     * Constructor
     * @param msg - message
     */
    public AccessException(String msg) {
        super(msg);
    }
}
