package form;

public class FormModel {
    private String username;
    private String psw;


    public void login(String user, String password) throws AccessException{

    }

    public void register(String user, String password) throws UsernameException{

    }

    public String getUsername() {
        return username;
    }

    public String getPsw() {
        return psw;
    }
}
