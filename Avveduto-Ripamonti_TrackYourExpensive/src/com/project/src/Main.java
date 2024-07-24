package com.project.src;
import controlP5.*;
import com.project.src.accountManager.*;
import com.project.src.filters.*;
import processing.core.*;

import java.util.Arrays;
import java.util.Collections;

/**
 * com.project.src.Main application class for tracking expenses.
 * Extends {@link PApplet} to use the Processing framework for graphical
 * user interface and rendering.
 *
 * This class initializes the application window, manages the main
 * application logic, and handles user interactions such as form
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
        if(model.screen[0]) {
            model.form.draw();
        }
        else if(model.screen[1]) {
            model.expense.draw();
        }
    }

    /**
     * Handles the login form submission.
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
     * Handles the register form submission.
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
        if (theEvent.isFrom(model.expense.filter.getList())) {
            int cat = (int) theEvent.getController().getValue();
            model.expense.changeListToShow(CategoryFilter.filter(model.expense.getListToShow(),model.expense.filter.callback(cat)));
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
        if (event.isFrom(model.expense.order.getList())) {
            Collections.sort(model.expense.getListToShow(), model.expense.order.callback((int) event.getController().getValue()));
        }
    }

    public void deleteAccount() {
        model.account.deleteAccount();
        Arrays.fill(model.screen,false);
        model.screen[0] = true;
    }
    public void accountShowPassword(){
        model.account.showPassword();
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
