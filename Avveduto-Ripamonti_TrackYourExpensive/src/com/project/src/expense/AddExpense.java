package com.project.src.expense;

import com.project.src.Model;
import com.project.src.accountManager.Actions;
import com.project.src.accountManager.DBManager;
import controlP5.*;
import processing.core.PApplet;

import java.util.ArrayList;

/**
 * The AddExpense class handles the UI and logic for adding a new expense in the application.
 *
 * @author Angelo Ripamonti & Luca Avveduto
 * @version 1.0
 */
public class AddExpense {
    private ControlP5 cp5;
    private PApplet parent;
    private Textfield nameField;
    private Textfield descField;
    private Textfield amountField;
    private DropdownList select;
    private Button done;
    private float selectPos;
    private int selectStatus;
    private Button exit;
    private DBManager dbManager;
    private boolean check;

    /**
     * Constructor for AddExpense.
     *
     * @param cp5   The ControlP5 instance used for creating UI elements. Must not be null.
     * @param parent The PApplet instance used as the parent. Must not be null.
     * @param db    The DBManager instance used for database operations. Must not be null.
     * @throws NullPointerException if any of the parameters are null.
     */
    public AddExpense(ControlP5 cp5, PApplet parent, DBManager db) throws NullPointerException {
        if(cp5 == null || parent == null || db == null) throw new NullPointerException("Almost one parameter passed is null");
        this.cp5 = cp5;
        this.parent = parent;
        dbManager = db;
        exit = cp5.addButton("exit").setLabel("Exit").setPosition(parent.width/3, parent.height/2+250).setSize(80, 30).hide();
        done = cp5.addButton("done").setLabel("Done").setPosition(parent.width-parent.width/3, parent.height/2+250).setSize(80, 30).hide();
        nameField = cp5.addTextfield("name").setColorForeground(parent.color(255)).setFont(parent.createFont("arial",25)).setPosition(parent.width/3,parent.height/2-80).setSize(200,40).setFocus(true).setColor(parent.color(255)).setColorActive(parent.color(255)).setColorBackground(0).setCaptionLabel("").hide();
        descField = cp5.addTextfield("desc").setColorForeground(parent.color(255)).setFont(parent.createFont("arial",25)).setPosition(parent.width/3,parent.height/2).setSize(200,40).setFocus(true).setColor(parent.color(255)).setColorActive(parent.color(255)).setColorBackground(0).setCaptionLabel("").hide();
        amountField = cp5.addTextfield("amount").setColorForeground(parent.color(255)).setFont(parent.createFont("arial",25)).setPosition(parent.width/3,parent.height/2+80).setSize(200,40).setFocus(true).setColor(parent.color(255)).setColorActive(parent.color(255)).setColorBackground(0).setCaptionLabel("").hide();
        select = this.cp5.addDropdownList("category")
                .setBarHeight(20)
                .setItemHeight(20).setPosition(parent.width/3,parent.height/2+160)
                .setSize(200,100).close()
                .setColorBackground(0).hide();
        select.addItem("CATEGORY",-1);
        select.addItem("FOOD",0);
        select.addItem("SHOPPING", 1);
        select.addItem("PLEASURE", 2);
        selectPos = select.getHeight();
        check = false;
    }

    /**
     * Displays the expense input menu on the screen.
     */
    public void showMenu() {
        exit.show();
        parent.rectMode(3);
        parent.fill(255);
        parent.rect(parent.width/2,parent.height/2,parent.width/3*2,parent.height/3*2);
        parent.textAlign(3,3);
        parent.fill(0);
        parent.textSize(70);
        parent.text("Insert your expense",parent.width/2,parent.height/4);
        parent.textAlign(0,0);
        parent.textSize(20);
        parent.text("Name",nameField.getPosition()[0],nameField.getPosition()[1]-(nameField.getHeight()/2));
        nameField.show();
        parent.text("Description (Max 50 characters):",descField.getPosition()[0],descField.getPosition()[1]-(descField.getHeight()/2));
        descField.show();
        parent.text("Amount:",amountField.getPosition()[0],amountField.getPosition()[1]-(descField.getHeight()/2));
        amountField.show();
        done.show();
        parent.text("Expense category",select.getPosition()[0],select.getPosition()[1]-(selectPos/2));
        select.show();
        parent.rectMode(0);
        if(check) {
            parent.fill(255,0,0);
            parent.textAlign(parent.CENTER,parent.CENTER);
            parent.text("Error check text fields values",parent.width/2,exit.getPosition()[1] + exit.getHeight()/2);
            parent.textAlign(parent.BASELINE,parent.BASELINE);
        }
    }

    /**
     * Hides the expense input menu from the screen.
     */
    public void hideMenu() {
        parent.background(255);
        nameField.hide();
        descField.hide();
        select.hide();
        done.hide();
        exit.hide();
        amountField.hide();
    }

    /**
     * Handles the logic for the done button callback, including validation and adding the expense to the model.
     *
     * @param m    The Model instance. Must not be null
     * @param list The list of ExpenseController instances. Must not be null.
     * @return True if the expense was successfully added, false otherwise.
     * @throws NullPointerException if any of the parameters are null.
     */
    public boolean doneCallback(Model m, ArrayList<ExpenseController> list) throws NullPointerException{
        if(m == null || list == null) throw new NullPointerException("Almost one parameter passed are null");
        done.show();
        hideMenu();
        boolean check = false;
        String name = nameField.getText() == null ? "" : nameField.getText();
        String desc = descField.getText() == null ? "" : descField.getText();
        float amount = 0;
        if(!name.isEmpty() && name.length() < 20) {
            if(!desc.isEmpty() && desc.length() < (14*4)) {
                if (selectStatus != 0) {
                    try {
                        amount = Float.parseFloat(amountField.getText());
                        check = true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        nameField.clear();
        descField.clear();
        amountField.clear();
        if(check) {
            select.setLabel("Category");
            ExpenseController item = new ExpenseController(name, new LocalDate(java.time.LocalDate.now()), Categories.values()[selectStatus - 1], amount, desc);
            Model.getAccount().addExpense(item);
            dbManager.updateList(Model.getAccount());
            this.check = false;
            return true;
        }
        else {
            this.check = true;
            return false;
        }
    }

    /**
     * Callback for handling category selection.
     *
     * @param event The ControlEvent instance.
     */
    public void categoryCallback(ControlEvent event) {
        selectStatus = (int) event.getController().getValue();
    }

    /**
     * Gets the category dropdown list.
     *
     * @return The category DropdownList instance.
     */
    public DropdownList getSelect() {
        return select;
    }

    /**
     * Gets the done button.
     *
     * @return The done Button instance.
     */
    public Button getDone() {
        return done;
    }

    /**
     * Gets the exit button.
     *
     * @return The exit Button instance.
     */
    public Button getExit() {
        return exit;
    }
}
