package expense;

import java.io.Serializable;


public class LocalDate implements Serializable {
    private int day;
    private int month;
    private int year;
    private boolean leapYear;

    public LocalDate(java.time.LocalDate date){
        day = date.getDayOfMonth();
        month = date.getMonthValue();
        year = date.getYear();
        leapYear = date.isLeapYear();
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public boolean isLeapYear() {
        return leapYear;
    }
    public String toString(){
        return day + "/" + month + "/" + year;
    }
}
