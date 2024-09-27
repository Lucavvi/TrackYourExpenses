package com.project.src;
import controlP5.*;
import com.project.src.accountManager.*;
import com.project.src.filters.*;
import processing.core.*;
import processing.event.MouseEvent;

import java.util.Arrays;


/**
 * Main application class for tracking expenses.
 * Extends PApplet to use the Processing framework for graphical
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
     * a new Model instance, and populating the list of expenses.
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
        else if(model.screen[2]) {
           model.graphicPage.showInterface(model.expense.getListToShow());
        }
        else{
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

    public void home() {
        model.graphicPage.hideInterface();
        Arrays.fill(model.screen,false);
        model.screen[1] = true;
    }

    /**
     * Handles selection events from the dropdown list.
     *
     * This method updates the list of expenses to display based on the
     * selected category from the dropdown list.
     *
     * @param theEvent the ControlEvent containing the select data
     */
    public void select(ControlEvent theEvent) {
        if (theEvent.isFrom(model.expense.filter.getList())) {
            int cat = (int) theEvent.getController().getValue();
            model.expense.changeListToShow(CategoryFilter.filter(model.dao.getExpensesByAccount(Model.getAccount()),model.expense.filter.callback(cat)));
        }
    }

    /**
     * Switches the screen to display the graphic interface.
     *
     * This method hides various UI elements and sets the screen state to
     * show the graphical representation of expenses.
     */
    public void graphic() {
        Arrays.fill(model.screen,false);
        model.expense.getSettings().hide();
        model.expense.getAddButton().hide();
        model.expense.filter.hideList();
        model.expense.order.hideList();
        model.expense.getGraphic().hide();
        model.screen[2] = true;
    }

    /**
     * Handles the event when the add expense button is pressed.
     *
     * This method hides the add button, locks filters, shows the add expense
     * menu, and triggers the add expense callback.
     *
     * @param event the ControlEvent containing the event data
     */
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

    /**
     * Handles the event when a range selection is made in the graphic interface.
     *
     * This method calls the select callback for the graphic page.
     *
     * @param event the ControlEvent containing the event data
     */
    public void range(ControlEvent event) {
        if(event.isFrom(model.graphicPage.getSelect())) {
            model.graphicPage.selectCallBack(event);
        }
    }

    /**
     * Handles the event when the account settings button is pressed.
     *
     * This method hides various UI elements and sets the screen state to
     * show the account settings.
     *
     * @param event the ControlEvent containing the event data
     */
    public void account(ControlEvent event) {
        if(event.isFrom(model.expense.getSettings())) {
            Arrays.fill(model.screen,false);
            model.expense.getSettings().hide();
            model.expense.getAddButton().hide();
            model.expense.filter.hideList();
            model.expense.order.hideList();
            model.expense.getGraphic().hide();
        }
    }

    /**
     * Handles the event when a category is selected in the add expense menu.
     *
     * This method calls the category callback for the add expense menu.
     *
     * @param event the ControlEvent containing the event data
     */
    public void category(ControlEvent event) {
        if(event.isFrom(model.expense.getAdx().getSelect())) model.expense.getAdx().categoryCallback(event);
    }

    /**
     * Handles the event when the exit button is pressed in the add expense menu.
     *
     * This method hides the add expense menu and resets the screen state to
     * show the main expense list.
     *
     * @param event the ControlEvent containing the event data
     */
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

    /**
     * Handles the event when the done button is pressed in the add expense menu.
     *
     * This method triggers the done callback for the add expense menu, updates
     * the list of expenses, and resets the screen state to show the main expense list.
     *
     * @param event the ControlEvent containing the event data
     */
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
     * @param event the ControlEvent containing the selection data
     */
    public void reorder(ControlEvent event) {
        if (event.isFrom(model.expense.order.getList())) {
           model.expense.reorderCallback(model.expense.order.callback((int) event.getController().getValue()));
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
        model.account.hideFields();
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
        model.expense.changeListToShow(model.dao.getExpensesByAccount(Model.getAccount()));
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
     * Exports the current account data to a JSON file.
     *
     * This method calls the "AccountPage.exportData()" method to create
     * a directory if it does not exist and writes the account data to a JSON file.
     * It handles any potential errors during the directory creation or file writing
     * process and updates the user interface with appropriate success or failure messages.
     */
    public void exportData(){
        model.account.exportData();
    }

    /**
     * Handles mouse wheel events to scroll through the list of expenses.
     *
     * This method is called whenever the user scrolls the mouse wheel. It updates the
     * scrolling position of the expense list based on the scroll direction and speed.
     *
     * @param event the MouseEvent instance containing the scroll data
     */
    public void mouseWheel(MouseEvent event){
        model.expense.mouseWheel(event);
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
