package com.project.src.accountManager;

import com.google.gson.Gson;
import com.project.src.Model;
import com.project.src.expense.Color;
import com.project.src.expense.ExpensesPage;
import controlP5.Button;
import controlP5.ControlP5;
import controlP5.Textfield;
import processing.core.PApplet;

import java.io.*;
import java.nio.file.*;


/**
 * Manages the account page for the application.
 *
 * This class is responsible for displaying and handling interactions
 * on the account management page, including showing/hiding the password,
 * deleting the account, and deleting the list of expenses.
 *
 * @author Angelo Ripamonti & Luca Avveduto
 * @version 1.0
 */
public class AccountPage {
    private PApplet processing;
    private ControlP5 cp5;
    private DBManager database;
    private Textfield username;
    private Textfield password;
    private Button showPsw;
    private Button deleteAccount;
    private Button deleteList;
    private Button backExpenses;
    private Button logout;
    private Button export;
    private int passwordButtonController;
    private boolean dirCheck;
    private boolean writerCheck;
    private boolean successCheck;

    /**
     * Initializes the account page with the given Processing, ControlP5,
     * and database manager instances.
     *
     * @param processing the Processing applet instance. Must not be null.
     * @param cp5        the ControlP5 instance for handling GUI controls. Must not be null.
     * @param database   the database manager instance for interacting with the database. Must not be null.
     * @throws NullPointerException if any of the parameters are null.
     */
    public AccountPage(PApplet processing, ControlP5 cp5, DBManager database) throws NullPointerException{
        if(processing == null || cp5 == null || database == null) throw new NullPointerException("Almost one parameter passed is null");
        this.processing = processing;
        this.cp5 = cp5;
        this.database = database;
        passwordButtonController = 0;
        username = cp5.addTextfield("accountUsername").setPosition(processing.width / 2 - 100, processing.height / 2 - 80).setSize(200, 40).setColorForeground(processing.color(255)).setFont(processing.createFont("arial", 20)).setColor(processing.color(255)).setColorActive(processing.color(255)).setColorBackground(0).setCaptionLabel("").setAutoClear(false).setLock(true).hide();
        password = cp5.addTextfield("accountPassword").setPosition(username.getPosition()[0], username.getPosition()[1] + username.getHeight() + 10).setSize(200, 40).setFont(processing.createFont("arial", 20)).setColor(processing.color(255)).setColorActive(processing.color(255)).setColorBackground(0).setCaptionLabel("").setAutoClear(false).setLock(true).setPasswordMode(true).hide();
        showPsw = cp5.addButton("accountShowPassword").setLabel("Show Password").setPosition(password.getPosition()[0] + password.getWidth() + 10, password.getPosition()[1]).setSize(100, 40).setColorBackground(processing.color(0, 0, 255)).hide();
        deleteAccount = cp5.addButton("deleteAccount").setLabel("Delete Account").setPosition(password.getPosition()[0], password.getPosition()[1] + password.getHeight() + 10).setSize(90, 40).setColorBackground(processing.color(255, 0, 0)).hide();
        deleteList = cp5.addButton("deleteList").setLabel("Delete List").setPosition(deleteAccount.getPosition()[0] + deleteAccount.getWidth() + 20, deleteAccount.getPosition()[1]).setSize(90, 40).setColorBackground(processing.color(255, 0, 0)).hide();
        backExpenses = cp5.addButton("backExpenses").setLabel("Turn Back").setPosition(deleteAccount.getPosition()[0], deleteAccount.getPosition()[1] + deleteAccount.getHeight() + 10).setSize(200, 40).setColorBackground(processing.color(0)).setColorLabel(255).hide();
        logout = cp5.addButton("logout").setLabel("Log Out").setPosition(backExpenses.getPosition()[0], backExpenses.getPosition()[1] + backExpenses.getHeight() + 10).setSize(200, 40).setColorBackground(processing.color(0)).setColorLabel(255).hide();
        export = cp5.addButton("exportData").setLabel("Export Data").setPosition(logout.getPosition()[0], logout.getPosition()[1] + logout.getHeight() + 10).setSize(200, 40).setColorBackground(processing.color(0)).setColorLabel(255).hide();
        dirCheck = false;
        writerCheck = false;
        successCheck= false;
    }

