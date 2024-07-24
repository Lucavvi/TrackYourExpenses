package com.project.src;

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

    /** The ControlP5 instance for UI control management. */
    ControlP5 cp5;

    /** The FormController instance for managing com.project.src.form interactions. */
    FormController form;

    /** The ExpensesPage instance for managing expense-related interactions. */
    ExpensesPage expense;

    /** The DBManager instance for database interactions. */
    DBManager dao;

    /** The current category filter applied to the expenses. */
    Categories target;

    /** Array of boolean values representing different screen states. */
    boolean[] screen;

    /** The current Account instance representing the logged-in user. */
    static Account acc;

    AccountPage account;


    /**
     * Constructs a new Model instance.
     *
     * Initializes the ControlP5 instance, DBManager for database operations,
     * FormController for handling form actions, and ExpensesPage for managing expense interactions.
     * Sets up the initial screen state, with the first screen activated.
     *
     * @param processing the {@link PApplet} instance used for creating and managing
     *                   the UI controls and other visual elements.
     */
    public Model(PApplet processing) {
        cp5 = new ControlP5(processing);
        dao = new DBManager();
        form=new FormController(processing,cp5,dao);
        expense = new ExpensesPage(processing,cp5,dao);
        screen = new boolean [10];
        Arrays.fill(screen,false);
        screen[0]=true;
        account = new AccountPage(processing, cp5, dao);
    }

    /**
     * Changes the current account to the specified account.
     *
     * @param a the new {@link Account} to be set as the current account
     */
    public static void changeAccount(Account a) {
        acc = a;
    }

    /**
     * Retrieves the current account.
     *
     * @return the current {@link Account}
     */
    public static Account getAccount() { return acc; }
}
