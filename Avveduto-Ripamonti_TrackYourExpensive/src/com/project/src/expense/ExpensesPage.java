package com.project.src.expense;

import com.project.src.Model;
import com.project.src.accountManager.DBManager;
import com.project.src.filters.FilterObj;
import com.project.src.filters.OrderObj;
import controlP5.Button;
import controlP5.ControlP5;
import processing.core.*;
import processing.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Represents the page for displaying and managing expenses.
 *
 * @author Angelo Ripamonti & Luca Avveduto
 * @version 1.0
 */
public class ExpensesPage {
    private PApplet processing;
    private ControlP5 cp5;
    private DBManager database;
    private ArrayList<ExpenseController> listToShow;
    public FilterObj filter;
    public OrderObj order;
    private Button add;
    private AddExpense adx;
    private Button settings;
    private Button graphic;
    private boolean on;
    private int contentHeight;
    private int scrollPos;
    private int scrollSpeed;

    /**
     * Constructs an ExpensesPage with the specified processing, ControlP5 instance, and database manager.
     *
     * @param processing the PApplet instance for Processing library functions. Must not be null.
     * @param cp5 the ControlP5 instance for GUI elements. Must not be null.
     * @param database the DBManager instance for database operations. Must not be null.
     * @throws NullPointerException if any of the parameters are null.
     */
    public ExpensesPage(PApplet processing, ControlP5 cp5, DBManager database) throws NullPointerException{
        if(cp5 == null || processing == null || database == null) throw new NullPointerException("Almost one parameter passed is null");
        this.processing = processing;
        this.cp5 = cp5;
        this.database = database;
        this.listToShow = null;
        on = true;
        contentHeight = 2000;
        scrollPos = 0;
        scrollSpeed = 20;
        filter = new FilterObj(cp5,processing);
        order = new OrderObj(cp5,processing);
        adx = new AddExpense(cp5,processing,database);
        settings = cp5.addButton("account").setLabel("Account").setPosition(processing.width-0.5f*processing.width/3+100, 0.4f*processing.height/3).setSize(80, 30).hide();
        graphic = cp5.addButton("graphic").setLabel("Graphic").setPosition(processing.width/3, 10).setSize(80, 30).setColorBackground(0).hide();
        add = cp5.addButton("add").setLabel("Add").setPosition(processing.width-0.5f*processing.width/3+100, processing.height-0.5f*processing.height/3).setSize(80, 30).hide();
    }

    /**
     * Draws the expenses page including the filtered and ordered list of expenses.
     */
    public void draw(){
        processing.background(255);
        processing.pushMatrix();
        processing.translate(0, -scrollPos);
        if(on) {
            filter.showList();
            order.showList();
            settings.show();
            add.show();
            graphic.show();
            if (listToShow != null && !listToShow.isEmpty()) ExpenseController.renderList(processing, listToShow, 50, 80);
            else {
                processing.textAlign(processing.CENTER, processing.CENTER);
                processing.text("No expense yet", processing.width / 2, processing.height / 2);
                processing.textAlign(0, 0);
            }
        }else {
            adx.showMenu();
        }
        processing.popMatrix();
        processing.fill(229, 229, 229, 255);
        processing.stroke(229, 229, 229);
        processing.rect(0, 0, processing.width, 50);
        processing.stroke(0);
    }

    /**
     * Handles mouse wheel events to scroll the content of the expenses page.
     *
     * This method adjusts the scroll position based on the amount of scrolling performed by the user.
     * The scroll position is constrained to ensure it remains within valid bounds.
     *
     * @param event the mouse event containing the scroll amount
     */
    public void mouseWheel(MouseEvent event){
        float e = event.getCount();
        scrollPos += e * scrollSpeed;
        scrollPos = processing.constrain(scrollPos, 0, contentHeight - processing.height);
    }

    /**
     * Callback method to switch the view to the add expense menu.
     *
     * This method sets the on flag to false, which triggers the display of the
     * add expense menu instead of the main expenses page.
     */
    public void addCallback() {
        on = false;
    }

    /**
     * Returns a copy of the list of ExpenseController objects to be shown.
     *
     * @return a new ArrayList containing the ExpenseController objects to be shown
     */
    public ArrayList<ExpenseController> getListToShow(){
        return new ArrayList<ExpenseController>(listToShow);
    }

    /**
     * Reorders the list of expenses based on the provided comparator.
     *
     * This method retrieves the list of expenses for the current account from the database,
     * then sorts this list using the specified comparator. If the comparator is null,
     * a NullPointerException will be thrown.
     *
     * @param c the comparator used to order the list of expenses. Must not be null
     * @throws NullPointerException if the comparator c is null
     */
    public void reorderCallback(Comparator c) throws NullPointerException{
        if(c == null) throw new NullPointerException("The comparator is null");
        listToShow = database.getExpensesByAccount(Model.getAccount());
        Collections.sort(listToShow,c);
    }

    /**
     * Changes the list of ExpenseController objects to be shown.
     *
     * @param listToShow the new list of ExpenseController objects
     */
    public void changeListToShow(ArrayList<ExpenseController> listToShow){
        this.listToShow = listToShow;
    }

    /**
     * Returns the button used to add new expenses.
     *
     * @return the button for adding new expenses
     */
    public Button getAddButton() {
        return add;
    }

    /**
     * Returns the instance of the AddExpense class used for managing the add expense menu.
     *
     * @return the AddExpense instance
     */
    public AddExpense getAdx() {
        return adx;
    }

    /**
     * Sets the flag indicating whether the main expenses page or the add expense menu is displayed.
     *
     * @param v true to show the main expenses page, false to show the add expense menu
     */
    public void setOn(boolean v) {
        on = v;
    }

    /**
     * Returns the button used to access graphic settings.
     *
     * @return the button for graphic settings
     */
    public Button getGraphic() {
        return graphic;
    }

    /**
     * Returns the button used to access account settings.
     *
     * @return the button for account settings
     */
    public Button getSettings() {
        return settings;
    }
}
