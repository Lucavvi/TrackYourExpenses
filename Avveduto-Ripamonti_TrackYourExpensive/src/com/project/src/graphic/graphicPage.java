package com.project.src.graphic;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;
import com.project.src.expense.ExpenseController;
import controlP5.Button;
import controlP5.ControlEvent;
import controlP5.ControlP5;
import controlP5.DropdownList;
import processing.core.PApplet;

/**
 * Represents the graphical interface for displaying expense data over a selected range of months.
 *
 * @author Angelo Ripamonti & Luca Avveduto
 * @version 1.0
 */
public class graphicPage {
    private DropdownList select;
    private PApplet parent;
    private ControlP5 cp5;
    private int range;
    private LocalDate[] target;
    private String[] names;
    private float[] averages;
    private float[] points;
    private float[] xAxis;
    private Button comeBack;

    /**
     * Initializes a new instance of the graphicPage class.
     *
     * @param parent The PApplet instance. Must not be null
     * @param cp5 The ControlP5 instance. Must not be null
     * @throws NullPointerException if any of the parameters are null
     */
    public graphicPage(PApplet parent, ControlP5 cp5) throws NullPointerException{
        if(parent == null || cp5 == null)throw new NullPointerException("Almost one parameter passed is null");
        this.cp5 = cp5;
        this.parent = parent;
        select = this.cp5.addDropdownList("range")
                .setBarHeight(20)
                .setItemHeight(20)
                .setPosition(parent.width - parent.width / 3, 15)
                .setSize(200, 100)
                .close()
                .setColorBackground(0)
                .hide();
        select.addItem("3 months", 0);
        select.addItem("6 months", 1);
        select.addItem("12 months", 2);
        target = new LocalDate[2];
        target[0] = LocalDate.now();
        range = 3;
        comeBack = cp5.addButton("home").setLabel("Home").setPosition(parent.width-0.5f*parent.width/3, 10).setSize(80, 30).hide();
    }

    /**
     * Callback function for handling selection events from the dropdown list.
     *
     * @param event The ControlEvent instance.
     */
    public void selectCallBack(ControlEvent event) {
        switch ((int) event.getController().getValue()) {
            case 0 -> range = 3;
            case 1 -> range = 6;
            case 2 -> range = 12;
        }
        target[1] = target[0].minusMonths(range);
    }

    /**
     * Displays the graphical interface for the expense data.
     *
     * @param list The list of ExpenseController instances. Must not be null.
     * @throws NullPointerException if any of the parameters are null
     */
    public void showInterface(ArrayList<ExpenseController> list) throws NullPointerException {
        if(list == null) throw new NullPointerException("Almost one parameter passed is null");
        comeBack.show();
        parent.background(255);
        float height = parent.height;
        float width = parent.width;
        parent.fill(229, 229, 229);
        parent.noStroke();
        parent.rect(0, 0, width, 50);
        select.show();
        parent.strokeWeight(2);
        parent.stroke(0);
        parent.line(width / 3, height - height / 3, width / 3, height / 3);
        parent.line(width / 3, height - height / 3, width - width / 3, height - height / 3);
        parent.textAlign(3,3);
        parent.textSize(20);
        parent.fill(0);
        parent.text("Amount",width / 3, height / 3 - 20);
        parent.text("Month",width - width / 3 + 50, height - height / 3);
        parent.textSize(60);
        parent.fill(parent.color(173,0,10));
        parent.text("Graphic traker",parent.width/2,parent.height/3-parent.height/4);
        parent.textAlign(0,0);
        float distance = parent.dist(width / 3, height - height / 3, width - width / 3 - 20, height - height / 3);
        parent.strokeWeight(2);
        parent.stroke(0);
        names = new String[range];
        for (int i = range - 1; i >= 0; i--) {
            names[i] = LocalDate.now().minusMonths(i).getMonth().getDisplayName(TextStyle.SHORT, Locale.ITALIAN);
        }
        float dividedDistance = distance / range;
        parent.textAlign(PApplet.CENTER, PApplet.CENTER);
        parent.textSize(16);
        parent.fill(0);
        xAxis = new float[range];
        for (float i = 1, x = width / 3 + dividedDistance; i <= range; i++, x += dividedDistance) {
            parent.line(x, height - height / 3 - 10, x, height - height / 3 + 10);
            parent.text(names[Math.round(range - i)], x, height - height / 3 + 20);
        }
        averages = new float[range];
        for (int i = 0; i < range; i++) {
            averages[i] = calculateAverage(list, LocalDate.now().minusMonths(i).getMonth());
            if (Float.isNaN(averages[i])) averages[i] = 0;
        }
        points = scale(averages);
        parent.strokeWeight(10);
        parent.stroke(0);
        for (int i = points.length - 1, x = (int) (width / 3 + dividedDistance); i >= 0 ; i--, x += dividedDistance) {
            parent.point(x, points[i]);
            xAxis[i] = x;
        }
        drawLines(xAxis, points);
        parent.textAlign(0,0);
    }

