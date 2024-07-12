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
    private boolean check;
    private String failError;

    /**
     * Constructor that initialize the connector to the DB
     */
    public FormModel() {
        manager = new DBManager();
        check=false;
    }

    /**
     * Method to log in own account
     * @param user - username
     * @param password - password
     * @throws AccessException - if one or both the inputs are wrong
     */
    public void login(String user, String password) throws AccessException{
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
     * Method to register a new account
     * @param user - username
     * @param password - password
     * @throws UsernameException - if the username already exists
     */
    public void register(String user, String password) throws UsernameException{
        try {
            manager.register(user, password);
        }
        catch(Exception e) {
            throw new UsernameException(e.getMessage());
        }
        username = user;
        psw = password;
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

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getFailError() {
        return failError;
    }

    public void setFailError(String failError) {
        this.failError = failError;
    }
}
