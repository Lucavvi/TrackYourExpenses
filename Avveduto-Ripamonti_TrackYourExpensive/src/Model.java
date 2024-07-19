import controlP5.ControlP5;
import dao.DBManager;
import expense.Categories;
import expense.*;
import filters.FilterObj;
import form.FormController;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Arrays;

class Model {
    ControlP5 cp5;
    FormController form;
    ArrayList<ExpenseController> listToShow;
    FilterObj v;
    DBManager dao;
    Categories target;
    boolean[] screen;

    public Model(PApplet processing) {
        cp5 = new ControlP5(processing);
        v = new FilterObj(cp5,processing);
        dao= new DBManager();
        form=new FormController(processing,cp5,dao);
        screen = new boolean [10];
        Arrays.fill(screen,false);
        screen[0]=true;
    }
}
