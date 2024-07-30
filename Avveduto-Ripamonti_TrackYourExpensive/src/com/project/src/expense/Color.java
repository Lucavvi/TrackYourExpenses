package com.project.src.expense;

import java.io.Serializable;

/**
 * Class representing a color.
 * This class encapsulates a color value and provides a method to retrieve its RGB representation.
 *
 * @author Angelo Ripamonti & Luca Avveduto
 * @version 1.0
 */
public class Color implements Serializable {
    private int value;

    /**
     * Constructs a Color object from a java.awt.Color object.
     *
     * @param color the java.awt.Color object
     */
    public Color(java.awt.Color color){
        this.value = (color.getAlpha() << 24) | (color.getRed() << 16) | (color.getGreen() << 8) | color.getBlue();
    }

    /**
     * Gets the RGB value of the color.
     *
     * @return the RGB value of the color
     */
    public int getRGB(){
        return value;
    }

}
