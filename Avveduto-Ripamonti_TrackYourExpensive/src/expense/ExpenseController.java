package expense;

import processing.core.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * MVC class expense
 * @author luke & Angelo
 * @version 1.0
 */
public class ExpenseController implements Serializable {
    private ExpenseModel model;
    private ExpenseView view;

    /**
     * @param name - name of the expense
     * @param date - date of the expense
     * @param category - category of the expense
     * @param amount - cost of the expese
     * @param desc - description
     * @param parent - main instance
     */
    public ExpenseController(String name, LocalDate date, Categories category, float amount, String desc,PApplet parent) {
        model = new ExpenseModel(name,date,category,amount,desc);
        view = new ExpenseView();
    }

    /**
     * Method for showing the expense
     * @param x - position on x axis
     * @param y - position on y axis
     */
    public void showExpense(PApplet parent,int x, int y) {
        view.view(parent,model,x,y);
    }

    /**
     * Method to show all the list expenses
     * @param parent - main instance
     * @param list - list to render
     * @param startX - x coordinate start
     * @param startY - y coordinate start
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
     * @return the model obj
     */
    public ExpenseModel getModel() {
        return model;
    }

    /**
     * @return the view obj
     */
    public ExpenseView getView() {
        return view;
    }
}
