package form;
import processing.core.PApplet;
import controlP5.*;

/**
 * MVC Class
 * @author Angelo Ripamonti, Luca Avveduto
 * @version 1.0
 */
public class FormView {
    private PApplet processing;
    private ControlP5 cp5;
    public Textfield tf;
    public Textfield lf;
    private Button submitButton;

    /**
     * Constructor which initialize the attributes
     * @param processing - main instance
     * @param cp5 - callback instance
     */
    public FormView(PApplet processing, ControlP5 cp5){
        this.processing = processing;
        this.cp5 = cp5;
        tf = cp5.addTextfield("username").setColorForeground(processing.color(255)).setFont(processing.createFont("arial",25)).setPosition(10,processing.height/2-40).setSize(200,40).setFocus(true).setColor(processing.color(255)).setColorActive(processing.color(255)).setColorBackground(0).hide();
        lf = cp5.addTextfield("password").setColorForeground(processing.color(255)).setFont(processing.createFont("arial",25)).setPosition(processing.width/2,processing.height/2+40).setSize(200,40).setFocus(true).setColor(processing.color(255)).setColorActive(processing.color(255)).setColorBackground(0).hide();
        submitButton = cp5.addButton("submit")
                .setLabel("Submit")
                .setPosition(50, 150)
                .setSize(80, 30);


    }

    /**
     * loop method for each text filed
     */
    public void draw(){
        processing.background(255);
        tf.show();
        lf.show();
    }
}
