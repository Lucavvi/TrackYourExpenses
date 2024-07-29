package com.project.src.expense;

import com.project.src.Model;
import com.project.src.accountManager.DBManager;
import com.project.src.filters.FilterObj;
import com.project.src.filters.OrderObj;
import controlP5.Button;
import controlP5.ControlP5;
import processing.core.PApplet;
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
        on = true;
        filter = new FilterObj(cp5,processing);
        order = new OrderObj(cp5,processing);
        adx = new AddExpense(cp5,processing,database);
        settings = cp5.addButton("account").setLabel("Account").setPosition(processing.width-0.5f*processing.width/3, 0.4f*processing.height/3).setSize(80, 30).hide();
        graphic = cp5.addButton("graphic").setLabel("Graphic").setPosition(processing.width/3, 10).setSize(80, 30).setColorBackground(0).hide();
        add = cp5.addButton("add").setLabel("Add").setPosition(processing.width-0.5f*processing.width/3, processing.height-0.5f*processing.height/3).setSize(80, 30).hide();
    }

    /**
     * Draws the expenses page including the filtered and ordered list of expenses.
     */
    public void draw(){
        processing.background(255);
        processing.fill(229, 229, 229);
        processing.stroke(229, 229, 229);
        processing.rect(0, 0, processing.width, 50);
        processing.stroke(0);
        if(on) {
            filter.showList();
            order.showList();
            settings.show();
            add.show();
            graphic.show();
            if (!listToShow.isEmpty()) ExpenseController.renderList(processing, listToShow, 50, 80);
            else {
                processing.textAlign(processing.CENTER, processing.CENTER);
                processing.text("No expense yet", processing.width / 2, processing.height / 2);
                processing.textAlign(0, 0);
            }
        }else {
            adx.showMenu();
        }
    }

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

    public void reorderCallback(Comparator c) {
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

    public Button getAddButton() {
        return add;
    }

    public AddExpense getAdx() {
        return adx;
    }

    public void setOn(boolean v) {
        on = v;
    }

    public Button getGraphic() {
        return graphic;
    }

    public Button getSettings() {
        return settings;
    }
}
