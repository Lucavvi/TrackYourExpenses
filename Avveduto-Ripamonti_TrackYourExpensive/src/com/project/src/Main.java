package com.project.src;
import com.project.src.expense.ExpensesPage;
import controlP5.*;
import com.project.src.accountManager.*;
import com.project.src.filters.*;
import processing.core.*;

import java.util.Arrays;
import java.util.Collections;

/**
 * Main application class for tracking expenses.
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
     * Main draw loop for rendering graphics.
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
        else{
            //Model.acc = model.dao.login("Angelo","qwerty");
            model.account.draw(Model.acc);
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
            model.expense.changeListToShow(model.dao.getExpensesByAccount(Model.getAccount()));
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
            Model.acc = new Account(model.form.model.getUsername(), model.form.model.getPsw());
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
            model.expense.changeListToShow(CategoryFilter.filter(model.dao.getExpensesByAccount(Model.getAccount()),model.expense.filter.callback(cat)));
        }
    }

    public void add(ControlEvent event) {
        if(event.isFrom(model.expense.getAddButton())) {
            model.expense.getAddButton().hide();
            model.screen[1] = true;
            model.expense.filter.getList().lock();
            model.expense.order.getList().lock();
            model.expense.getAdx().showMenu();
            model.expense.getSettings().hide();
            model.expense.addCallback();
        }
    }

    public void account(ControlEvent event) {
        if(event.isFrom(model.expense.getSettings())) {
            Arrays.fill(model.screen,false);
            model.expense.getSettings().hide();
            model.expense.getAddButton().hide();
            model.expense.filter.hideList();
            model.expense.order.hideList();
        }
    }

    public void category(ControlEvent event) {
        if(event.isFrom(model.expense.getAdx().getSelect())) model.expense.getAdx().categoryCallback(event);
    }

    public void exit(ControlEvent event) {
        if(event.isFrom(model.expense.getAdx().getExit())) {
            background(255);
            model.screen[1] = true;
            model.expense.getAdx().hideMenu();
            model.expense.filter.getList().unlock();
            model.expense.order.getList().unlock();
            model.expense.setOn(true);
        }
    }

    public void done(ControlEvent event) {
        if(event.isFrom(model.expense.getAdx().getDone()) &&  model.expense.getAdx().doneCallback(model,model.expense.getListToShow())) {
            model.expense.filter.showList();
            model.expense.order.showList();
            model.expense.filter.getList().unlock();
            model.expense.order.getList().unlock();
            model.screen[1] = true;
            model.expense.setOn(true);
            model.expense.changeListToShow(model.dao.getExpensesByAccount(Model.getAccount()));
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
            Collections.sort(model.dao.getExpensesByAccount(Model.getAccount()), model.expense.order.callback((int) event.getController().getValue()));
        }
    }

    /**
     * Handles account deletion.
     *
     * This method deletes the user account and resets the screen state to
     * show the login form.
     */
    public void deleteAccount() {
        model.account.deleteAccount();
        Arrays.fill(model.screen,false);
        model.screen[0] = true;
    }

    /**
     * Handles deletion of the expense list.
     *
     * This method deletes all expenses associated with the current account.
     */
    public void deleteList() {
        model.account.deleteList();
    }

    /**
     * Toggles the visibility of the account password.
     *
     * This method shows or hides the account password in the user interface.
     */
    public void accountShowPassword(){
        model.account.showPassword();
    }

    /**
     * Switches the screen to display the list of expenses.
     *
     * This method hides account management fields and shows the expense list.
     */
    public void backExpenses(){
        model.account.hideFields();
        model.screen[1]=true;
    }

    /**
     * Handles the user logout process.
     *
     * This method hides account management fields, resets the screen state to
     * show the login form, and sets the current account to null.
     */
    public void logout(){
        model.account.hideFields();
        model.screen[0]=true;
        Model.acc = null;
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
