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

    /**
     * Constructs an ExpenseModel with the specified details.
     *
     * @param name the name of the expense. Must not be null or blank. The character must not be more than 10.
     * @param date the date of the expense. Must not be null. Must not be in the future.
     * @param category the category of the expense. Must not be null.
     * @param amount the cost of the expense. Must not be NaN or Infinite.
     * @param desc the description of the expense. Must not be null or blank. The character must not be more than 56.
     * @throws NullPointerException if any of the parameters are null.
     * @throws IllegalArgumentException if the name or description is too long or blank. If the amount is NaN or Infinite. If the date is in the future.
     */
    public ExpenseModel(String name, LocalDate date, Categories category, float amount, String desc) throws NullPointerException, IllegalArgumentException{
        if(name == null || date == null || category == null || desc == null) throw new NullPointerException("Almost one parameter passed is null");
        if(name.isBlank() || desc.isBlank()) throw new IllegalArgumentException("Almost one parameter passed is blank");
        if(date.isAfter(new LocalDate(java.time.LocalDate.now()))) throw new IllegalArgumentException("Date passed is in the future");
        if(Float.isNaN(amount) || Float.isInfinite(amount)) throw new IllegalArgumentException("Amount passed is Not a Number or Infinite number.");
        if(name.length() <= 10) this.name = name;
        else throw new IllegalArgumentException("Name passed too long.");
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.desc = "";
        if(desc.length() > (14 * 4)) throw new IllegalArgumentException("Description passed is too long");
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

}
