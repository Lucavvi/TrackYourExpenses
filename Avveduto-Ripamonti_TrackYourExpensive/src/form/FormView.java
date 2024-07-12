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
    protected Textfield usernameField;
    protected Textfield passwordField;
    private Button registerButton;
    private Button loginButton;


    /**
     * Constructor which initialize the attributes
     * @param processing - main instance
     * @param cp5 - callback instance
     */
    FormView(PApplet processing, ControlP5 cp5){
        this.processing = processing;
        this.cp5 = cp5;
        usernameField = cp5.addTextfield("username").setColorForeground(processing.color(255)).setFont(processing.createFont("arial",25)).setPosition(processing.width/2-100,processing.height/2-40).setSize(200,40).setFocus(true).setColor(processing.color(255)).setColorActive(processing.color(255)).setColorBackground(0).setCaptionLabel("").hide();
        passwordField = cp5.addTextfield("password").setPasswordMode(true).setColorForeground(processing.color(255)).setFont(processing.createFont("arial",25)).setPosition(processing.width/2-100,processing.height/2+40).setSize(200,40).setFocus(true).setColor(processing.color(255)).setColorActive(processing.color(255)).setColorBackground(0).setCaptionLabel("").hide();
        registerButton = cp5.addButton("register").setLabel("Register").setPosition(processing.width/2-100, processing.height/2+110).setSize(80, 30).hide();
        loginButton = cp5.addButton("login").setLabel("Login").setPosition(processing.width/2, processing.height/2+110).setSize(80, 30).hide();
    }

    /**
     * loop method for each text filed
     */
    void draw(){
        processing.background(255);
        processing.textSize(30);
        processing.fill(0,0,255);
        processing.text("Form:",usernameField.getPosition()[0],usernameField.getPosition()[1]-(usernameField.getHeight()/2)-50);
        processing.textSize(20);
        processing.fill(0);
        processing.text("Username:",usernameField.getPosition()[0],usernameField.getPosition()[1]-(usernameField.getHeight()/2));
        usernameField.show();
        processing.text("Password:",passwordField.getPosition()[0],passwordField.getPosition()[1]-(passwordField.getHeight()/2));
        passwordField.show();
        registerButton.show();
        loginButton.show();
    }

    public void hideField(){
        usernameField.hide();
        passwordField.hide();
        registerButton.hide();
        loginButton.hide();
    }

    void fail(String failError){
        processing.background(255);
        processing.rectMode(processing.CENTER);
        processing.fill(255,0,0,120);
        processing.rect(processing.width/2,processing.height/2,200,100,25);
        processing.textAlign(processing.CENTER,processing.CENTER);
        processing.textSize(20);
        processing.fill(0);
        processing.text(failError,processing.width/2,processing.height/2);
        processing.rectMode(processing.CORNER);
        processing.textAlign(processing.BASELINE,processing.BASELINE);
    }
}
