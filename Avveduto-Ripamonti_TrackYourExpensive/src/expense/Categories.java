package expense;
import java.awt.*;
public enum Categories {
    FOOD(Color.GREEN,"Food"),
    SHOPPING(Color.ORANGE,"Shopping"),
    PLEASURE(Color.BLUE,"Pleasures");

    private Color color;
    private String name;
    private Categories(Color c,String n) {
        color = c;
        name = n;
    }

    public Color getColor() {
        return color;
    }
    public String getName() {return name;}
}
