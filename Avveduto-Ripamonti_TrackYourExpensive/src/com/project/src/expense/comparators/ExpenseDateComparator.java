package com.project.src.expense.comparators;

import com.project.src.expense.ExpenseController;
import java.util.Comparator;

/**
 * Comparator for comparing expenses by date.
 * This comparator allows sorting of ExpenseController objects based on their date values.
 *
 * @author Angelo Ripamonti & Luca Avveduto
 * @version 1.0
 */
public class ExpenseDateComparator implements Comparator<ExpenseController> {

    /**
     * Compares two ExpenseController objects based on their date.
     *
     * @param exp the first ExpenseController object
     * @param t1 the second ExpenseController object
     * @return a negative integer, zero, or a positive integer as the first argument is earlier than,
     * equal to, or later than the second
     */
    @Override
    public int compare(ExpenseController exp, ExpenseController t1) {
        if(exp.getModel().getDate().isBefore(t1.getModel().getDate())) return -1;
        else if(exp.getModel().getDate().equals(t1.getModel().getDate())) return 0;
        else return 1;
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
