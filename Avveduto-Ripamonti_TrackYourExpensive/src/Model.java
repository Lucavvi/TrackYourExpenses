import controlP5.ControlP5;
import expense.Categories;
import expense.ExpenseController;
import filters.FilterObj;
import form.FormController;
import processing.core.PApplet;

import java.time.LocalDate;
import java.util.Arrays;

class Model {
    ControlP5 cp5;
    FormController form;
    ExpenseController expense;
    FilterObj v;
    boolean[] screen;

    public Model(PApplet processing) {
        cp5 = new ControlP5(processing);
        v = new FilterObj(cp5,processing);
        form=new FormController(processing,cp5);
        expense = new ExpenseController("c", LocalDate.now(), Categories.FOOD,10,"ciao",processing);
        screen = new boolean [10];
        Arrays.fill(screen,false);
        screen[0]=true;
    }
}
