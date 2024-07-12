import controlP5.ControlP5;
import expense.Categories;
import expense.ExpenseController;
import expense.ExpenseModel;
import expense.ExpenseView;
import form.FormController;
import processing.core.PApplet;

import java.time.LocalDate;
import java.util.ArrayList;

class Model {
    ControlP5 cp5;
    FormController form;
    ExpenseController expense;
    boolean[] screen;

    public Model(PApplet processing) {
        cp5 = new ControlP5(processing);
        form=new FormController(processing,cp5);
        expense = new ExpenseController("c", LocalDate.now(), Categories.FOOD,10,"ciao",processing);
        screen = new boolean [10];
        for(int i = 0; i < 10; i++) screen[i] = false;
        screen[0]=true;
    }
}
