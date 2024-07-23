package com.project.src;

import controlP5.*;
import com.project.src.dao.*;
import com.project.src.expense.*;
import com.project.src.filters.*;
import processing.core.*;

/**
 * com.project.src.Main application class for tracking expenses.
 * Extends {@link PApplet} to use the Processing framework for graphical
 * user interface and rendering.
 *
 * This class initializes the application window, manages the main
 * application logic, and handles user interactions such as com.project.src.form
 * submissions and filtering expenses.
 *
 * @author Angelo Ripamonti & Luca Avveduto
 * @version 1.0
 */
public class Main extends PApplet {
    private Model model;
    /**
     * Configures the size of the application window.
     */
    public void settings(){
        size(1600,900);
    }

    /**
     * Initializes the application by setting the window title, creating
     * a new {@link Model} instance, and populating the list of expenses.
     *
     * This method also sets up sample expenses and initializes the list
     * to be displayed.
     */
    public void setup(){
        windowTitle("Track Your Expenses");
        model = new Model(this);
    }

    /**
     * com.project.src.Main draw loop for rendering graphics.
     *
     * This method updates the display based on the current screen state
     * and renders the list of expenses.
     */
    public void draw(){
        background(255);
        if(model.screen[0]) {
            model.form.draw();
        }
        else if(model.screen[1]) {
            model.listToShow = model.dao.getExpensesByAccount(model.acc);
            model.v.showList();
            model.reorder.showList();
            fill(229,229,229);
            stroke(229,229,229);
            rect(0,0,width,50);
            stroke(0);
            if(!model.listToShow.isEmpty()) ExpenseController.renderList(this,model.listToShow,50,80);
            else {
                textAlign(CENTER,CENTER);
                text("No expense yet",width/2,height/2);
            }
        }
    }

    /**
     * Handles the login com.project.src.form submission.
     *
     * This method calls the login function from the FormView class and
     * switches the screen state to show the main expense list.
     */
    public void login() {
        if (model.form.submitForm()) {
            model.screen[0] = false;
            model.screen[1] = true;
        }
    }

    /**
     * Handles the register com.project.src.form submission.
     *
     * This method calls the register function from the FormView class and
     * switches the screen state to show the main expense list.
     */
    public void register() {
        if(model.form.submitForm(true)) {
            model.screen[0] = false;
            model.screen[1] = true;
            model.acc = new Account(model.form.model.getUsername(), model.form.model.getPsw());
        }
    }

    /**
     * Handles selection events from the dropdown list.
     *
     * This method updates the list of expenses to display based on the
     * selected category from the dropdown list.
     *
     * @param theEvent the {@link ControlEvent} containing the selection data
     */
    public void select(ControlEvent theEvent) {
        if (theEvent.isFrom(model.v.getList())) {
            int cat = (int) theEvent.getController().getValue();
            model.listToShow = CategoryFilter.filter(model.listToShow,model.v.callback(cat));
        }
    }

    /**
     * Handles selection events from the dropdown list.
     *
     * This method reorder the list of expenses to display based on the
     * order from the dropdown list.
     *
     * @param event the {@link ControlEvent} containing the selection data
     */
    public void reorder(ControlEvent event) {
        if (event.isFrom(model.reorder.getList())) {
            //Collections.sort(e,model.reorder.callback((int) event.getController().getValue()));
        }
    }

    /**
     * The main method to launch the application.
     *
     * @param passedArgs command-line arguments
     */
    public static void main(String[] passedArgs) {
        String[] appletArgs = new String[]{"com.project.src.Main"};
        if (passedArgs != null) {
            PApplet.main(concat(appletArgs, passedArgs));
        } else {
            PApplet.main(appletArgs);
        }
    }
}
