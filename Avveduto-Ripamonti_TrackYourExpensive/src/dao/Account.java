package dao;

import expense.ExpenseController;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents an account with associated expenses.
 * Implements the {@link Serializable} interface to enable saving the
 * account data to a file.
 *
 * The {@code Account} class contains the account's username, password,
 * and a list of expenses associated with the account.
 *
 * @author Angelo Ripamonti & Luca Avveduto
 * @version 1.0
 */
public class Account implements Serializable {
    private final String username;
    private final String password;
    private ArrayList<ExpenseController> expenses;

    /**
     * Constructs a new {@code Account} with the specified username, password,
     * and list of expenses.
     *
     * @param username the account's username
     * @param password the account's password
     * @param expenses the list of expenses associated with the account
     */
    public Account(String username, String password, ArrayList<ExpenseController> expenses) {
        this.username = username;
        this.password = password;
        this.expenses = expenses;
    }

    /**
     * Constructs a new {@code Account} with the specified username and password.
     * Initializes the list of expenses as an empty list.
     *
     * @param username the account's username
     * @param password the account's password
     */
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.expenses = new ArrayList<>();
    }

    /**
     * Returns the username of the account.
     *
     * @return the account's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password of the account.
     *
     * @return the account's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns a copy of the list of expenses associated with the account.
     *
     * @return a list containing all the expenses associated with the account
     */
    public ArrayList<ExpenseController> getExpenses() {
        return new ArrayList<ExpenseController>(expenses);
    }
}
