package dao;
import expense.ExpenseController;
import form.*;

import java.util.ArrayList;

/**
 * Interface to manage account actions
 * @author Angelo Ripamonti, Luca Avveduto
 * @version 1.0
 */
public interface Actions {

    /**
     * Method for logging into an account
     * @param username the account username
     * @param psw the account password
     * @throws AccessException If the cretentials aren't correct
     */
    public void login(String username ,String psw) throws AccessException;

    /**
     * Method for registering an account
     * @param username the account username
     * @param psw the account password
     * @throws UsernameException If the username already exist
     */
    public void register(String username , String psw) throws UsernameException;

    /**
     * Method to update the user's list
     * @param username - username
     * @param updatedList - updated list
     */
    public void updateList(String username, ArrayList<ExpenseController> updatedList);

    public ArrayList<ExpenseController> getAccountByUsername(String user);


    }
