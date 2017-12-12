package br.com.talles.ecommercebooks.domain.report;

public class SalesPerGender extends Report {

    public SalesPerGender(SpecificationQuery specificationQuery, ResultQuery resultQuery) {
        super.setSpecificationQuery(specificationQuery);
        super.setResultQuery(resultQuery);
    }

}
