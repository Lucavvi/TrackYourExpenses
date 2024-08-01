package com.project.src.accountManager;

import com.project.src.expense.ExpenseController;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents an account with associated expenses.
 * Implements the Serializable interface to enable saving the
 * account data to a file.
 *
 * The Account class contains the account's username, password,
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
     * Constructs a new Account with the specified username, password,
     * and list of expenses.
     *
     * @param username the account's username. Must not be null or blank.
     * @param password the account's password. Must not be null or blank.
     * @param expenses the list of expenses associated with the account. Must not be null.
     * @throws NullPointerException if any of the parameters are null.
     * @throws IllegalArgumentException if the username or password is blank.
     */
    public Account(String username, String password, ArrayList<ExpenseController> expenses) throws NullPointerException, IllegalArgumentException {
        if(username == null || password == null || expenses == null) throw new NullPointerException("Almost one parameter passed is null");
        if(username.isBlank() || password.isBlank()) throw new IllegalArgumentException("Almost one parameter passed is blank");
        this.username = username;
        this.password = password;
        this.expenses = expenses;
    }

    /**
     * Constructs a new Account with the specified username and password.
     * Initializes the list of expenses as an empty list.
     *
     * @param username the account's username. Must not be null or blank.
     * @param password the account's password. Must not be null or blank.
     * @throws NullPointerException if any of the parameters are null.
     * @throws IllegalArgumentException if the username or password is blank.
     */
    public Account(String username, String password) throws NullPointerException, IllegalArgumentException{
        if(username == null || password == null) throw new NullPointerException("Almost one parameter passed is null");
        if(username.isBlank() || password.isBlank()) throw new IllegalArgumentException("Almost one parameter passed is blank");
        this.username = username;
        this.password = password;
        this.expenses = new ArrayList<ExpenseController>();
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
    String getPassword() {
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

    /**
     * Adds a new expense to the account.
     *
     * @param exp the expense to be added. Must not be null.
     * @throws NullPointerException if the provided expense is null.
     */
    public void addExpense(ExpenseController exp) throws NullPointerException{
        if(exp == null) throw new NullPointerException("The parameter passed is null");
        expenses.add(exp);
    }

    /**
     * Resets the list of expenses associated with the account.
     *
     * @return true if the list of expenses was cleared successfully
     */
    boolean resetExpenses() {
        this.expenses.clear();
        return true;
    }
}
