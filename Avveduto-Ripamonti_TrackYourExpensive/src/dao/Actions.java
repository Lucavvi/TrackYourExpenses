package dao;
import expense.ExpenseController;
import form.*;

import java.util.ArrayList;

/**
 * Interface to manage account actions.
 * This interface defines the essential methods for handling user account operations such as logging in,
 * registering, and updating user-specific lists.
 *
 * @author Angelo Ripamonti & Luca Avveduto
 * @version 1.0
 */
public interface Actions {

    /**
     * Logs into an account.
     *
     * @param username the account username
     * @param psw the account password
     * @throws AccessException if the credentials are incorrect
     */
    public void login(String username ,String psw) throws AccessException;

    /**
     * Registers a new account.
     *
     * @param username the account username
     * @param psw the account password
     * @throws UsernameException if the username already exists
     */
    public void register(String username , String psw) throws UsernameException;

    /**
     * Updates the user's list of expenses.
     *
     * @param username the account username
     * @param updatedList the updated list of expenses
     */
    public void updateList(String username, ArrayList<ExpenseController> updatedList);

    /**
     * Retrieves the account details for a given username.
     *
     * @param user the account username
     * @return the list of expenses associated with the account
     */
    public ArrayList<ExpenseController> getAccountByUsername(String user);
}
