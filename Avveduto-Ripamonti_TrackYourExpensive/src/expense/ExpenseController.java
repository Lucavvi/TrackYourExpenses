package expense;

import processing.core.PApplet;

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
        view = new ExpenseView(parent);
    }

    /**
     * Method for showing the expense
     * @param x - position on x axis
     * @param y - position on y axis
     */
    public void showExpense(PApplet parent,int x, int y) {
        view.view(parent,model,x,y);
    }

    public void renderList(PApplet parent,ArrayList<ExpenseController> list, int startX, int startY) {
        int cont = 0;
        int x = startX;
        int y = startY;
        for(ExpenseController e : list) {
            e.showExpense(parent,x,y);
            if(++cont <= 3) {
                x += 350;
            }else {
                x = startX;
                y += 200;
                cont = 0;
            }
        }
    }

    public ExpenseModel getModel() {
        return model;
    }

    public ExpenseView getView() {
        return view;
    }
}
