package form;

import controlP5.ControlP5;
import processing.core.PApplet;

public class FormController {
    private FormView view;
    private FormModel model;

    public FormController(PApplet processing, ControlP5 cp5) {
        view = new FormView(processing, cp5);
        model = new FormModel();
    }

    public void draw(){
        if(model.isCheck())
            view.fail(model.getFailError());
        else
            view.draw();
    }

    public void submitForm(){
        String username = view.usernameField.getText();
        String password = view.passwordField.getText();
        if(password == null || username == null || username.isEmpty()  || password.isEmpty()){
            //controllare che username e password corrispondano ad una voce del db
        }
        else{
            model.setCheck(true);
            model.setFailError("Credenziali Errate!");
            view.hideField();
        }
        view.usernameField.clear();
        view.passwordField.clear();
    }
    public void submitForm(boolean register){
        if(!register) submitForm();
        else {
            String username = view.usernameField.getText();
            String password = view.passwordField.getText();
            if (password == null || username == null || username.isEmpty() || password.isEmpty()) {
                //Controllare username che non esista, successivamente aggiungere al db
            } else {
                model.setCheck(true);
                model.setFailError("Credenziali non valide!");
                view.hideField();
            }
            view.usernameField.clear();
            view.passwordField.clear();
        }
    }
}
