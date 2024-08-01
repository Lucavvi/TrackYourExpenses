package com.project.src.form;

import com.project.src.accountManager.*;

/**
 * Model class for managing user authentication and registration.
 * This class handles interactions with the database to log in or register a user.
 *
 * @author Angelo Ripamonti & Luca Avveduto
 * @version 1.0
 */
public class FormModel {
    private String username;
    private String psw;
    private DBManager manager;
    protected boolean check;
    protected String failError;

    /**
     * Constructs a FormModel with a specified DBManager for database operations.
     *
     * @param manager the DBManager instance used for database interactions. Must not be null.
     * @throws NullPointerException if any of the parameters are null
     */
    FormModel(DBManager manager)throws NullPointerException {
        if(manager == null) throw new NullPointerException("Almost one parameter passed is null");
        this.manager = manager;
        check=false;
    }

    /**
     * Logs in a user with the specified username and password.
     *
     * @param user the username of the account. Must not be null or blank.
     * @param password the password of the account. Must not be null or blank.
     * @return the Account associated with the provided username and password if the credentials are valid.
     * @throws AccessException if the username or password is incorrect
     * @throws NullPointerException if any of the parameters are null
     * @throws IllegalArgumentException if any of the parameters are blank
     */
    Account login(String user, String password) throws AccessException, NullPointerException, IllegalArgumentException{
        if(user == null || password == null) throw new NullPointerException("Wrong Credentials!");
        if(user.isBlank() || password.isBlank()) throw new IllegalArgumentException("Wrong Credentials!");
        Account acc = manager.login(user, password);
        if (acc == null) throw new AccessException("Wrong Credentials!");
        username = user;
        psw = password;
        return acc;
    }

    /**
     * Registers a new user with the specified username and password.
     *
     * @param user the username to register. Must not be null or blank.
     * @param password the password for the new account. Must not be null or blank.
     * @throws UsernameException if the username already exists
     * @throws NullPointerException if any of the parameters are null
     * @throws IllegalArgumentException if any of the parameters are blank
     */
    void register(String user, String password) throws UsernameException, NullPointerException, IllegalArgumentException{
        if(user == null || password == null) throw new NullPointerException("Not valid Credentials!");
        if(user.isBlank() || password.isBlank()) throw new IllegalArgumentException("Not valid Credentials!");
        try {
            manager.register(new Account(user, password));
        }
        catch(Exception e) {
            throw new UsernameException(e.getMessage());
        }
        username = user;
        psw = password;
    }

    /**
     * Returns the username used for login or registration.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password used for login or registration.
     *
     * @return the password
     */
    public String getPsw() {
        return psw;
    }
}
