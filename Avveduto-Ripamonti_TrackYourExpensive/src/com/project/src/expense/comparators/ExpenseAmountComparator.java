package com.project.src.expense.comparators;

import com.project.src.expense.ExpenseController;
import java.util.Comparator;

/**
 * Comparator for comparing expenses by amount.
 * This comparator allows sorting of ExpenseController objects based on their amount values.
 *
 * @author Angelo Ripamonti & Luca Avveduto
 * @version 1.0
 */
public class ExpenseAmountComparator implements Comparator<ExpenseController> {

    /**
     * Compares two ExpenseController objects based on their amount.
     *
     * @param expenseController the first ExpenseController object
     * @param t1 the second ExpenseController object
     * @return a negative integer, zero, or a positive integer as the first argument is less than,
     * equal to, or greater than the second
     */
    @Override
    public int compare(ExpenseController expenseController, ExpenseController t1) {
        return Float.compare(expenseController.getModel().getAmount(),t1.getModel().getAmount());
    }

    /**
     * Returns a comparator that imposes the reverse ordering of this comparator.
     *
     * @return a comparator that imposes the reverse ordering of this comparator
     */
    @Override
    public Comparator<ExpenseController> reversed() {
        return Comparator.super.reversed();
    }
}
