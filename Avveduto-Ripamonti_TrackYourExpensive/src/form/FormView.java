package form;
import processing.core.PApplet;
import controlP5.*;

/**
 * MVC Class
 * @author Angelo Ripamonti, Luca Avveduto
 * @version 1.0
 */
class FormView {
    private PApplet processing;
    private ControlP5 cp5;
    public Textfield usernameField;
    public Textfield passwordField;
    private Button submitButton;

    /**
     * Constructor which initialize the attributes
     * @param processing - main instance
     * @param cp5 - callback instance
     */
    FormView(PApplet processing, ControlP5 cp5){
        this.processing = processing;
        this.cp5 = cp5;
        usernameField = cp5.addTextfield("username").setColorForeground(processing.color(255)).setFont(processing.createFont("arial",25)).setPosition(processing.width/2-100,processing.height/2-40).setSize(200,40).setFocus(true).setColor(processing.color(255)).setColorActive(processing.color(255)).setColorBackground(0).setLabelVisible(false).hide();
        passwordField = cp5.addTextfield("password").setColorForeground(processing.color(255)).setFont(processing.createFont("arial",25)).setPosition(processing.width/2-100,processing.height/2+40).setSize(200,40).setFocus(true).setColor(processing.color(255)).setColorActive(processing.color(255)).setColorBackground(0).setLabelVisible(false).hide();
        submitButton = cp5.addButton("submit").setLabel("Submit").setPosition(processing.width/2-40, processing.height/2+110).setSize(80, 30).hide();
    }

    /**
     * loop method for each text filed
     */
    void draw(){
        processing.background(255);
        processing.textSize(20);
        processing.fill(0);
        processing.text("Username:",usernameField.getPosition()[0],usernameField.getPosition()[1]-(usernameField.getHeight()/2));
        usernameField.show();
        processing.text("Password:",passwordField.getPosition()[0],passwordField.getPosition()[1]-(passwordField.getHeight()/2));
        passwordField.show();
        submitButton.show();
    }

    public void hideField(){
        usernameField.hide();
        passwordField.hide();
        submitButton.hide();
    }

    void fail(){
        processing.background(255);
        processing.rectMode(processing.CENTER);
        processing.fill(255,0,0,120);
        processing.rect(processing.width/2,processing.height/2,200,100,25);
        processing.textAlign(processing.CENTER,processing.CENTER);
        processing.textSize(20);
        processing.fill(0);
        processing.text("Credenziali Errate!",processing.width/2,processing.height/2);
        processing.rectMode(processing.CORNER);
        processing.textAlign(processing.BASELINE,processing.BASELINE);
    }
}
