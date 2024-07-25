package com.project.src.expense;

import com.project.src.Model;
import com.project.src.accountManager.Actions;
import controlP5.*;
import processing.core.PApplet;

import java.util.ArrayList;

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
    private Actions dbManager;

    public AddExpense(ControlP5 cp5, PApplet parent, Actions db) {
        this.cp5 = cp5;
        this.parent = parent;
        dbManager = db;
        exit = cp5.addButton("exit").setLabel("Exit").setPosition(parent.width/3, parent.height/2+250).setSize(80, 30).hide();
        done = cp5.addButton("done").setLabel("Done").setPosition(parent.width-parent.width/3, parent.height/2+250).setSize(80, 30).hide();
        nameField = cp5.addTextfield("name").setColorForeground(parent.color(255)).setFont(parent.createFont("arial",25)).setPosition(parent.width/3,parent.height/2-80).setSize(200,40).setFocus(true).setColor(parent.color(255)).setColorActive(parent.color(255)).setColorBackground(0).setCaptionLabel("").hide();
        descField = cp5.addTextfield("desc").setColorForeground(parent.color(255)).setFont(parent.createFont("arial",25)).setPosition(parent.width/3,parent.height/2).setSize(200,40).setFocus(true).setColor(parent.color(255)).setColorActive(parent.color(255)).setColorBackground(0).setCaptionLabel("").hide();
        amountField = cp5.addTextfield("amount").setColorForeground(parent.color(255)).setFont(parent.createFont("arial",25)).setPosition(parent.width/3,parent.height/2+80).setSize(200,40).setFocus(true).setColor(parent.color(255)).setColorActive(parent.color(255)).setColorBackground(0).setCaptionLabel("").hide();
        select = this.cp5.addDropdownList("category")
                .setLabel("Category").setBarHeight(20)
                .setItemHeight(20).setPosition(parent.width/3,parent.height/2+160)
                .setSize(200,100).close()
                .setColorBackground(0).hide();
        select.addItem("FOOD", Categories.FOOD);
        select.addItem("SHOPPING", Categories.SHOPPING);
        select.addItem("PLEASURE", Categories.PLEASURE);
        selectPos = select.getHeight();
    }

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
    }

    public void hideMenu() {
        parent.background(255);
        nameField.hide();
        descField.hide();
        select.hide();
        done.hide();
        exit.hide();
        amountField.hide();
    }

    public void doneCallback(Model m, ArrayList<ExpenseController> list) {
        done.show();
        hideMenu();
        boolean check = true;
        String name = nameField.getText();
        String desc = descField.getText();
        float amount = 0;
        if(!name.isEmpty() && name.length() < 20) {
            if(!desc.isEmpty() && desc.length() < (14*4)) {
                try {
                     amount = Float.parseFloat(amountField.getText());
                }catch (Exception e) {
                    check = false;
                }
            }
        }
        if(check) {
            select.setLabel("Category");
            System.out.println(name + desc + selectStatus);
            ExpenseController item = new ExpenseController(name, new LocalDate(java.time.LocalDate.now()), Categories.values()[selectStatus], amount, desc, parent);
            Model.getAccount().addExpense(item);
            dbManager.updateList(Model.getAccount());
        }else System.out.println("sbagliato");
        nameField.clear();
        descField.clear();
        amountField.clear();
    }

    public void categoryCallback(ControlEvent event) {
        selectStatus = (int) event.getController().getValue();
    }

    public Textfield getNameField() {
        return nameField;
    }

    public Textfield getDescField() {
        return descField;
    }

    public DropdownList getSelect() {
        return select;
    }

    public Button getDone() {
        return done;
    }

    public Button getExit() {
        return exit;
    }
}
