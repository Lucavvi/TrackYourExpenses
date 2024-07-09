package dao;
import form.*;

/**
 * Interfaccia
 * @author Angelo Ripamonti, Luca Avveduto
 * @version 1.0
 */
public interface Actions {
    public void login(String username ,String psw) throws AccessException;
    public void register(String username ,String psw) throws UsernameException;

}
