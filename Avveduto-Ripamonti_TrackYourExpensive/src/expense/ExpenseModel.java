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
     * @param name
     * @param date
     * @param category
     * @param amount
     * @param desc
     */
    public ExpenseModel(String name, LocalDate date, Categories category, float amount, String desc) {
        this.name = name;
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.desc = desc;
        cont++;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public Categories getCategory() {
        return category;
    }

    public float getAmount() {
        return amount;
    }

    public String getDesc() {
        return desc;
    }

    public int getCont() {
        return cont;
    }
}
