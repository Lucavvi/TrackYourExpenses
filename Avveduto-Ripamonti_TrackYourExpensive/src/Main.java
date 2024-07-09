import controlP5.ControlP5;
import form.*;
import processing.core.PApplet;

/**
 * Classe Main
 * @author Angelo Ripamonti, Luca Avveduto
 * @version 1.0
 */
public class Main extends PApplet {
    private FormView fw;

    public void settings(){
        size(500,500);
    }
    public void setup(){
        fw=new FormView(this,new ControlP5(this));
    }
    public void draw(){
        fw.draw();
    }

    //CallBack ControlP5
    public void username(String txt) {
        System.out.println(txt);
    }

    public static void main(String[] args) {
        PApplet.main("Main");
    }
}
