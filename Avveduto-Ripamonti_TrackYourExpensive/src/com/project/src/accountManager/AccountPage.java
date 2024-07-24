package com.project.src.accountManager;

import controlP5.Button;
import controlP5.ControlP5;
import controlP5.Textfield;
import processing.core.PApplet;

public class AccountPage {
    private PApplet processing;
    private ControlP5 cp5;
    private DBManager database;
    private Textfield username;
    private Textfield password;
    private Button showPsw;
    private Button delete;
    private int passwordButtonController;

    public AccountPage(PApplet processing, ControlP5 cp5, DBManager database) {
        this.processing = processing;
        this.cp5 = cp5;
        this.database = database;
        passwordButtonController=0;
        username = cp5.addTextfield("accountUsername")
                .setPosition(20, 20)
                .setSize(200, 40)
                .setFont(processing.createFont("arial", 20))
                .setColor(processing.color(255))
                .setAutoClear(false)
                .setLock(true)
                .hide();
        password = cp5.addTextfield("accountPassword")
                .setPosition(20, 100)
                .setSize(200, 40)
                .setFont(processing.createFont("arial", 20))
                .setColor(processing.color(255))
                .setAutoClear(false)
                .setLock(true)
                .setPasswordMode(true)
                .hide();
        showPsw = cp5.addButton("accountShowPassword")
                .setLabel("Show Password")
                .setPosition(20, 150)
                .setSize(200, 40)
                .setColorBackground(processing.color(255))
                .hide();
        delete = cp5.addButton("deleteAccount")
                .setPosition(20, 200)
                .setSize(200, 40)
                .setColorBackground(processing.color(255,0,0))
                .hide();
    }

    public void draw(Account account){
        processing.background(processing.color(255));
        username.setText(account.getUsername());
        password.setText(account.getPassword());
        username.show();
        password.show();
        showPsw.show();
        delete.show();
    }

    public void showPassword(){
        if(passwordButtonController % 2 == 0) {
            password.setPasswordMode(false);
            showPsw.setLabel("Hide Password");
        }
        else{
            password.setPasswordMode(true);
            showPsw.setLabel("Show Password");
        }
        passwordButtonController++;
    }
    public void deleteAccount(){
        database.deleteAccount(database.login(username.getText(),password.getText()));
    }
}
