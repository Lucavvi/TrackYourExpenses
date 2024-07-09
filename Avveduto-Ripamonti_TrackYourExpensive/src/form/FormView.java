package form;

import processing.core.PApplet;
import controlP5.*;

public class FormView {
    private PApplet processing;
    private ControlP5 cp5;
    private Textfield tf;
    public FormView(PApplet processing, ControlP5 cp5){
        this.processing = processing;
        this.cp5 = cp5;
        tf = cp5.addTextfield("username").setColorForeground(processing.color(255)).setFont(processing.createFont("arial",25)).setPosition(10,100).setSize(200,40).setFocus(true).setColor(processing.color(255)).setColorActive(processing.color(255)).setAutoClear(true).setColorBackground(0).hide();
    }
    public void draw(){
        processing.background(255);
        tf.show();
    }
}
