package com.project.src.accountManager;
import com.project.src.expense.ExpenseController;
import com.project.src.form.*;

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
     * @return the logged-in account
     */
    public Account login(String username ,String psw) throws AccessException;

    /**
     * Registers a new account.
     *
     * @param acc the account to register
     * @throws UsernameException if the username already exists
     * @return true if the registration is completed successfully
     */
    public boolean register(Account acc) throws UsernameException;

    /**
     * Updates the user's list of expenses.
     *
     * @param acc the account whose expense list is to be updated
     */
    public void updateList(Account acc);

    /**
     * Retrieves the account details for a given account.
     *
     * @param acc the account whose expenses are to be retrieved
     * @return the list of expenses associated with the account
     */
    public ArrayList<ExpenseController> getExpensesByAccount(Account acc);

    /**
     * Deletes an account.
     *
     * @param acc the account to be deleted
     */
    public void deleteAccount(Account acc);
}
