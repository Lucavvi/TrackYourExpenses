package form;

import processing.core.PApplet;
import controlP5.*;

public class FormView {
    PApplet processing;
    ControlP5 cp5;
    Textfield tf;
    public FormView(PApplet processing, ControlP5 cp5){
        this.processing = processing;
        this.cp5 = cp5;
        tf = cp5.addTextfield("username").setPosition(10,100).setSize(200,40).setFocus(true).setColor(processing.color(255,0,0)).setAutoClear(true).hide();
    }
    public void draw(){
        processing.background(255);
        tf.show();
    }
}
