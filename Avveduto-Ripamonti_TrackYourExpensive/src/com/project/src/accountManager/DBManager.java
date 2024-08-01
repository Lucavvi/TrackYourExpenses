package com.project.src.accountManager;
import com.google.gson.Gson;
import com.project.src.expense.ExpenseController;
import com.project.src.form.AccessException;
import com.project.src.form.UsernameException;
import java.sql.*;
import java.util.*;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

/**
 * Implements the Actions interface for database operations.
 * This class provides methods to interact with a database for account management,
 * including logging in, registering, and updating user expense lists.
 *
 * @author Angelo Ripamonti & Luca Avveduto
 * @version 1.0
 */
public class DBManager implements Actions{
    private final String URI;

    /**
     * Constructs a DBManager with default database connection parameters.
     */
    public DBManager(){
        URI = "jdbc:mysql://avnadmin:AVNS_FvXsx71KHxD3Fv3tq_k@trackyourexpenses-prova12345675364245123421421.f.aivencloud.com:24168/defaultdb?ssl-mode=REQUIRED";
    }

    /**
     * Logs into an account in the database.
     *
     * @param user the account username. Must not be null or blank
     * @param psw the account password. Must not be null or blank
     * @throws AccessException if the credentials are incorrect or database error
     * @throws NullPointerException if any of the parameters are null.
     * @throws IllegalArgumentException if any of the parameters are blank.
     * @return the logged-in account
     */
    @Override
    public Account login(String user, String psw) throws AccessException, NullPointerException, IllegalArgumentException {
        if(user == null || psw == null) throw new NullPointerException("Almost one parameter passed is null");
        if(user.isBlank() || psw.isBlank()) throw new IllegalArgumentException("Almost one parameter passed is blank");
        final String query = "SELECT * FROM defaultdb.accounts WHERE username=? AND password=?";
        try(
                Connection con = DriverManager.getConnection(URI);
                PreparedStatement st = con.prepareStatement(query);
        ) {
            st.setString(1,user);
            st.setString(2,psw);
            ResultSet rs = st.executeQuery();
            Gson gson = new Gson();
            Type listType = new TypeToken<Account>() {}.getType();
            Account res=null;
            while(rs.next()){
                res=gson.fromJson(rs.getString("accountObj"), listType);
            }
            return res;
        }catch (Exception e) {
            throw new AccessException("Credentials are not correct. Or database error");
        }
    }

    /**
     * Registers a new account in the database.
     *
     * @param acc the account to register. Must not be null.
     * @throws UsernameException if the username already exists or database error or acc is null
     * @return true if the registration is completed successfully
     */
    @Override
    public boolean register(Account acc) throws UsernameException {
        final String query = "INSERT INTO defaultdb.accounts (username, password, accountObj) VALUES (?, ?, ?)";
        try(
                Connection conn = DriverManager.getConnection(URI);
                PreparedStatement stmt = conn.prepareStatement(query);
        ){
            Gson gson = new Gson();
            String json = gson.toJson(acc);

            stmt.setString(1,acc.getUsername());
            stmt.setString(2,acc.getPassword());
            stmt.setString(3, json);
            stmt.executeUpdate();
        }
        catch(Exception e) {
            throw new UsernameException("An account with that username already exists. Or database error");
        }
        return true;
    }

    /**
     * Updates the user's list of expenses in the database.
     *
     * @param acc the account whose expense list is to be updated. Must not be null
     * @throws RuntimeException if there are connection problems or acc is null
     */
    @Override
    public void updateList(Account acc) throws RuntimeException{
        final String command = "UPDATE defaultdb.accounts SET accountObj=? WHERE username=?";
        try(
                Connection conn = DriverManager.getConnection(URI);
                PreparedStatement stmt = conn.prepareStatement(command);
        ){
            Gson gson = new Gson();
            String json = gson.toJson(acc);
            stmt.setString(2,acc.getUsername());
            stmt.setString(1,json);
            stmt.executeUpdate();
        }
        catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Retrieves the account details for a given account from the database.
     *
     * @param acc the account whose expenses are to be retrieved. Must not be null
     * @return the list of expenses associated with the account
     * @throws RuntimeException if there are connection problems or acc is null
     */
    @Override
    public ArrayList<ExpenseController> getExpensesByAccount(Account acc) {
        final String command = "SELECT * FROM defaultdb.accounts WHERE  username=?";
        try(
                Connection conn = DriverManager.getConnection(URI);
                PreparedStatement stmt = conn.prepareStatement(command);
        ){
            Account res = null;
            stmt.setString(1,acc.getUsername());
            ResultSet rs = stmt.executeQuery();

            Gson gson = new Gson();
            Type listType = new TypeToken<Account>() {}.getType();
            while (rs.next()) res = gson.fromJson(rs.getString("accountObj"), listType);
            rs.close();
            return res.getExpenses();
        }
        catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Deletes an account from the database.
     *
     * @param acc the account to be deleted. Must not be null
     * @throws RuntimeException if there are connection problems. Or acc is null
     */
    @Override
    public void deleteAccount(Account acc) {
        final String command = "DELETE FROM defaultdb.accounts WHERE  username=?";
        try(
                Connection conn = DriverManager.getConnection(URI);
                PreparedStatement stmt = conn.prepareStatement(command);
        ){
            stmt.setString(1,acc.getUsername());
            stmt.executeUpdate();
        }
        catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}