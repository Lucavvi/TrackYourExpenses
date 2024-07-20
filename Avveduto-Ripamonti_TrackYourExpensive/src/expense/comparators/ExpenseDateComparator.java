package expense.comparators;

import expense.ExpenseController;
import java.util.Comparator;

public class ExpenseDateComparator implements Comparator<ExpenseController> {
    @Override
    public int compare(ExpenseController exp, ExpenseController t1) {
        if(exp.getModel().getDate().isBefore(t1.getModel().getDate())) return -1;
        else if(exp.getModel().getDate().equals(t1.getModel().getDate())) return 0;
        else return 1;
    }

    @Override
    public Comparator<ExpenseController> reversed() {
        return Comparator.super.reversed();
    }
}
