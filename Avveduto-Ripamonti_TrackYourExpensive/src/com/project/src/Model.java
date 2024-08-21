package com.project.src;

import com.project.src.graphic.graphicPage;
import controlP5.ControlP5;
import com.project.src.accountManager.*;
import com.project.src.expense.*;
import com.project.src.form.*;
import processing.core.*;

import java.util.*;

/**
 * MVC Model class for managing the application state and interactions.
 *
 * This class initializes and maintains references to various components
 * such as the ControlP5 library for UI controls, the FormController for
 * handling form-related actions, and the DBManager for database operations.
 * It also manages the state of the screens and the list of expenses to be shown.
 *
 * @author Angelo Ripamonti & Luca Avveduto
 * @version 1.0
 */
public class Model {
    ControlP5 cp5;
    FormController form;
    ExpensesPage expense;
    DBManager dao;
    boolean[] screen;
    static Account acc;
    AccountPage account;
    graphicPage graphicPage;


    /**
     * Constructs a new Model instance.
     *
     * Initializes the ControlP5 instance, DBManager for database operations,
     * FormController for handling form actions, and ExpensesPage for managing expense interactions.
     * Sets up the initial screen state, with the first screen activated.
     *
     * @param processing the PApplet instance used for creating and managing
     * the UI controls and other visual elements. Must not be null.
     * @throws NullPointerException if processing is null.
     */
    public Model(PApplet processing) throws NullPointerException{
        if(processing == null) throw new NullPointerException("processing parameter is null");
        cp5 = new ControlP5(processing);
        dao = new DBManager();
        form=new FormController(processing,cp5,dao);
        expense = new ExpensesPage(processing,cp5,dao);
        screen = new boolean[3];
        Arrays.fill(screen,false);
        screen[0]=true;
        account = new AccountPage(processing, cp5, dao);
        graphicPage = new graphicPage(processing,cp5);
    }

    /**
     * Changes the current account to the specified account.
     *
     * @param a the new Account to be set as the current account
     */
    public static void changeAccount(Account a) {
        acc = a;
    }

    /**
     * Retrieves the current account.
     *
     * @return the current Account
     */
    public static Account getAccount() { return acc; }
}
