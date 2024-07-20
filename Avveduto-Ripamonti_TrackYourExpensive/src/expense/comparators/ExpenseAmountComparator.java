package expense.comparators;

import expense.ExpenseController;
import java.util.Comparator;

public class ExpenseAmountComparator implements Comparator<ExpenseController> {
    @Override
    public int compare(ExpenseController expenseController, ExpenseController t1) {
        return Float.compare(expenseController.getModel().getAmount(),t1.getModel().getAmount());
    }

    @Override
    public Comparator<ExpenseController> reversed() {
        return Comparator.super.reversed();
    }
}