    /**
     * Renders the account page with the given account details.
     *
     * @param account the account whose details are to be displayed. Must not be null.
     * @throws NullPointerException if account is null
     */
    public void draw(Account account)throws NullPointerException {
        if(account == null) throw new NullPointerException("account is null");
        processing.background(processing.color(255));
        processing.fill(229, 229, 229);
        processing.rectMode(processing.CENTER);
        processing.rect(processing.width / 2, processing.height / 2, 500, 500, 20);
        processing.rectMode(processing.CORNER);
        processing.textSize(30);
        processing.fill(0, 0, 255);
        processing.text("Dati Account", username.getPosition()[0], username.getPosition()[1] - (username.getHeight() / 2));
        processing.textSize(20);
        username.setText(account.getUsername());
        password.setText(account.getPassword());
        username.show();
        password.show();
        showPsw.show();
        deleteAccount.show();
        deleteList.show();
        backExpenses.show();
        logout.show();
        export.show();
        if(dirCheck) showMessage("Creazione della cartella fallita!", new Color(java.awt.Color.RED));
        if(writerCheck) showMessage("Esportazione dati fallita!", new Color(java.awt.Color.RED));
        if(successCheck) showMessage("Dati esportati con successo!", new Color(java.awt.Color.GREEN));
    }

    /**
     * Hides all the fields on the account page.
     */
    public void hideFields() {
        username.hide();
        password.hide();
        showPsw.hide();
        deleteAccount.hide();
        deleteList.hide();
        backExpenses.hide();
        logout.hide();
        export.hide();
        dirCheck = false;
        writerCheck = false;
        successCheck= false;
    }

    /**
     * Toggles the visibility of the password field.
     *
     * This method alternates between showing and hiding the password.
     */
    public void showPassword() {
        if (passwordButtonController % 2 == 0) {
            password.setPasswordMode(false);
            showPsw.setLabel("Hide Password");
        } else {
            password.setPasswordMode(true);
            showPsw.setLabel("Show Password");
        }
        passwordButtonController++;
    }

    /**
     * Deletes the account from the database.
     *
     * This method deletes the current account from the database.
     */
    public void deleteAccount() {
        database.deleteAccount(database.login(username.getText(), password.getText()));
    }

    /**
     * Deletes the list of expenses for the current account.
     *
     * This method resets the expenses for the current account and updates the database.
     */
    public void deleteList() {
        Account acc = Model.getAccount();
        acc.resetExpenses();
        database.updateList(acc);
    }

    /**
     * Exports the current account data to a JSON file.
     *
     * This method creates a directory if it does not exist and writes the account data to a JSON file.
     */
    public void exportData() {
        dirCheck = false;
        writerCheck = false;
        successCheck= false;
        Account data = database.login(username.getText(), password.getText());
        Gson gson = new Gson();
        String json = gson.toJson(data);
        Path directoryPath = Paths.get("data");
        try {
            if (Files.notExists(directoryPath))
                Files.createDirectory(directoryPath);
        } catch (Exception e) {
            dirCheck = true;
            return;
        }
        String pathName = "data\\data" + username.getText() + ".json";
        Path dataPath = Paths.get(pathName);
        File dataFile = dataPath.toFile();

        try (FileWriter f = new FileWriter(dataFile);
             PrintWriter fOUT = new PrintWriter(f)) {
            fOUT.println(json);
            fOUT.flush();
        } catch (Exception e) {
            writerCheck = true;
            return;
        }
        successCheck = true;
    }

    /**
     * Displays a message on the screen.
     *
     * @param msg the message to display. Must not be null or blank.
     * @param c   the color of the message. Must not be null.
     * @throws NullPointerException if any of the parameters are null
     * @throws IllegalArgumentException if msg is blank
     */
    private void showMessage(String msg, Color c) throws NullPointerException, IllegalArgumentException{
        if(msg == null || c == null) throw new NullPointerException("Almost one parameter passed is null");
        if(msg.isBlank()) throw new IllegalArgumentException("msg parameter is blank");
        processing.fill(c.getRGB());
        processing.textSize(30);
        processing.textAlign(processing.CENTER,processing.CENTER);
        processing.text(msg,processing.width/2, username.getPosition()[1] - (username.getHeight() / 2) - 50);
        processing.textAlign(processing.BASELINE,processing.BASELINE);
    }
}
