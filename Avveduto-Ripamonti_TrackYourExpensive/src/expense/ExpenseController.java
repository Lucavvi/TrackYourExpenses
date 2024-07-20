package expense;

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
     * @param name the name of the expense
     * @param date the date of the expense
     * @param category the category of the expense
     * @param amount the cost of the expense
     * @param desc the description of the expense
     * @param parent the main instance
     */
    public ExpenseController(String name, LocalDate date, Categories category, float amount, String desc,PApplet parent) {
        model = new ExpenseModel(name,date,category,amount,desc);
        view = new ExpenseView();
    }

    /**
     * Shows the expense at the specified position.
     *
     * @param parent the main instance
     * @param x the x-coordinate position
     * @param y the y-coordinate position
     */
    public void showExpense(PApplet parent,int x, int y) {
        view.view(parent,model,x,y);
    }

    /**
     * Renders a list of expenses starting at the specified position.
     *
     * @param parent the main instance
     * @param list the list of expenses to render
     * @param startX the starting x-coordinate
     * @param startY the starting y-coordinate
     */
    public static void renderList(PApplet parent, ArrayList<ExpenseController> list, int startX, int startY) {
        int cont = 0;
        int x = startX;
        int y = startY;
        boolean firstRow = false;
        for(ExpenseController e : list) {
            e.getView().view(parent,e.getModel(),x,y);
            boolean ex = firstRow ? ++cont<=3 : ++cont < 3;
            if(ex) {
                x += 350;
            }else {
                firstRow = true;
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
