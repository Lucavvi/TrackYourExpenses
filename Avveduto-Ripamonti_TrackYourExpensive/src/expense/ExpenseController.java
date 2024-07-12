package expense;

import processing.core.PApplet;

import java.time.LocalDate;

public class ExpenseController {
    private ExpenseModel model;
    private ExpenseView view;

    public ExpenseController(String name, LocalDate date, Categories category, float amount, String desc,PApplet parent) {
        model = new ExpenseModel(name,date,category,amount,desc);
        view = new ExpenseView(parent);
    }

    public void showExpense(int x, int y) {
        view.view(model,x,y);
    }
}
