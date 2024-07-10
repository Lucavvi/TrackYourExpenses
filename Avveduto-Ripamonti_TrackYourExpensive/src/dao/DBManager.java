package dao;

import form.AccessException;
import form.UsernameException;
import java.sql.*;
/**
 * Class to implements Actions' interface to database
 * @author Angelo Ripamonti, Luca Avveduto
 * @version 1.0
 */
public class DBManager implements Actions{
    private final String USERNAME = "user";
    private final String PASSWORD = "password";
    private final String URL = "jdbc:mysql://localhost/tracker";

    /**
     * Method for logging into an account in the database
     * @param username the account username
     * @param psw the account password
     * @throws AccessException If the cretentials aren't correct
     */
    @Override
    public void login(String username, String psw) throws AccessException {
        final String query = "SELECT USERNAME,PASSWORD FROM tracker.accounts WHERE USERNAME=? AND PASSWORD=?";
        try(
                Connection con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
                PreparedStatement st = con.prepareStatement(query);
        ) {
            st.setString(1,username);
            st.setString(2,psw);
            ResultSet rs = st.executeQuery();
        }catch (Exception e) {
            throw new AccessException("Credentials are not correct");
        }
    }

    /**
     * Method for registering an account within the database
     * @param username the account username
     * @param psw the account password
     * @throws UsernameException If the username already exist
     */
    @Override
    public void register(String username, String psw) throws UsernameException {
        final String query = "INSERT INTO tracker.accounts (USERNAME, PASSWORD) VALUES (?, ?)";
        try(
                Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(query);
        ){
            stmt.setString(1,username);
            stmt.setString(2,psw);
            stmt.executeUpdate();
        }
        catch(Exception e) {
            throw new UsernameException("An account with that username already exists");
        }
    }
}
