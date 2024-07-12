package form;

import controlP5.ControlP5;
import processing.core.PApplet;

/**
 * MVC Class to control form page
 * @author Angelo Ripamonti, Luca Avveduto
 * @version 1.0
 */
public class FormController {
    private FormView view;
    private FormModel model;

    /**
     * Constructor
     * @param processing - main instance
     * @param cp5 - callback
     */
    public FormController(PApplet processing, ControlP5 cp5) {
        view = new FormView(processing, cp5);
        model = new FormModel();
    }

    /**
     * Draw the Form Page
     */
    public void draw(){
        if(model.check)
            view.fail(model.failError);
        else
            view.draw();
    }

    /**
     * When login button is pressed, the callback recall this function
     * and the function verify the credentials to login
     */
    public void submitForm(){
        String username = view.usernameField.getText();
        String password = view.passwordField.getText();
        if(password == null || username == null || username.isEmpty()  || password.isEmpty()){
            model.login(username,password);
        }
        else{
            model.check = true;
            model.failError = "Credenziali Errate!";
            view.hideField();
        }
        view.usernameField.clear();
        view.passwordField.clear();
    }

    /**
     * When register button is pressed, the callback recall this function
     * and the function verify the credentials to register
     */
    public void submitForm(boolean register){
        if(!register) submitForm();
        else {
            String username = view.usernameField.getText();
            String password = view.passwordField.getText();
            if (password == null || username == null || username.isEmpty() || password.isEmpty()) {
                model.register(username,password);
            } else {
                model.check = true;
                model.failError = "Credenziali non valide!";
                view.hideField();
            }
            view.usernameField.clear();
            view.passwordField.clear();
        }
    }
}
