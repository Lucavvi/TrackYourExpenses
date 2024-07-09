package form;

import dao.DBManager;

/**
 * Classe MVC
 * @author Angelo Ripamonti, Luca Avveduto
 * @version 1.0
 */
public class FormModel {
    private String username;
    private String psw;
    private DBManager manager;

    public FormModel() {
        manager = new DBManager();
    }

    public void login(String user, String password) throws AccessException{
        manager.login(user,password);
    }

    public void register(String user, String password) throws UsernameException{
        manager.register(user,password);
    }

    public String getUsername() {
        return username;
    }

    public String getPsw() {
        return psw;
    }
}
