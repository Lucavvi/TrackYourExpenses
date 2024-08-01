package com.project.src.expense;
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
     * @param parent the main instance of PApplet used for drawing. Must not be null.
     * @param model the ExpenseModel containing the data to display. Must not be null.
     * @param x the x-coordinate position where the expense will be displayed. Must not be less than 0 or more than parent.width.
     * @param y the y-coordinate position where the expense will be displayed. Must not be less than 0 or more than parent.height.
     * @throws NullPointerException if any of the parameters are null.
     * @throws IllegalArgumentException If x or y are less than 0. Otherwise, if x are more than parent.width or y are more than parent.height.
     */
    public void view(PApplet parent,ExpenseModel model, int x, int y) throws NullPointerException, IllegalArgumentException{
        if(parent == null || model == null) throw new NullPointerException("Almost one parameter passed is null");
        if(x < 0 || y < 0 || x > parent.width || y > parent.height) throw new IllegalArgumentException("x or y are less than 0. Otherwise, x are more than parent.width or y are more than parent.height");
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
    }
}
