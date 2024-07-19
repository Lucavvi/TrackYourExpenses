import controlP5.ControlEvent;
import expense.Categories;
import expense.ExpenseController;
import expense.LocalDate;
import processing.core.PApplet;
import java.util.ArrayList;

/**
 * Main Class
 * @author Angelo Ripamonti, Luca Avveduto
 * @version 1.0
 */
public class Main extends PApplet {
    private Model model;
    ArrayList<ExpenseController> e;

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
        e = new ArrayList<>();
        for(int i=0;i<10;i++) e.add(new ExpenseController("c", new LocalDate(java.time.LocalDate.now()), Categories.FOOD,10,"ciao",this));
        //Ogni 14 caratteri si mette \n per massimo 4 volte
    }

    /**
     * loop method
     */
    public void draw(){
        background(255);
        if(model.screen[0]) {
            model.form.draw();
        }
        else if(model.screen[1]) {
            model.v.showList();
            model.expense.renderList(this,e,50,50);
        }
    }

    /**
     * submit button for the FormView callback
     */
    public void login() {
        model.form.submitForm();
        model.screen[0] = false;
        model.screen[1] = true;
    }
    /**
     * submit button for the FormView callback
     */
    public void register() {
        model.form.submitForm(true);
        model.screen[0] = false;
        model.screen[1] = true;
    }

    /**
     * @param theEvent - from the dropdown list
     * select callback.
     */
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
