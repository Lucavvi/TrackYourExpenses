import controlP5.ControlP5;
import expense.Categories;
import expense.ExpenseController;
import expense.ExpenseModel;
import expense.ExpenseView;
import form.FormController;
import processing.core.PApplet;

import java.time.LocalDate;

class Model {
    ControlP5 cp5;
    FormController form;
    ExpenseController expense;

    public Model(PApplet processing) {
        cp5 = new ControlP5(processing);
        form=new FormController(processing,cp5);
        expense = new ExpenseController("c", LocalDate.now(), Categories.FOOD,10,"ciao",processing);
    }
}
