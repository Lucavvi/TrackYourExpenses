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

    private FormController form;
    private ExpenseController expense;

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
        form=new FormController(this,new ControlP5(this));
        expense = new ExpenseController(new ExpenseModel("c", LocalDate.now(), Categories.FOOD,10),new ExpenseView(this));
        form=new FormController(this,new ControlP5(this));
    }

    /**
     * loop method
     */
    public void draw(){
        form.draw();
        expense.showExpense(200,200);
    }

    /**
     * submit button for the FormView callback
     */
    public void submit() {
        form.submitForm();
    }

    /**
     * Main method
     * @param args - arguments
     */
    public static void main(String[] args) {
        PApplet.main("Main");
    }
}
