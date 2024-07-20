package expense.comparators;

import expense.ExpenseController;
import java.util.Comparator;

/**
 * Comparator by date
 * @author luke
 * @version 1.0
 */
public class ExpenseDateComparator implements Comparator<ExpenseController> {
    /**
     * @param exp - first obj
     * @param t1 - second obj
     * @return - conventional values
     */
    @Override
    public int compare(ExpenseController exp, ExpenseController t1) {
        if(exp.getModel().getDate().isBefore(t1.getModel().getDate())) return -1;
        else if(exp.getModel().getDate().equals(t1.getModel().getDate())) return 0;
        else return 1;
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
