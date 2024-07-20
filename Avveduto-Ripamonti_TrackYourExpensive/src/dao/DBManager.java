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
 * Class to implements Actions' interface to database
 * @author Angelo Ripamonti, Luca Avveduto
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
     * Method for logging into an account in the database
     * @param user the account username
     * @param psw the account password
     * @throws AccessException If the cretentials aren't correct
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
     * Method for registering an account within the database
     * @param user the account username
     * @param psw the account password
     * @throws UsernameException If the username already exist
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
     * Method to update the user's expenses list
     * @param username - account's username
     * @param updatedList - updated list
     * @throws RuntimeException - because of connection problems
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
     * Metodo per ottenere tutti gli studenti di anno
     * @param user l'anno di nascita degli studenti desiderati
     * @return la lista con tutti gli studenti nati in anno
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
