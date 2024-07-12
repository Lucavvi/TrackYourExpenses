package expense;
import processing.core.PApplet;

public class ExpenseView {
    private PApplet parent;

    public ExpenseView(PApplet parent) {
        this.parent = parent;
    }
    public void view(ExpenseModel model, int x, int y) {
        parent.fill(model.getCategory().getColor().getRGB());
        parent.rect(x,y,x + 200, y + 200);
    }
}
