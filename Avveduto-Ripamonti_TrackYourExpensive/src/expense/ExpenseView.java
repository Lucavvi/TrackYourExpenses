package expense;
import processing.core.PApplet;
import java.io.Serializable;
import java.util.Currency;
import java.util.Locale;

/**
 * MVC class for the expense view.
 * This class is responsible for rendering the visual representation of an expense using the Processing library.
 *
 * @author Angelo Ripamonti & Luca Avveduto
 * @version 1.0
 */
public class ExpenseView implements Serializable {

    /**
     * Renders the expense details on the screen.
     *
     * @param parent the main instance of PApplet used for drawing
     * @param model the ExpenseModel containing the data to display
     * @param x the x-coordinate position where the expense will be displayed
     * @param y the y-coordinate position where the expense will be displayed
     */
    public void view(PApplet parent,ExpenseModel model, int x, int y) {
        parent.strokeWeight(4);
        parent.fill(model.getCategory().getColor().getRGB(),180);
        parent.rect(x,y,300, 100);
        parent.fill(0);
        parent.textFont(parent.createFont("arial",16));
        parent.text("Name: " + model.getName(),x + 10,y + 20);
        parent.strokeWeight(1);
        parent.line(x,y+25,x + 150,y+25);
        String date = model.getDate().toString();
        parent.text("Date: " + date,x + 10,y + 40);
        parent.line(x,y+45,x + 150,y+45);
        Locale l = Locale.getDefault();
        parent.text("Amount: " + Currency.getInstance(l).getSymbol() + (Math.floor(model.getAmount()*100)/100),x + 10,y + 60);
        parent.line(x,y+65,x + 150,y+65);
        parent.text("Category: " + model.getCategory().getName(),x + 10,y + 80);
        parent.strokeWeight(2);
        parent.line(x+ 160,y,x + 160,y+100);
        parent.text(model.getDesc(),x + 170,y + 20);
        parent.textSize(8);
        parent.text(String.format("%03d",model.getCont()),x+280,y+90);
    }
}
