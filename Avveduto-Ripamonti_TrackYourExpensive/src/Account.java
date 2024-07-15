import expense.ExpenseController;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class which represent the account.
 * Implements Serializable interface to save it in a file.
 */
public class Account implements Serializable {
    private final String username;
    private final String password;
    private ArrayList<ExpenseController> expenses;

    /**
     * Constructor
     * @param username - account's username
     * @param password - account's password
     * @param expenses - account's expenses list
     */
    public Account(String username, String password, ArrayList<ExpenseController> expenses) {
        this.username = username;
        this.password = password;
        this.expenses = expenses;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return all the expenses
     */
    public ArrayList<ExpenseController> getExpenses() {
        return new ArrayList<ExpenseController>(expenses);
    }
}
