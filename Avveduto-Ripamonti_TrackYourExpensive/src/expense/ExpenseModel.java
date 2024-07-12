package expense;

import java.time.LocalDate;

public class ExpenseModel {
    private String name;
    private LocalDate date;
    private Categories category;
    private static int cont = 0;

    public ExpenseModel(String name, LocalDate date, Categories category) {
        this.name = name;
        this.date = date;
        this.category = category;
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

    public static int getCont() {
        return cont;
    }
}
