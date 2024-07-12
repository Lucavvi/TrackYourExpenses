package filters;
import expense.Categories;
import expense.ExpenseController;
import java.util.List;

public interface Filter {
    public List<ExpenseController> filter(List<ExpenseController> expenses, Categories filter);
}
