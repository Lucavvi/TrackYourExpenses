package com.project.src.form;

import com.project.src.Model;
import controlP5.ControlP5;
import com.project.src.accountManager.*;
import processing.core.PApplet;

/**
 * MVC Controller class for managing the form page.
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
     * @param processing the PApplet instance used for rendering. Must not be null.
     * @param cp5 the ControlP5 instance used for user interface controls. Must not be null.
     * @param manager the DBManager instance used for database operations. Must not be null.
     * @throws NullPointerException if any of the parameters are null
     */
    public FormController(PApplet processing, ControlP5 cp5, DBManager manager) throws NullPointerException {
        if(processing == null || cp5 == null || manager == null) throw new NullPointerException("Almost one parameter passed is null");
        view = new FormView(processing, cp5);
        model = new FormModel(manager);
    }

    /**
     * Draws the form page.
     * If there is a failure condition, displays an error message; otherwise, renders the form.
     */
    public void draw(){
        if(model.check)
            view.fail(model.failError);
        else
            view.draw();
    }

    /**
     * Handles the form submission for login.
     * Verifies the credentials and logs in the user. Displays an error message if credentials are invalid.
     */
    public boolean submitForm(){
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
            model.failError = "Wrong Credentials!";
            return false;
        }
    }

    /**
     * Handles the form submission for registration.
     * Verifies the credentials and registers the user if the `register` flag is true.
     * Displays an error message if credentials are invalid or registration fails.
     *
     * @param register if true, registers a new user; if false, performs login
     */
    public boolean submitForm(boolean register) {
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
                    model.failError = "Wrong Credentials!";
                    return false;
                }
                view.hideField();
                return true;
            } else {
                model.check = true;
                model.failError = "Not valid credentials!";
                return false;
            }
        }
    }
}
