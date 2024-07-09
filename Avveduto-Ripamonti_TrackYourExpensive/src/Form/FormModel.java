package Form;

import DAO.DBManager;

public class FormModel {
    private String username;
    private String psw;
    private DBManager manager;

    public FormModel() {
        manager = new DBManager();
    }


    public void access(String user, String password) throws AccessException{
        manager.access(user,password);
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
