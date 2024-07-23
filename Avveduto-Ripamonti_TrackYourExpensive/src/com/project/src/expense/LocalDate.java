package com.project.src.expense;

import java.io.Serializable;

/**
 * This class represents a local date with day, month, year, and leap year information.
 * It provides methods to check if this date is before, after, or equal to another date.
 *
 * @author Angelo Ripamonti & Luca Avveduta
 * @version 1.0
 */
public class LocalDate implements Serializable {
    private int day;
    private int month;
    private int year;
    private boolean leapYear;

    /**
     * Constructs a LocalDate instance from a java.time.LocalDate.
     *
     * @param date the java.time.LocalDate instance
     */
    public LocalDate(java.time.LocalDate date) {
        this.day = date.getDayOfMonth();
        this.month = date.getMonthValue();
        this.year = date.getYear();
        this.leapYear = date.isLeapYear();
    }

    /**
     * Returns the day of the month.
     *
     * @return the day of the month
     */
    public int getDay() {
        return day;
    }

    /**
     * Returns the month of the year.
     *
     * @return the month of the year
     */
    public int getMonth() {
        return month;
    }

    /**
     * Returns the year.
     *
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * Returns whether the year is a leap year.
     *
     * @return true if the year is a leap year, false otherwise
     */
    public boolean isLeapYear() {
        return leapYear;
    }

    /**
     * Returns a string representation of the date in the format day/month/year.
     *
     * @return a string representation of the date
     */
    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }

    /**
     * Checks if this date is before the specified date.
     *
     * @param date the other LocalDate to compare to
     * @return true if this date is before the specified date, false otherwise
     */
    public boolean isBefore(LocalDate date) {
        if (this.year < date.year) return true;
        if (this.year > date.year) return false;
        if (this.month < date.month) return true;
        if (this.month > date.month) return false;
        return this.day < date.day;
    }

    /**
     * Checks if this date is after the specified date.
     *
     * @param date the other LocalDate to compare to
     * @return true if this date is after the specified date, false otherwise
     */
    public boolean isAfter(LocalDate date) {
        if (this.year > date.year) return true;
        if (this.year < date.year) return false;
        if (this.month > date.month) return true;
        if (this.month < date.month) return false;
        return this.day > date.day;
    }

    /**
     * Checks if this date is equal to the specified date.
     *
     * @param date the other LocalDate to compare to
     * @return true if this date is equal to the specified date, false otherwise
     */
    public boolean equals(LocalDate date) {
        return this.year == date.year && this.month == date.month && this.day == date.day;
    }

}
