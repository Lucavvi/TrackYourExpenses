package expense;
import processing.core.PApplet;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.Locale;

public class ExpenseView {
    private PApplet parent;

    public ExpenseView(PApplet parent) {
        this.parent = parent;
    }
    public void view(ExpenseModel model, int x, int y) {
        parent.fill(model.getCategory().getColor().getRGB());
        parent.rect(x,y,300, 100);
        parent.fill(0);
        parent.textSize(14);
        parent.text(model.getName(),x + 10,y + 20);
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = model.getDate().format(f);
        parent.text(date,x + 10,y + 40);
        Locale l = Locale.getDefault();
        parent.text(Currency.getInstance(l).getSymbol() + model.getAmount(),x + 10,y + 60);
        parent.text(model.getCategory().getName(),x + 10,y + 80);
    }
}
