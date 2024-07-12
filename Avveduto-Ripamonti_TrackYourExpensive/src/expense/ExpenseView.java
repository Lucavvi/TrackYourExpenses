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
        parent.strokeWeight(4);
        parent.fill(model.getCategory().getColor().getRGB(),180);
        parent.rect(x,y,300, 100);
        parent.fill(0);
        parent.textFont(parent.createFont("arial",16));
        parent.text("\uD835\uDC0D\uD835\uDC1A\uD835\uDC26\uD835\uDC1E: " + model.getName(),x + 10,y + 20);
        parent.strokeWeight(1);
        parent.line(x,y+25,x + 150,y+25);
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = model.getDate().format(f);
        parent.text("\uD835\uDC03\uD835\uDC1A\uD835\uDC2D\uD835\uDC1E: " + date,x + 10,y + 40);
        parent.line(x,y+45,x + 150,y+45);
        Locale l = Locale.getDefault();
        parent.text("\uD835\uDC00\uD835\uDC26\uD835\uDC28\uD835\uDC2E\uD835\uDC27\uD835\uDC2D: " + Currency.getInstance(l).getSymbol() + model.getAmount(),x + 10,y + 60);
        parent.line(x,y+65,x + 150,y+65);
        parent.text("\uD835\uDC02\uD835\uDC1A\uD835\uDC2D\uD835\uDC1E\uD835\uDC20\uD835\uDC28\uD835\uDC2B\uD835\uDC32: " + model.getCategory().getName(),x + 10,y + 80);
        parent.strokeWeight(2);
        parent.line(x+ 150,y,x + 150,y+100);
        parent.text(model.getDesc(),x + 170,y + 20);
    }
}
