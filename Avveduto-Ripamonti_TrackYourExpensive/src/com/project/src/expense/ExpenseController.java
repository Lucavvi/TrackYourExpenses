package com.project.src.expense;

import processing.core.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * MVC class for managing an expense.
 * This class acts as the controller in the MVC pattern, managing the ExpenseModel and ExpenseView.
 * It provides methods to show an individual expense and render a list of expenses.
 *
 * @author Angelo Ripamonti & Luca Avveduto
 * @version 1.0
 */
public class ExpenseController implements Serializable {
    private ExpenseModel model;
    private ExpenseView view;

    /**
     * Constructs an ExpenseController with the specified details.
     *
     * @param name the name of the expense. Must not be null or blank. The character must not be more than 10.
     * @param date the date of the expense. Must not be null. Must not be in the future.
     * @param category the category of the expense. Must not be null.
     * @param amount the cost of the expense. Must not be NaN or Infinite.
     * @param desc the description of the expense. Must not be null or blank. The character must not be more than 56.
     * @throws RuntimeException if the ExcpenseModel throw any exception.
     */
    public ExpenseController(String name, LocalDate date, Categories category, float amount, String desc) throws RuntimeException {
        model = new ExpenseModel(name,date,category,amount,desc);
        view = new ExpenseView();
    }

    /**
     * Shows the expense at the specified position.
     *
     * @param parent the main instance. Must not be null
     * @param x the x-coordinate position. Must not be less than 0 or more than parent.width
     * @param y the y-coordinate position. Must not be less than 0 or more than parent.height
     * @throws NullPointerException if parent is null
     * @throws IllegalArgumentException If x or y are less than 0. Otherwise, if x are more than parent.width or y are more than parent.height.
     */
    public void showExpense(PApplet parent,int x, int y) throws NullPointerException, IllegalArgumentException {
        if(parent == null) throw new NullPointerException("parent parameter is null");
        if(x < 0 || y < 0 || x > parent.width || y > parent.height) throw new IllegalArgumentException("x or y are less than 0. Otherwise, x are more than parent.width or y are more than parent.height");
        view.view(parent,model,x,y);
    }

    /**
     * Renders a list of expenses starting at the specified position.
     *
     * @param parent the main instance. Must not be null
     * @param list the list of expenses to render. Must not be null
     * @param startX the starting x-coordinate. Must not be less than 0 or more than parent.width
     * @param startY the starting y-coordinate. Must not be less than 0 or more than parent.height
     * @throws NullPointerException if parent or list are null
     * @throws IllegalArgumentException If x or y are less than 0. Otherwise, if x are more than parent.width or y are more than parent.height.
     */
    public static void renderList(PApplet parent, ArrayList<ExpenseController> list, int startX, int startY)throws NullPointerException, IllegalArgumentException{
        if(parent == null || list == null) throw new NullPointerException("parent or list parameter are null");
        if(startX < 0 || startY < 0 || startX > parent.width || startY > parent.height) throw new IllegalArgumentException("x or y are less than 0. Otherwise, x are more than parent.width or y are more than parent.height");
        int cont = 0;
        int x = startX;
        int y = startY;
        for(ExpenseController e : list) {
            e.getView().view(parent,e.getModel(),x,y);
            if(++cont<=3) {
                x += 350;
            }else {
                x = startX;
                y += 200;
                cont = 0;
            }
        }
    }

    /**
     * Gets the model object.
     *
     * @return the model object
     */
    public ExpenseModel getModel() {
        return model;
    }

    /**
     * Gets the view object.
     *
     * @return the view object
     */
    public ExpenseView getView() {
        return view;
    }
}
