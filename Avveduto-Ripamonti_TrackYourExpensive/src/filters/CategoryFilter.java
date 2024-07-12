package filters;
import expense.Categories;
import expense.ExpenseController;
import java.util.ArrayList;
import java.util.List;

public class CategoryFilter implements Filter{
    @Override
    public List<ExpenseController> filter(List<ExpenseController> expenses, Categories cat) {
        ArrayList<ExpenseController> filteredList = new ArrayList<>();
        for(ExpenseController e : expenses) {
            if(e.getModel().getCategory() == cat) {
                filteredList.add(e);
            }
        }
        return filteredList;
    }
}
