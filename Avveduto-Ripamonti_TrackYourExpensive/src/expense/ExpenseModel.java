package expense;

import java.time.LocalDate;

public class ExpenseModel {
    private String name;
    private LocalDate date;
    private Categories category;
    private float amount;
    private String desc;
    private static int cont = 0;

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

    public static int getCont() {
        return cont;
    }
}
