package filters;

import controlP5.ControlP5;
import controlP5.DropdownList;
import expense.ExpenseController;
import expense.comparators.ExpenseAmountComparator;
import expense.comparators.ExpenseDateComparator;
import expense.comparators.ExpenseNameComparator;
import processing.core.PApplet;
import java.util.Comparator;

public class OrderObj {
    private ControlP5 cp5;
    private PApplet parent;
    private DropdownList reorder;

    public OrderObj(ControlP5 cp5, PApplet parent) {
        this.cp5 = cp5;
        this.parent = parent;
        reorder = cp5.addDropdownList("reorder")
                .setLabel("Filter").setBarHeight(20)
                .setItemHeight(20).setPosition(parent.width-parent.width/3,15)
                .setSize(200,100).close()
                .setColorBackground(0).hide();
        reorder.addItem("Date descending", 0);
        reorder.addItem("Date ascending",1);
        reorder.addItem("Name ascending",2);
        reorder.addItem("Name ascending",3);
        reorder.addItem("Amount ascending",4);
        reorder.addItem("Amount ascending",5);
    }

    public Comparator<ExpenseController> callback(int cat) {
        Comparator<ExpenseController> comparator = null;
        switch (cat) {
            case 0 -> {comparator = new ExpenseDateComparator().reversed();}
            case 1 -> {comparator = new ExpenseDateComparator();}
            case 2 -> {comparator = new ExpenseNameComparator();}
            case 3 -> {comparator = new ExpenseNameComparator().reversed();}
            case 4 -> {comparator = new ExpenseAmountComparator();}
            case 5 -> {comparator = new ExpenseAmountComparator().reversed();}
        }
        return comparator;
    }

    public void showList() {reorder.show();}
    public void hideList() {reorder.hide();}
    public DropdownList getList() {return reorder;}
}
