import controlP5.ControlP5;
import expense.Categories;
import expense.ExpenseController;
import expense.ExpenseModel;
import expense.ExpenseView;
import form.*;
import processing.core.PApplet;
import java.time.LocalDate;

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
        if(model.screen[0])
            model.form.draw();
        else if(model.screen[1])
            model.expense.showExpense(200,200);
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

    /**
     * Main method
     * @param args - arguments
     */
    public static void main(String[] args) {
        PApplet.main("Main");
    }
}
