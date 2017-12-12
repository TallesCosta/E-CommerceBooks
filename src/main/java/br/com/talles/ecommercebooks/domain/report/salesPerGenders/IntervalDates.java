package br.com.talles.ecommercebooks.domain.report.salesPerGenders;

import br.com.talles.ecommercebooks.domain.report.SpecificationQuery;

import java.util.Date;

public class IntervalDates extends SpecificationQuery {

    private Date inicialDate;
    private Date finalDate;

    public IntervalDates() {
    }

    public IntervalDates(Date inicialDate, Date finalDate) {
        this.inicialDate = inicialDate;
        this.finalDate = finalDate;
    }

    public Date getInicialDate() {
        return inicialDate;
    }

    public void setInicialDate(Date inicialDate) {
        this.inicialDate = inicialDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

}
