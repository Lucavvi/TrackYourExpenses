package com.project.src.filters;
import controlP5.*;
import com.project.src.expense.Categories;
import processing.core.PApplet;

/**
 * Class for managing and displaying a dropdown list used for filtering expenses by category.
 * This class uses the ControlP5 library to create a dropdown list for selecting an expense category.
 *
 * @author Angelo Ripamonti & Luca Avveduto
 * @version 1.0
 */
public class FilterObj {
    private ControlP5 cp5;
    private PApplet parent;
    private DropdownList select;

    /**
     * Constructs a FilterObj with the specified ControlP5 instance and PApplet parent.
     * Initializes the dropdown list with predefined categories.
     *
     * @param cp5 the ControlP5 instance used for UI controls
     * @param parent the PApplet instance used for drawing
     */
    public FilterObj(ControlP5 cp5, PApplet parent) {
        this.cp5 = cp5;
        this.parent = parent;
        select = cp5.addDropdownList("select")
                .setLabel("Filter").setBarHeight(20)
                .setItemHeight(20).setPosition(parent.width-parent.width/4,15)
                .setSize(200,100).close()
                .setColorBackground(0).hide();
        select.addItem("FOOD", Categories.FOOD);
        select.addItem("SHOPPING", Categories.SHOPPING);
        select.addItem("PLEASURE", Categories.PLEASURE);
        select.addItem("ALL", Categories.ALL);
    }

    /**
     * Converts the index of the selected item to a Categories enum value.
     *
     * @param cat the index of the selected item
     * @return the corresponding Categories value
     */
    public Categories callback(int cat) {
        Categories target = null;
        switch (cat) {
            case 0 -> {target = Categories.FOOD;}
            case 1 -> {target = Categories.SHOPPING;}
            case 2 -> {target = Categories.PLEASURE;}
            case 3 -> {target = Categories.ALL;}
        }
        return target;
    }

    /**
     * Shows the dropdown list.
     */
    public void showList() {select.show();}

    /**
     * Hides the dropdown list.
     */
    public void hideList() {select.hide();}

    /**
     * Gets the DropdownList instance.
     *
     * @return the DropdownList instance
     */
    public DropdownList getList() {return select;}
}
