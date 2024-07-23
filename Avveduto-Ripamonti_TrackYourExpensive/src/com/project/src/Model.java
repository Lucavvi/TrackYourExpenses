package com.project.src;

import controlP5.ControlP5;
import com.project.src.dao.Account;
import com.project.src.dao.DBManager;
import com.project.src.expense.Categories;
import com.project.src.expense.*;
import com.project.src.filters.FilterObj;
import com.project.src.filters.OrderObj;
import com.project.src.form.FormController;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * MVC com.project.src.Model class for managing the application state and interactions.
 *
 * This class initializes and maintains references to various components
 * such as the ControlP5 library for UI controls, the FormController for
 * handling com.project.src.form-related actions, and the DBManager for database operations.
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

    /** List of {@link ExpenseController} instances to display. */
    ArrayList<ExpenseController> listToShow;

    /** The FilterObj instance for managing filtering options. */
    FilterObj v;

    /** The DBManager instance for database interactions. */
    DBManager dao;

    /** The current category filter applied to the expenses. */
    Categories target;

    /** Array of boolean values representing different screen states. */
    boolean[] screen;
    OrderObj reorder;
    static Account acc;

    /**
     * Constructs a new com.project.src.Model instance.
     *
     * Initializes the ControlP5 instance, FilterObj for managing filter options,
     * DBManager for database operations, and FormController for handling com.project.src.form actions.
     * Sets up the initial screen state, with the first screen activated.
     *
     * @param processing the {@link PApplet} instance used for creating and managing
     *                   the UI controls and other visual elements.
     */
    public Model(PApplet processing) {
        cp5 = new ControlP5(processing);
        v = new FilterObj(cp5,processing);
        dao = new DBManager();
        form=new FormController(processing,cp5,dao);
        screen = new boolean [10];
        Arrays.fill(screen,false);
        screen[0]=true;
        reorder = new OrderObj(cp5,processing);
    }

    public static void changeAccount(Account a) {
        acc = a;
    }
}
