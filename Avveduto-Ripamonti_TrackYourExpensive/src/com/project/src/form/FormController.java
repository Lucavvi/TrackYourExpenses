package com.project.src.form;

import com.project.src.Model;
import controlP5.ControlP5;
import com.project.src.accountManager.*;
import processing.core.PApplet;

/**
 * MVC Controller class for managing the com.project.src.form page.
 * Handles interactions between the view and the model, including user input and com.project.src.form submission.
 *
 * @version 1.0
 */
public class FormController {
    private FormView view;
    public FormModel model;

    /**
     * Constructs a FormController with the specified PApplet and ControlP5 instances, and a DBManager.
     *
     * @param processing the PApplet instance used for rendering
     * @param cp5 the ControlP5 instance used for user interface controls
     * @param manager the DBManager instance used for database operations
     * @throws NullPointerException if any of the parameters are null
     */
    public FormController(PApplet processing, ControlP5 cp5, DBManager manager) throws NullPointerException {
        if(processing == null || cp5 == null) throw new NullPointerException("L'implementazione di Processing o della libreria ControlP5 è fallita!");
            view = new FormView(processing, cp5);
            model = new FormModel(manager);
    }

    /**
     * Draws the com.project.src.form page.
     * If there is a failure condition, displays an error message; otherwise, renders the com.project.src.form.
     */
    public void draw(){
        if(model.check)
            view.fail(model.failError);
        else
            view.draw();
    }

    /**
     * Handles the com.project.src.form submission for login.
     * Verifies the credentials and logs in the user. Displays an error message if credentials are invalid.
     *
     * @throws AccessException if the credentials are incorrect or missing
     */
    public boolean submitForm() throws AccessException{
        String username = view.usernameField.getText();
        String password = view.passwordField.getText();
        view.usernameField.clear();
        view.passwordField.clear();
        if(password != null && username != null && !username.isEmpty()  && !password.isEmpty()){
            try {
                 Account acc = model.login(username, password);
                 Model.changeAccount(acc);
            }
            catch(AccessException e){
                model.check = true;
                model.failError = e.getMessage();
                return false;
            }
            view.hideField();
            return true;
        }
        else{
            model.check = true;
            model.failError = "Credenziali Errate!";
            return false;
        }
    }

    /**
     * Handles the com.project.src.form submission for registration.
     * Verifies the credentials and registers the user if the `register` flag is true.
     * Displays an error message if credentials are invalid or registration fails.
     *
     * @param register if true, registers a new user; if false, performs login
     * @throws UsernameException if the username already exists
     */
    public boolean submitForm(boolean register) throws UsernameException {
        if(!register) return submitForm();
        else {
            String username = view.usernameField.getText();
            String password = view.passwordField.getText();
            view.usernameField.clear();
            view.passwordField.clear();
            if (password != null && username != null && !username.isEmpty() && !password.isEmpty()) {
                try {
                    model.register(username, password);
                }
                catch(UsernameException e){
                    model.check = true;
                    model.failError = "Credenziali Errate!";
                    return false;
                }
                view.hideField();
                return true;
            } else {
                model.check = true;
                model.failError = "Credenziali non valide!";
                return false;
            }
        }
    }
}
