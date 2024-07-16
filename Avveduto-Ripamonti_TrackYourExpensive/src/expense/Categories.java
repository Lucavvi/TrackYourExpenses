package expense;
import java.io.Serializable;

/**
 * Enum with all the categories
 * @author luke & Angelo
 * @version 1.0
 */
public enum Categories implements Serializable {
    FOOD(new Color(java.awt.Color.GREEN),"Food"),
    SHOPPING(new Color(java.awt.Color.ORANGE),"Shopping"),
    PLEASURE(new Color(java.awt.Color.BLUE),"Pleasures"),
    ALL(new Color(new java.awt.Color (0,0,0,0)),"All");


    private Color color;
    private String name;

    /**
     * @param c - color
     * @param n - name
     * constructor
     */
    private Categories(Color c,String n) {
        color = c;
        name = n;
    }

    /**
     * @return the category's color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @return the category's name
     */
    public String getName() {return name;}
}
