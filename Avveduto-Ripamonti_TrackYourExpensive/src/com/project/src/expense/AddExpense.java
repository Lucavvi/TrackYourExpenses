package com.project.src.expense;

import controlP5.Button;
import controlP5.ControlP5;
import controlP5.DropdownList;
import controlP5.Textfield;
import processing.core.PApplet;

public class AddExpense {
    private ControlP5 cp5;
    private PApplet parent;
    private Textfield nameField;
    private Textfield descField;
    private DropdownList select;
    private Button done;
    private float selectPos;

    public AddExpense(ControlP5 cp5, PApplet parent) {
        this.cp5 = cp5;
        this.parent = parent;
        done = cp5.addButton("done").setLabel("Done").setPosition(parent.width-parent.width/3, parent.height/2+200).setSize(80, 30).hide();
        nameField = cp5.addTextfield("name").setColorForeground(parent.color(255)).setFont(parent.createFont("arial",25)).setPosition(parent.width/3,parent.height/2-40).setSize(200,40).setFocus(true).setColor(parent.color(255)).setColorActive(parent.color(255)).setColorBackground(0).setCaptionLabel("").hide();
        descField = cp5.addTextfield("desc").setColorForeground(parent.color(255)).setFont(parent.createFont("arial",25)).setPosition(parent.width/3,parent.height/2+40).setSize(200,40).setFocus(true).setColor(parent.color(255)).setColorActive(parent.color(255)).setColorBackground(0).setCaptionLabel("").hide();
        select = this.cp5.addDropdownList("category")
                .setLabel("Category").setBarHeight(20)
                .setItemHeight(20).setPosition(parent.width/3,parent.height/2+120)
                .setSize(200,100).close()
                .setColorBackground(0).hide();
        select.addItem("FOOD", Categories.FOOD);
        select.addItem("SHOPPING", Categories.SHOPPING);
        select.addItem("PLEASURE", Categories.PLEASURE);
        selectPos = select.getHeight();
    }

    public void showMenu() {
        parent.background(255);
        parent.rectMode(3);
        parent.fill(255);
        parent.rect(parent.width/2,parent.height/2,parent.width/3*2,parent.height/3*2);
        parent.textAlign(3,3);
        parent.fill(0);
        parent.textSize(70);
        parent.text("Insert your expense",parent.width/2,parent.height/3);
        parent.textAlign(0,0);
        parent.textSize(20);
        parent.text("Name",nameField.getPosition()[0],nameField.getPosition()[1]-(nameField.getHeight()/2));
        nameField.show();
        parent.text("Description (Max 50 characters):",descField.getPosition()[0],descField.getPosition()[1]-(descField.getHeight()/2));
        descField.show();
        done.show();
        parent.text("Expense category",select.getPosition()[0],select.getPosition()[1]-(selectPos/2));
        select.show();
        parent.rectMode(0);
    }

    public void hideMenu() {
        nameField.hide();
        descField.hide();
        select.hide();
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
}
