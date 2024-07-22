package form;

import dao.Account;
import dao.DBManager;

/**
 * Model class for managing user authentication and registration.
 * This class handles interactions with the database to log in or register a user.
 *
 * @author Angelo Ripamonti & Luca Avveduto
 * @version 1.0
 */
class FormModel {
    private String username;
    private String psw;
    private DBManager manager;
    protected boolean check;
    protected String failError;

    /**
     * Constructs a FormModel with a specified DBManager for database operations.
     *
     * @param manager the DBManager instance used for database interactions
     */
    FormModel(DBManager manager) {
        this.manager = manager;
        check=false;
    }

    /**
     * Logs in a user with the specified username and password.
     *
     * @param user the username of the account
     * @param password the password of the account
     * @throws AccessException if the username or password is incorrect
     */
    void login(String user, String password) throws AccessException{
        try {
            manager.login(user, password);
        }
        catch(Exception e) {
            throw new AccessException(e.getMessage());
        }
        username = user;
        psw = password;
    }

    /**
     * Registers a new user with the specified username and password.
     *
     * @param user the username to register
     * @param password the password for the new account
     * @throws UsernameException if the username already exists
     */
    void register(String user, String password) throws UsernameException{
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
    String getUsername() {
        return username;
    }

    /**
     * Returns the password used for login or registration.
     *
     * @return the password
     */
    String getPsw() {
        return psw;
    }
}
