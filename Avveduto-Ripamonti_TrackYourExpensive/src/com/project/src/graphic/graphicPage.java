package com.project.src.graphic;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;

import com.project.src.expense.Categories;
import com.project.src.expense.ExpenseController;
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

    /**
     * Initializes a new instance of the graphicPage class.
     *
     * @param parent The PApplet instance.
     * @param cp5 The ControlP5 instance.
     */
    public graphicPage(PApplet parent, ControlP5 cp5) {
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
        range = 1;
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
     * @param list The list of ExpenseController instances.
     */
    public void showInterface(ArrayList<ExpenseController> list) {
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
        float distance = parent.dist(width / 3, height - height / 3, width - width / 3 - 20, height - height / 3);
        parent.strokeWeight(2);
        parent.stroke(0);

        if (range != 1) {
            names = new String[range];
            for (int i = range - 1; i >= 0; i--) {
                names[i] = LocalDate.now().minusMonths(i).getMonth().getDisplayName(TextStyle.SHORT, Locale.ITALIAN);
            }
            float dividedDistance = distance / range;
            parent.textAlign(PApplet.CENTER, PApplet.CENTER);
            parent.textSize(16);
            parent.fill(0);
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
            for (int i = 0, x = (int) (width / 3 + dividedDistance); i < points.length; i++, x += dividedDistance) {
                parent.point(x, points[i]);
            }
        }
    }

    /**
     * Calculates the average expense for a given month.
     *
     * @param list The list of ExpenseController instances.
     * @param month The month to calculate the average for.
     * @return The average expense for the month.
     */
    private float calculateAverage(ArrayList<ExpenseController> list, Month month) {
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
     * @param values The values to scale.
     * @return The scaled values.
     */
    private float[] scale(float[] values) {
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
     * Hides the graphical interface.
     */
    public void hideInterface() {
        select.hide();
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
