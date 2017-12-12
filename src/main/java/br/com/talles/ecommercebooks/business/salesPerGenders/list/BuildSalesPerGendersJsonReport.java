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

public class BuildSalesPerGendersJsonReport implements IStrategy {

    @Override
    public Result process(Entity entity, Result result) {
        SalesPerGenders salesPerGenders;
        salesPerGenders = (SalesPerGenders) result.getEntities(SalesPerGenders.class.getSimpleName()).get(0);

        IntervalDates intervalDates = (IntervalDates) salesPerGenders.getSpecificationQuery();

        List<ResultQuery> resultsQuery = salesPerGenders.getResultsQuery();
        List<CountOfGenders> countOfGenders = new ArrayList<>();
        for (ResultQuery resultQuery : resultsQuery) {
            countOfGenders.add((CountOfGenders) resultQuery);
        }

        Calendar inicialCalendar = Calendar.getInstance();
        inicialCalendar.setTime(intervalDates.getInicialDate());

        Calendar finalCalendar = Calendar.getInstance();
        finalCalendar.setTime(intervalDates.getFinalDate());

        Calendar auxCalendar = Calendar.getInstance();
        auxCalendar.setTime(intervalDates.getInicialDate());

        String json = "";
        json +=
            "{\n" +
                "\t\"type\": \"line\",\n" +
                "\t\"data\": {\n" +
                    "\t\t\"labels\": [";

        while (auxCalendar.compareTo(finalCalendar) < 1) {
            json += "\"" + auxCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + "/" +
                    auxCalendar.get(Calendar.YEAR) + "\", ";
            auxCalendar.add(Calendar.MONTH, 1);
        }
        json = cutLefts(json);

        json +=     "],\n" +
                    "\t\t\"datasets\": [\n";

        String[] genders = {"Feminino", "Masculino", "Outros"};
        int i = 0;

        for(String genderS : genders) {
            List<CountOfGenders> temp = new ArrayList<>();

            // Classifying CountOfGenders
            for(CountOfGenders countG : countOfGenders)
                if(countG.getGender().getName().equals(genderS))
                    temp.add(countG);

            json +=     "\t\t\t{\n" +
                    "\t\t\t\t\"data\": [";
            for (CountOfGenders countGData : temp) {
                json +=     countGData.getSalesAmmount() + ", ";
            }
            json = cutLefts(json);

            json +=         "],\n" +
                    "\t\t\t\t\"label\": " + "\"" + genderS + "\",\n" +
                    "\t\t\t\t\"borderColor\": " + (i < 1 ? "\"#f26430\"" : (i > 1 ? "\"#6761a8\"" : "\"#009b72\"")) + ",\n" +
                    "\t\t\t\t\"fill\": false\n" +
                    "\t\t\t},\n";
            i++;
        }

        json = cutLefts(json);

        json +=     "\n\t\t]" +
                "\n\t}," +
                "\n\t\"options\": {" +
                    "\n\t\t\"title\": {" +
                    "\n\t\t\"display\": true," +
                    "\n\t\t\"text\": \"Quantidade de vendas de acordo com os gêneros " +
    "(" + inicialCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + "/" + inicialCalendar.get(Calendar.YEAR) +
    "-" + finalCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + "/" + finalCalendar.get(Calendar.YEAR) + ")\"" +
                    "\n\t\t}" +
                "\n\t}" +
            "\n}";

        salesPerGenders.setJson(json);
        result.addEntity(salesPerGenders);

        return result;
    }

    public String cutLefts(String json) {
        return json.substring(0, json.length() - 2);
    }

}
