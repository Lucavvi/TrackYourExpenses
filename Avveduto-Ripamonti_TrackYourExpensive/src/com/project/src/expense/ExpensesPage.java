package com.project.src.expense;

import com.project.src.Model;
import com.project.src.accountManager.DBManager;
import com.project.src.filters.FilterObj;
import com.project.src.filters.OrderObj;
import controlP5.Button;
import controlP5.ControlP5;
import processing.core.PApplet;
import java.util.ArrayList;

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

    /**
     * Constructs an ExpensesPage with the specified processing, ControlP5 instance, and database manager.
     *
     * @param processing the PApplet instance for Processing library functions
     * @param cp5 the ControlP5 instance for GUI elements
     * @param database the DBManager instance for database operations
     */
    public ExpensesPage(PApplet processing, ControlP5 cp5, DBManager database) {
        this.processing = processing;
        this.cp5 = cp5;
        this.database = database;
        this.listToShow = null;
        filter = new FilterObj(cp5,processing);
        order = new OrderObj(cp5,processing);
        adx = new AddExpense(cp5,processing);
        settings = cp5.addButton("account").setLabel("Account").setPosition(processing.width-0.5f*processing.width/3, 0.3f*processing.height/3).setSize(80, 30).hide();
        add = cp5.addButton("add").setLabel("Add").setPosition(processing.width-0.5f*processing.width/3, processing.height-0.5f*processing.height/3).setSize(80, 30).hide();
    }

    /**
     * Draws the expenses page including the filtered and ordered list of expenses.
     */
    public void draw(){
        processing.background(255);
        listToShow = database.getExpensesByAccount(Model.getAccount());
        filter.showList();
        order.showList();
        settings.show();
        add.show();
        processing.fill(229,229,229);
        processing.stroke(229,229,229);
        processing.rect(0,0,processing.width,50);
        processing.stroke(0);
        if(!listToShow.isEmpty()) ExpenseController.renderList(processing,listToShow,50,80);
        else {
            processing.textAlign(processing.CENTER,processing.CENTER);
            processing.text("No expense yet",processing.width/2,processing.height/2);
            processing.textAlign(0,0);
        }
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
     * Changes the list of ExpenseController objects to be shown.
     *
     * @param listToShow the new list of ExpenseController objects
     */
    public void changeListToShow(ArrayList<ExpenseController> listToShow){
        this.listToShow = listToShow;
    }

    public Button getAddButton() {
        return add;
    }

    public AddExpense getAdx() {
        return adx;
    }

    public Button getSettings() {
        return settings;
    }
}
