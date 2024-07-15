import expense.ExpenseController;
import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Serializable {
    private final String username;
    private final String password;
    private ArrayList<ExpenseController> expenses;

    public Account(String username, String password, ArrayList<ExpenseController> expenses) {
        this.username = username;
        this.password = password;
        this.expenses = expenses;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<ExpenseController> getExpenses() {
        return new ArrayList<ExpenseController>(expenses);
    }
}
