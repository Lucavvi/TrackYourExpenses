package expense;
import java.awt.*;
public enum Categories {
    FOOD(Color.GREEN),
    SHOPPING(Color.ORANGE),
    PLEASURE(Color.BLUE);

    private Color color;

    private Categories(Color c) {
        color = c;
    }

    public Color getColor() {
        return color;
    }
}
