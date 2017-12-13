package br.com.talles.ecommercebooks.business.salesPerGenders.list;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.Gender;
import br.com.talles.ecommercebooks.domain.report.ResultQuery;
import br.com.talles.ecommercebooks.domain.report.salesPerGenders.CountOfGenders;
import br.com.talles.ecommercebooks.domain.report.salesPerGenders.IntervalDates;
import br.com.talles.ecommercebooks.domain.report.salesPerGenders.SalesPerGenders;

import java.util.*;

public class CompleteReportConsult implements IStrategy {

    @Override
    public Result process(Entity entity, Result result) {
        SalesPerGenders salesPerGendersReport = (SalesPerGenders) entity;
        IntervalDates intervalDates = (IntervalDates) salesPerGendersReport.getSpecificationQuery();

        Calendar finalCalendar = Calendar.getInstance();
        finalCalendar.setTime(intervalDates.getFinalDate());

        Calendar auxCalendar = Calendar.getInstance();
        auxCalendar.setTime(intervalDates.getInicialDate());

        String[] genders = {"Feminino", "Masculino", "Outros"};
        while (auxCalendar.compareTo(finalCalendar) < 1) {
            for (String gender : genders) {
                CountOfGenders countG = new CountOfGenders();

                countG.setGender(new Gender(gender));
                countG.setMonth(auxCalendar.get(Calendar.MONTH) + 1);
                countG.setYear(auxCalendar.get(Calendar.YEAR));

                if (!salesPerGendersReport.getResultsQuery().contains(countG))
                    salesPerGendersReport.addResultQuery(countG);
            }
            auxCalendar.add(Calendar.MONTH, 1);
        }

        Collections.sort(salesPerGendersReport.getResultsQuery(), new Comparator<ResultQuery>() {
            @Override
            public int compare(ResultQuery o1, ResultQuery o2) {
                return o1.compareTo(o2);
            }
        });

        return result;
    }

}
