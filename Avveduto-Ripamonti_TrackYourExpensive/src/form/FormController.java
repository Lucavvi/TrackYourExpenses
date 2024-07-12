package form;

import controlP5.ControlP5;
import processing.core.PApplet;

public class FormController {
    private FormView view;
    private FormModel model;
    private boolean check;

    public FormController(PApplet processing, ControlP5 cp5) {
        view = new FormView(processing, cp5);
        check=false;
    }

    public void draw(){
        if(check)
            view.fail();
        else
            view.draw();
    }

    public void submitForm(){
        String username = view.usernameField.getText();
        String password = view.passwordField.getText();
        if(password == null || username == null || username.isEmpty()  || password.isEmpty()){
            //Salvare/Controllare/mandare a qualcun'altro username e password

            //Cancello i valori all'interno dei due TextField
            view.usernameField.clear();
            view.passwordField.clear();
        }
        else{
            check=true;
            view.fail();
            view.hideField();
        }
    }
}
