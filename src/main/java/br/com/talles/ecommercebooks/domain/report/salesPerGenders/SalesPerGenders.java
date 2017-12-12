package br.com.talles.ecommercebooks.domain.report.salesPerGenders;

import br.com.talles.ecommercebooks.domain.report.Report;
import br.com.talles.ecommercebooks.domain.report.ResultQuery;
import br.com.talles.ecommercebooks.domain.report.SpecificationQuery;

import java.util.ArrayList;
import java.util.List;

public class SalesPerGenders extends Report {

    public SalesPerGenders() {
        super.setResultsQuery(new ArrayList<ResultQuery>());
    }

    public SalesPerGenders(SpecificationQuery specificationQuery) {
        super.setSpecificationQuery(specificationQuery);
        super.setResultsQuery(new ArrayList<ResultQuery>());
    }

    public SalesPerGenders(SpecificationQuery specificationQuery, List<ResultQuery> resultsQuery) {
        super.setSpecificationQuery(specificationQuery);
        super.setResultsQuery(resultsQuery);
    }

}
