package filters;
import controlP5.*;
import expense.Categories;
import processing.core.PApplet;

public class FilterObj {
    private ControlP5 cp5;
    private PApplet parent;
    private DropdownList select;

    public FilterObj(ControlP5 cp5, PApplet parent) {
        this.cp5 = cp5;
        this.parent = parent;
        select = cp5.addDropdownList("select")
                .setLabel("Filter").setBarHeight(20)
                .setItemHeight(20).setPosition(parent.width-parent.width/4,50)
                .setSize(200,100).close()
                .setBackgroundColor(parent.color(0)).hide();
        select.addItem("FOOD", Categories.FOOD);
        select.addItem("SHOPPING", Categories.SHOPPING);
        select.addItem("PLEASURE", Categories.PLEASURE);
        select.addItem("ALL", Categories.ALL);
    }

    public Categories callback(int cat) {
        Categories target = null;
        switch (cat) {
            case 0 -> {target = Categories.FOOD;}
            case 1 -> {target = Categories.SHOPPING;}
            case 2 -> {target = Categories.PLEASURE;}
            case 3 -> {target = Categories.ALL;}
        }
        System.out.println(target);
        return target;
    }

    public void showList() {select.show();}
    public void hideList() {select.hide();}
    public DropdownList getList() {return select;}
}
