import controlP5.ControlP5;
import form.*;
import processing.core.PApplet;

/**
 * Main Class
 * @author Angelo Ripamonti, Luca Avveduto
 * @version 1.0
 */
public class Main extends PApplet {
    private FormView fw;

    /**
     * Window settings
     */
    public void settings(){
        size(500,500);
    }

    /**
     * method for initializing all variables
     */
    public void setup(){
        fw=new FormView(this,new ControlP5(this));
    }

    /**
     * loop method
     */
    public void draw(){
        fw.draw();
    }

    /**
     * Username text field callback
     * @param txt - text field content
     */
    public void username(String txt) {
        System.out.println(txt);
    }

    /**
     * Main method
     * @param args - arguments
     */
    public static void main(String[] args) {
        PApplet.main("Main");
    }
}
