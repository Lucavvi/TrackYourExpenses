package expense;

public class ExpenseController {
    private ExpenseModel model;
    private ExpenseView view;

    public ExpenseController(ExpenseModel model, ExpenseView view) {
        this.model = model;
        this.view = view;
    }

    public void showExpense(int x, int y) {
        view.view(model,x,y);
    }
}
