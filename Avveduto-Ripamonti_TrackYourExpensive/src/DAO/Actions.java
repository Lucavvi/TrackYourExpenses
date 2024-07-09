package DAO;
import form.*;
public interface Actions {
    public void login(String username ,String psw) throws AccessException;
    public void register(String username ,String psw) throws UsernameException;

}
