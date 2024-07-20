package expense.comparators;

import expense.ExpenseController;
import java.util.Comparator;

/**
 * Comparator by amount
 * @author luke & Angelo
 * @version 1.0
 */
public class ExpenseAmountComparator implements Comparator<ExpenseController> {
    /**
     * @param expenseController - first obj
     * @param t1 - second obj
     * @return - conventional values
     */
    @Override
    public int compare(ExpenseController expenseController, ExpenseController t1) {
        return Float.compare(expenseController.getModel().getAmount(),t1.getModel().getAmount());
    }

    /**
     * Reversed comparator
     * @return reversed comparator
     */
    @Override
    public Comparator<ExpenseController> reversed() {
        return Comparator.super.reversed();
    }
}
