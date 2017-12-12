package br.com.talles.ecommercebooks.domain.report;

import br.com.talles.ecommercebooks.domain.Entity;

import java.util.ArrayList;
import java.util.List;

public abstract class Report extends Entity {

    private String json = "";
    private SpecificationQuery specificationQuery;
    private List<ResultQuery> resultsQuery;

    public Report() {
    }

    public Report(SpecificationQuery specificationQuery) {
        this.specificationQuery = specificationQuery;
    }

    public Report(List<ResultQuery> resultsQuery) {
        this.resultsQuery = resultsQuery;
    }

    public Report(SpecificationQuery specificationQuery, List<ResultQuery> resultsQuery) {
        this.specificationQuery = specificationQuery;
        this.resultsQuery = resultsQuery;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public SpecificationQuery getSpecificationQuery() {
        return specificationQuery;
    }

    public void setSpecificationQuery(SpecificationQuery specificationQuery) {
        this.specificationQuery = specificationQuery;
    }

    public List<ResultQuery> getResultsQuery() {
        return resultsQuery;
    }

    public void setResultsQuery(List<ResultQuery> resultsQuery) {
        this.resultsQuery = resultsQuery;
    }

    public void addResultQuery(ResultQuery resultQuery) {
        this.resultsQuery.add(resultQuery);
    }

    public void clearResultQuery() {
        this.resultsQuery = new ArrayList<>();
    }
}
