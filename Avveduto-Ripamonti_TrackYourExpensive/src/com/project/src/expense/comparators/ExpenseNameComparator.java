package com.project.src.expense.comparators;

import com.project.src.expense.ExpenseController;

import java.util.Comparator;

/**
 * A comparator for comparing two ExpenseController objects by their expense names.
 * The comparison is case-insensitive.
 *
 * @author Angelo Ripamonti & Luca Avveduto
 * @version 1.0
 */
public class ExpenseNameComparator implements Comparator<ExpenseController> {

    /**
     * Compares two ExpenseController objects by the name of their expense models.
     *
     * @param o1 the first ExpenseController to be compared
     * @param o2 the second ExpenseController to be compared
     * @return a negative integer, zero, or a positive integer as the first argument is less than,
     * equal to, or greater than the second, ignoring case considerations
     */
    @Override
    public int compare(ExpenseController o1, ExpenseController o2) {
        return o1.getModel().getName().compareToIgnoreCase(o2.getModel().getName());
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
