package filters;

import controlP5.ControlP5;
import controlP5.DropdownList;
import expense.Categories;
import processing.core.PApplet;

public class FilterView {
    private ControlP5 cp5;
    private PApplet parent;
    private DropdownList select;

    public FilterView(ControlP5 cp5, PApplet parent) {
        this.cp5 = cp5;
        this.parent = parent;
        select = cp5.addDropdownList("select").hide();
        select.addItem("FOOD", Categories.FOOD);
        select.addItem("SHOPPING", Categories.SHOPPING);
        select.addItem("PLEASURE", Categories.PLEASURE);
    }

    public void showList() {select.show();}
    public void hideList() {select.hide();}
}
