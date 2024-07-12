import controlP5.ControlEvent;
import expense.Categories;
import processing.core.PApplet;

/**
 * Main Class
 * @author Angelo Ripamonti, Luca Avveduto
 * @version 1.0
 */
public class Main extends PApplet {
    private Model model;

    /**
     * Window settings
     */
    public void settings(){
        size(1600,900);
    }

    /**
     * method for initializing all variables
     */
    public void setup(){
        model = new Model(this);
        //Ogni 14 caratteri si mette \n per massimo 4 volte
    }

    /**
     * loop method
     */
    public void draw(){
        model.v.showList();
        if(model.screen[0])
            model.form.draw();
        else if(model.screen[1]) {
            model.expense.showExpense(200, 200);
        }
    }

    /**
     * submit button for the FormView callback
     */
    public void login() {
        model.form.submitForm();
    }
    /**
     * submit button for the FormView callback
     */
    public void register() {
        model.form.submitForm(true);
    }

    public void select(ControlEvent theEvent) {
        if (theEvent.isFrom(model.v.getList())) {
            int cat = (int) theEvent.getController().getValue();
            model.v.callback(cat);
        }
    }

    /**
     * Main method
     * @param args - arguments
     */
    public static void main(String[] args) {
        PApplet.main("Main");
    }
}
