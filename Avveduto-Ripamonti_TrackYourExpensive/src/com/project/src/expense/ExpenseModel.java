package com.project.src.expense;

import java.io.Serializable;

/**
 * MVC class representing an expense model.
 * This class stores the details of an expense, including its name, date, category, amount, and description.
 *
 * @author Angelo Ripamonti & Luca Avveduto
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
     * Constructs an ExpenseModel with the specified details.
     *
     * @param name the name of the expense
     * @param date the date of the expense
     * @param category the category of the expense
     * @param amount the cost of the expense
     * @param desc the description of the expense
     * @throws RuntimeException if the name or description is too long
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
     * Gets the name of the expense.
     *
     * @return the name of the expense
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the date of the expense.
     *
     * @return the date of the expense
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets the category of the expense.
     *
     * @return the category of the expense
     */
    public Categories getCategory() {
        return category;
    }

    /**
     * Gets the amount of the expense.
     *
     * @return the amount of the expense
     */
    public float getAmount() {
        return amount;
    }

    /**
     * Gets the description of the expense.
     *
     * @return the description of the expense
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Gets the total number of expense instances.
     *
     * @return the total number of expense instances
     */
    public int getCont() {
        return cont;
    }
}
