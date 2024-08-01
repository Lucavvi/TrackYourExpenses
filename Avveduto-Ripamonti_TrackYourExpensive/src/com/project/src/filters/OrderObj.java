package com.project.src.filters;

import controlP5.ControlP5;
import controlP5.DropdownList;
import com.project.src.expense.ExpenseController;
import com.project.src.expense.comparators.*;
import processing.core.PApplet;
import java.util.Comparator;

/**
 * Class for managing and displaying a dropdown list used for ordering expenses.
 * This class uses the ControlP5 library to create a dropdown list for selecting the order criteria for expenses.
 *
 * @author Angelo Ripamonti & Luca Avveduto
 * @version 1.0
 */
public class OrderObj {
    private ControlP5 cp5;
    private PApplet parent;
    private DropdownList reorder;

    /**
     * Constructs an OrderObj with the specified ControlP5 instance and PApplet parent.
     * Initializes the dropdown list with predefined ordering options.
     *
     * @param cp5 the ControlP5 instance used for UI controls. Must not be null.
     * @param parent the PApplet instance used for drawing. Must not be null.
     * @throws NullPointerException if any of the parameters are null.
     */
    public OrderObj(ControlP5 cp5, PApplet parent) throws NullPointerException{
        if(cp5 == null || parent == null) throw new NullPointerException("Almost one parameter passed is null");
        this.cp5 = cp5;
        this.parent = parent;
        reorder = cp5.addDropdownList("reorder")
                .setLabel("reorder").setBarHeight(20)
                .setItemHeight(20).setPosition(parent.width/2,15)
                .setSize(200,100).close()
                .setColorBackground(0).hide();
        reorder.addItem("Date descending", 0);
        reorder.addItem("Date ascending",1);
        reorder.addItem("Name descending",2);
        reorder.addItem("Name ascending",3);
        reorder.addItem("Amount descending",4);
        reorder.addItem("Amount ascending",5);
    }

    /**
     * Returns the appropriate comparator based on the selected ordering criteria.
     *
     * @param cat the index of the selected ordering option. Must not be less than 0 or more than 5.
     * @return a Comparator for ordering ExpenseController objects based on the selected criteria
     * @throws IllegalArgumentException if cat is less than 0 or more than 5.
     */
    public Comparator<ExpenseController> callback(int cat) throws IllegalArgumentException{
        Comparator<ExpenseController> comparator = null;
        switch (cat) {
            case 0 -> {comparator = new ExpenseDateComparator().reversed();}
            case 1 -> {comparator = new ExpenseDateComparator();}
            case 2 -> {comparator = new ExpenseNameComparator().reversed();}
            case 3 -> {comparator = new ExpenseNameComparator();}
            case 4 -> {comparator = new ExpenseAmountComparator().reversed();}
            case 5 -> {comparator = new ExpenseAmountComparator();}
            default -> {throw new IllegalArgumentException("cat is less than 0 or more than 5");}
        }
        return comparator;
    }

    /**
     * Shows the dropdown list for selecting the ordering criteria.
     */
    public void showList() {reorder.show();}

    /**
     * Hides the dropdown list for selecting the ordering criteria.
     */
    public void hideList() {reorder.hide();}

    /**
     * Gets the DropdownList instance used for ordering.
     *
     * @return the DropdownList instance
     */
    public DropdownList getList() {return reorder;}
}
