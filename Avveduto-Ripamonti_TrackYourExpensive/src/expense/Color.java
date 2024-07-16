package expense;

import java.io.Serializable;

public class Color implements Serializable {
    private int value;
    public Color(java.awt.Color color){
        this.value = (color.getAlpha() << 24) | (color.getRed() << 16) | (color.getGreen() << 8) | color.getBlue();
    }

    public int getRGB(){
        return value;
    }

}
