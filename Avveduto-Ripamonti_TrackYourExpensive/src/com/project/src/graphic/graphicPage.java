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

public class graphicPage {
    private DropdownList select;
    private PApplet parent;
    private ControlP5 cp5;
    private int range;
    private LocalDate target[];
    private String names[];
    private float averages[];
    private float points[];

    public graphicPage(PApplet parent, ControlP5 cp5) {
        this.cp5 = cp5;
        this.parent = parent;
        select = this.cp5.addDropdownList("range")
                .setBarHeight(20)
                .setItemHeight(20).setPosition(parent.width - parent.width / 3, 15)
                .setSize(200, 100).close()
                .setColorBackground(0).hide();
        select.addItem("3 months", 0);
        select.addItem("6 months", 1);
        select.addItem("12 months", 2);
        target = new LocalDate[2];
        target[0] = LocalDate.now();
        range = 1;
    }

    public void selectCallBack(ControlEvent event) {
        switch ((int) event.getController().getValue()) {
            case 0 -> {
                range = 3;
            }
            case 1 -> {
                range = 6;
            }
            case 2 -> {
                range = 12;
            }
        }
        target[1] = target[0].minusMonths(range);
    }

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
            parent.textAlign(3, 3);
            parent.textSize(16);
            parent.fill(0);
            for (float i = 1, x = width / 3 + dividedDistance, index = names.length - 1; i <= range; i++, x += dividedDistance, index--) {
                parent.line(x, height - height / 3 - 50, x, height - height / 3 + 50);
                parent.text(names[(int) index], x, height - height / 3 + 70);
            }
            averages = new float[range];
            for (int i = 0; i < range; i++) {
                averages[i] = calculateAverage(list, LocalDate.now().minusMonths(i).getMonth());
                if (Float.isNaN(averages[i])) averages[i] = 0;
            }
            points = scale(averages);
            parent.strokeWeight(20);
            parent.stroke(0);
            for (float i = 0, x = width / 3 + dividedDistance; i < points.length; i++, x += dividedDistance) {
                parent.point(x, points[(int) i]);
                System.out.println(points[(int) i]);
            }
        }
    }

    private float calculateAverage(ArrayList<ExpenseController> list, Month m) {
        ArrayList<Float> filteredList = new ArrayList<>();
        for (ExpenseController e : list) {
            if (Month.values()[e.getModel().getDate().getMonth() - 1] == m) {
                filteredList.add(e.getModel().getAmount());
            }
        }
        float sum = 0;
        for (float e : filteredList) sum += e;
        return sum / filteredList.size();
    }


    private float[] scale(float values[]) {
        float max = 0;
        float result[] = new float[values.length];
        float longness = parent.dist(parent.width/3, parent.height-parent.height/3, parent.width/3, parent.height/3);
        for (float x : values) {
            if (x > max) {
                max = x;
            }
        }
        float unit = longness/values.length;
        for(int i = 0; i < values.length; i++) {
            if(values[i] == 0) {
                values[i] = parent.height-parent.height/3;
            }
            else if(values[i] == max) result[i] = parent.height/3;
            else {
                result[i] = max/result[i]*unit;
            }
        }
        return result;
    }

    public void hideInterface() {
        select.hide();
    }

    public DropdownList getSelect() {
        return select;
    }

    public LocalDate[] getTarget() {
        return target;
    }
}
