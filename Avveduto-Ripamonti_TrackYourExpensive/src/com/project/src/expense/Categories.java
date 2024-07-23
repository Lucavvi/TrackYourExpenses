package com.project.src.expense;
import java.io.Serializable;
import com.project.src.expense.Color;

/**
 * Enum representing all the categories.
 * Each category is associated with a specific color and name.
 *
 * @author Angelo Ripamonti
 * @version 1.0
 */
public enum Categories implements Serializable {
    FOOD(new Color(java.awt.Color.GREEN),"Food"),
    SHOPPING(new Color(java.awt.Color.ORANGE),"Shopping"),
    PLEASURE(new Color(java.awt.Color.BLUE),"Pleasure"),
    ALL(new Color(new java.awt.Color (0,0,0,0)),"All");


    private Color color;
    private String name;

    /**
     * Constructor to initialize the category with a color and name.
     *
     * @param c the color associated with the category
     * @param n the name of the category
     */
    private Categories(Color c,String n) {
        color = c;
        name = n;
    }

    /**
     * Gets the color associated with the category.
     *
     * @return the color of the category
     */
    public Color getColor() {
        return color;
    }

    /**
     * Gets the name of the category.
     *
     * @return the name of the category
     */
    public String getName() {return name;}
}
