package dao;
import com.google.gson.Gson;
import expense.ExpenseController;
import form.AccessException;
import form.UsernameException;
import java.sql.*;
import java.util.*;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

/**
 * Class to implement the Actions interface for database operations.
 * This class provides methods to interact with a database for account management,
 * including logging in, registering, and updating user expense lists.
 *
 * @author Angelo Ripamonti & Luca Avveduto
 * @version 1.0
 */

public class DBManager implements Actions{
    private final String USERNAME;
    private final String PASSWORD;
    private final String URL;

    public DBManager(){
        USERNAME = "user";
        PASSWORD = "password";
        URL = "jdbc:mysql://localhost/tracker";
    }

    /**
     * Logs into an account in the database.
     *
     * @param user the account username
     * @param psw the account password
     * @throws AccessException if the credentials are incorrect
     */
    @Override
    public void login(String user, String psw) throws AccessException {
        final String query = "SELECT username,password FROM tracker.accounts WHERE username=? AND password=?";
        try(
                Connection con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
                PreparedStatement st = con.prepareStatement(query);
        ) {
            st.setString(1,user);
            st.setString(2,psw);
            ResultSet rs = st.executeQuery();
        }catch (Exception e) {
            throw new AccessException("Credentials are not correct");
        }
    }

    /**
     * Registers a new account in the database.
     *
     * @param user the account username
     * @param psw the account password
     * @throws UsernameException if the username already exists
     */
    @Override
    public void register(String user, String psw) throws UsernameException {
        final String query = "INSERT INTO tracker.accounts (username, password, expenseList) VALUES (?, ?, ?)";
        try(
                Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(query);
        ){
            Gson gson = new Gson();
            String json = gson.toJson(new ArrayList<ExpenseController>());

            stmt.setString(1,user);
            stmt.setString(2,psw);
            stmt.setString(3, json);
            stmt.executeUpdate();
        }
        catch(Exception e) {
            throw new UsernameException("An account with that username already exists");
        }
    }

    /**
     * Updates the user's list of expenses in the database.
     *
     * @param username the account username
     * @param updatedList the updated list of expenses
     * @throws RuntimeException if there are connection problems
     */
    @Override
    public void updateList(String username, ArrayList<ExpenseController> updatedList) throws RuntimeException{
        final String command = "UPDATE accounts SET expenseList=? WHERE username=?";
        try(
                Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(command);
        ){
            Gson gson = new Gson();
            String json = gson.toJson(updatedList); //CAMBIARE ATTRIBUTI PACKAGE EXPENSE, SE USIAMO CLASSI ESTERNE PROVARE A CREARNE DEI WRAPPER.
            stmt.setString(1,username);
            stmt.setString(2,json);
            stmt.executeUpdate();
        }
        catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Retrieves the account details for a given username from the database.
     *
     * @param user the account username
     * @return the list of expenses associated with the account
     * @throws RuntimeException if there are connection problems
     */
    @Override
    public ArrayList<ExpenseController> getAccountByUsername(String user) {
        final String command = "SELECT * FROM accounts WHERE  username=?";
        try(
                Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(command);
        ){
            stmt.setString(1,user);
            ResultSet rs = stmt.executeQuery();

            Gson gson = new Gson();
            // Definisci il tipo per la conversione
            Type listType = new TypeToken<ArrayList<ExpenseController>>() {}.getType();
            // Converte il JSON in ArrayList
            ArrayList<ExpenseController> res = gson.fromJson(rs.getString("expenseList"), listType);

            for(ExpenseController e : res){
                System.out.println(e);
            }


            rs.close();
            return res;
        }
        catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
