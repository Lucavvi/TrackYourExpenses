package DAO;
import Form.*;
public interface Actions {
    public void access(String username ,String psw) throws AccessException;
    public void register(String username ,String psw) throws UsernameException;

}
