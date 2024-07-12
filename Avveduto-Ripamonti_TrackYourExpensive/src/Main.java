import controlP5.ControlP5;
import form.*;
import processing.core.PApplet;

/**
 * Main Class
 * @author Angelo Ripamonti, Luca Avveduto
 * @version 1.0
 */
public class Main extends PApplet {

    private FormController form;


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
        form=new FormController(this,new ControlP5(this));
    }

    /**
     * loop method
     */
    public void draw(){
        form.draw();
    }

    /**
     * submit button for the FormView callback
     */
    public void submit() {
        form.submitForm();
    }

    /**
     * Main method
     * @param args - arguments
     */
    public static void main(String[] args) {
        PApplet.main("Main");
    }
}
