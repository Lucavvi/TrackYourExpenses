package expense;

import java.io.Serializable;

/**
 * MVC class expense
 * @author luke & Angelo
 * @version 1.0
 */
public class ExpenseModel implements Serializable {
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
    public ExpenseModel(String name, LocalDate date, Categories category, float amount, String desc) throws RuntimeException{
        if(name.length() <= 10) this.name = name;
        else throw new RuntimeException("Name too long");
        this.date = date;
        this.category = category;
        this.amount = amount;
        cont++;
        this.desc = "";
        if(desc.length() > (14 * 4)) throw new RuntimeException("Description is too long");
        for(int i = 0; i < desc.length(); i++) {
            this.desc += desc.charAt(i);
            if(i % 14 == 0 && i != 0) {
                this.desc += "\n";
            }
        }
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
