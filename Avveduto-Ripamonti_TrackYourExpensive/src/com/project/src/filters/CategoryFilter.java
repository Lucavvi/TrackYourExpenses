package com.project.src.filters;
import com.project.src.expense.*;
import com.project.src.expense.ExpenseController;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for filtering expenses by category.
 * This class provides a static method to filter a list of expenses based on a specified category.
 *
 * @author Angelo Ripamonti & Luca Avveduto
 * @version 1.0
 */
public class CategoryFilter {

    /**
     * Filters the given list of expenses based on the specified category.
     * If the category is "Categories.ALL", it returns the entire list of expenses.
     * Otherwise, it returns only those expenses that match the specified category.
     *
     * @param expenses the list of expenses to filter. Must not be null.
     * @param cat the category to filter by. Must not be null.
     * @return a list of expenses that match the specified category
     * @throws NullPointerException if any of the parameters are null.
     */
    public static ArrayList<ExpenseController> filter(List<ExpenseController> expenses, Categories cat) throws NullPointerException {
        if(expenses == null || cat == null) throw new NullPointerException("Almost one parameter passed is null");
        ArrayList<ExpenseController> filteredList = new ArrayList<>();
        for(ExpenseController e : expenses) {
            if(e.getModel().getCategory() == cat && cat != Categories.ALL) {
                filteredList.add(e);
            } else if (cat == Categories.ALL) {
                filteredList = new ArrayList<>(expenses);
            }
        }
        return filteredList;
    }
}
