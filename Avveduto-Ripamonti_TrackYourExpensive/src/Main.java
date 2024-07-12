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
        size(1600,900);
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
     * submit button for the FormView callback
     */
    public void submit() {
        System.out.println(fw.usernameField.getText() + " "+fw.passwordField.getText());
        //Cancello i valori all'interno dei due TextField
        fw.usernameField.clear();
        fw.passwordField.clear();
    }

    /**
     * Main method
     * @param args - arguments
     */
    public static void main(String[] args) {
        PApplet.main("Main");
    }
}
