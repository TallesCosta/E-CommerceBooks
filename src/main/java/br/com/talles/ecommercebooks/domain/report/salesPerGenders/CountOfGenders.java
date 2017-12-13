package br.com.talles.ecommercebooks.domain.report.salesPerGenders;

import br.com.talles.ecommercebooks.domain.customer.Gender;
import br.com.talles.ecommercebooks.domain.report.ResultQuery;

public class CountOfGenders extends ResultQuery {

    private int salesAmmount;
    private Gender gender;
    private int month;
    private int year;

    public CountOfGenders() {
    }

    public CountOfGenders(Gender gender, int month, int year) {
        this.gender = gender;
        this.month = month;
        this.year = year;
    }

    public int getSalesAmmount() {
        return salesAmmount;
    }

    public void setSalesAmmount(int salesAmmount) {
        this.salesAmmount = salesAmmount;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof CountOfGenders)) return false;
        CountOfGenders countOfGenders = (CountOfGenders) o;
        return this.getGender().equals(countOfGenders.getGender()) &&
                this.month == countOfGenders.month &&
                this.year == countOfGenders.year;
    }

    @Override
    public int compareTo(ResultQuery other) {
        if(!( other instanceof CountOfGenders ))
            return -1;

        CountOfGenders otherCount = (CountOfGenders) other;

        return  getYear() < otherCount.getYear() ? -1 :
                getYear() > otherCount.getYear() ? 1 :
                    getMonth() < otherCount.getMonth() ? -1 :
                    getMonth() > otherCount.getMonth() ? 1 :
                        getGender().getName().compareTo(otherCount.getGender().getName());
    }

}