    /**
     * Calculates the average expense for a given month.
     *
     * @param list  The list of ExpenseController instances. Must not be null.
     * @param month The month to calculate the average for. Must not be null.
     * @return The average expense for the month.
     * @throws NullPointerException if any of the parameters are null
     */
    private float calculateAverage(ArrayList<ExpenseController> list, Month month) throws NullPointerException {
        if(list == null || month == null) throw new NullPointerException("Almost one parameter passed is null");
        ArrayList<Float> filteredList = new ArrayList<>();
        for (ExpenseController e : list) {
            if (e.getModel().getDate().getMonth() == month.getValue()) {
                filteredList.add(e.getModel().getAmount());
            }
        }
        float sum = 0;
        for (float e : filteredList) sum += e;
        return filteredList.isEmpty() ? 0 : sum / filteredList.size();
    }

    /**
     * Scales the values to fit within the graphical interface.
     *
     * @param values The values to scale. Must not be null.
     * @return The scaled values.
     * @throws NullPointerException if any of the parameters are null
     */
    private float[] scale(float[] values) throws NullPointerException{
        if(values == null) throw new NullPointerException("Almost one parameter passed is null");
        float max = 0;
        for (float x : values) {
            if (x > max) {
                max = x;
            }
        }
        float[] result = new float[values.length];
        float graphHeight = parent.height - (parent.height / 3) * 2;
        for (int i = 0; i < values.length; i++) {
            if (values[i] == 0) {
                result[i] = parent.height - (parent.height / 3);
            } else {
                result[i] = parent.height - ((values[i] / max) * graphHeight + (parent.height / 3));
            }
        }
        return result;
    }

    /**
     * Draws lines connecting the given data points on the graphical interface.
     *
     * This method iterates through the provided x and y coordinates and draws lines between consecutive points
     * to represent the data graphically.
     *
     * @param x an array of x coordinates for the data points. Must not be null.
     * @param y an array of y coordinates for the data points. Must not be null.
     * @throws NullPointerException if either x or y is null.
     */
    private void drawLines(float[] x, float[] y) throws NullPointerException{
        if(x == null || y == null) throw new NullPointerException("Almost one parameter passed is null");
        parent.strokeWeight(2);
        parent.stroke(parent.color(0,0,255));
        for (int i = 0; i < x.length - 1; i++) {
            parent.line(x[i], y[i], x[i + 1], y[i + 1]);
        }
    }

    /**
     * Hides the graphical interface.
     */
    public void hideInterface() {
        select.hide();
        comeBack.hide();
    }

    /**
     * Gets the dropdown list for selecting the range.
     *
     * @return The dropdown list.
     */
    public DropdownList getSelect() {
        return select;
    }

    /**
     * Gets the target date range.
     *
     * @return The target date range.
     */
    public LocalDate[] getTarget() {
        return target;
    }
}