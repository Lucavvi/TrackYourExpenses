package com.project.src.form;
import processing.core.PApplet;
import controlP5.*;

/**
 * View class for managing the form page in the MVC pattern.
 * This class handles the display and layout of the user interface elements
 * for login and registration forms.
 *
 * @author Angelo Ripamonti & Luca Avveduto
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
     * Constructs a FormView with the specified PApplet and ControlP5 instances.
     * Initializes the UI components such as text fields and buttons.
     *
     * @param processing the main instance of PApplet used for rendering. Must not be null
     * @param cp5 the ControlP5 instance used for UI components. Must not be null
     * @throws NullPointerException if any of the parameters are null
     */
    FormView(PApplet processing, ControlP5 cp5) throws NullPointerException{
        if(processing == null || cp5 == null) throw new NullPointerException("Almost one parameter passed is null");
        this.processing = processing;
        this.cp5 = cp5;
        usernameField = cp5.addTextfield("username").setColorForeground(processing.color(255)).setFont(processing.createFont("arial",25)).setPosition(processing.width/2-100,processing.height/2-40).setSize(200,40).setFocus(true).setColor(processing.color(255)).setColorActive(processing.color(255)).setColorBackground(0).setCaptionLabel("").hide();
        passwordField = cp5.addTextfield("password").setPasswordMode(true).setColorForeground(processing.color(255)).setFont(processing.createFont("arial",25)).setPosition(processing.width/2-100,processing.height/2+40).setSize(200,40).setFocus(true).setColor(processing.color(255)).setColorActive(processing.color(255)).setColorBackground(0).setCaptionLabel("").hide();
        registerButton = cp5.addButton("register").setLabel("Register").setPosition(processing.width/2-100, processing.height/2+110).setSize(80, 30).hide();
        loginButton = cp5.addButton("login").setLabel("Login").setPosition(processing.width/2+20, processing.height/2+110).setSize(80, 30).hide();
    }

    /**
     * Draws the form page on the screen, including the background, text fields, and buttons.
     * Displays the form with the appropriate labels and positions.
     */
    void draw(){
        processing.background(255);
        processing.fill(229,229,229);
        processing.rectMode(processing.CENTER);
        processing.rect(processing.width/2,processing.height/2,500,500,20);
        processing.rectMode(processing.CORNER);
        processing.textSize(30);
        processing.fill(0,0,255);
        processing.textAlign(processing.CENTER,processing.CENTER);
        processing.text("Form",loginButton.getPosition()[0] - 20,usernameField.getPosition()[1]-(usernameField.getHeight()/2)-50);
        processing.textSize(20);
        processing.fill(0);
        processing.textAlign(processing.BASELINE,processing.BASELINE);
        processing.text("Username:",usernameField.getPosition()[0],usernameField.getPosition()[1]-(usernameField.getHeight()/2));
        usernameField.show();
        processing.text("Password:",passwordField.getPosition()[0],passwordField.getPosition()[1]-(passwordField.getHeight()/2));
        passwordField.show();
        registerButton.show();
        loginButton.show();
    }

    /**
     * Hides all UI components of the form, including text fields and buttons.
     */
    public void hideField(){
        usernameField.hide();
        passwordField.hide();
        registerButton.hide();
        loginButton.hide();
    }

    /**
     * Displays an error message for failed login or registration attempts.
     *
     * @param failError the error message to display. Must not be null or blank.
     * @throws NullPointerException if the parameters is null
     * @throws IllegalArgumentException if the parameters is blank
     */
    void fail(String failError) throws NullPointerException, IllegalArgumentException{
        if(failError == null) throw new NullPointerException("The parameter passed is null");
        if(failError.isBlank()) throw new IllegalArgumentException("The parameter passed is blank");
        draw();
        processing.fill(255,0,0);
        processing.textAlign(processing.CENTER,processing.CENTER);
        processing.text(failError,loginButton.getPosition()[0] - 20,registerButton.getPosition()[1] + (registerButton.getHeight()*2));
        processing.textAlign(processing.BASELINE,processing.BASELINE);
    }
}
