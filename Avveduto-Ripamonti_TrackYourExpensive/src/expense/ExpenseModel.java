package expense;

import java.time.LocalDate;

/**
 * MVC class expense
 * @author luke & Angelo
 * @version 1.0
 */
public class ExpenseModel {
    private String name;
    private LocalDate date;
    private Categories category;
    private float amount;
    private String desc;
    private static int cont = 0;

    /**
     * @param name - name of the expense
     * @param date - date of the expense
     * @param category - category of the expense
     * @param amount - cost of the expese
     * @param desc - description
     * constructor
     */
    public ExpenseModel(String name, LocalDate date, Categories category, float amount, String desc) {
        this.name = name;
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.desc = desc;
        cont++;
    }

    /**
     * @return the expense's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the expense's date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @return the expense's category
     */
    public Categories getCategory() {
        return category;
    }

    /**
     * @return the expense's cost
     */
    public float getAmount() {
        return amount;
    }

    /**
     * @return description
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @return the expense's number
     */
    public int getCont() {
        return cont;
    }
}
