package form;

import dao.DBManager;

/**
 * MVC Class
 * @author Angelo Ripamonti, Luca Avveduto
 * @version 1.0
 */
public class FormModel {
    private String username;
    private String psw;
    private DBManager manager;

    /**
     * Constructor that initialize the connector to the DB
     */
    public FormModel() {
        manager = new DBManager();
    }

    /**
     * Method to log in own account
     * @param user - username
     * @param password - password
     * @throws AccessException - if one or both the inputs are wrong
     */
    public void login(String user, String password) throws AccessException{
        manager.login(user,password);
    }

    /**
     * Method to register a new account
     * @param user - username
     * @param password - password
     * @throws UsernameException - if the username already exists
     */
    public void register(String user, String password) throws UsernameException{
        manager.register(user,password);
    }

    /**
     * @return the username written in the text field
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the password written in the text field
     */
    public String getPsw() {
        return psw;
    }
}
