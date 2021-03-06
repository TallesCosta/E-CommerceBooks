package br.com.talles.ecommercebooks.controll.viewHelper;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.report.salesPerGenders.CountOfGenders;
import br.com.talles.ecommercebooks.domain.report.salesPerGenders.IntervalDates;
import br.com.talles.ecommercebooks.domain.report.salesPerGenders.SalesPerGenders;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SalesPerGendersVh implements IViewHelper {

    @Override
    public Entity getEntity(HttpServletRequest request) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String inicialDateS = request.getParameter("inicialDate");
        Date inicialDate = new Date(0L);

        String finalDateS = request.getParameter("finalDate");
        Date finalDate = new Date(0L);
        try {
            //inicialDate = (inicialDate == null ? dateFormat.parse("2016-12-01") : dateFormat.parse(inicialDateS));
            if (inicialDateS == null || inicialDateS.equals(""))
                inicialDate = dateFormat.parse("2017-01-01");
            else {
                String[] tmp = inicialDateS.split("-");
                tmp[2] = "01";
                inicialDateS = tmp[0] + "-" + tmp[1] + "-" + tmp[2];
                inicialDate = dateFormat.parse(inicialDateS);
            }

            //finalDate = (inicialDate == null ? dateFormat.parse("2017-12-01") : dateFormat.parse(finalDateS));
            if (finalDateS == null || inicialDateS.equals(""))
                finalDate = dateFormat.parse("2017-12-31");
            else{
                String[] tmp = finalDateS.split("-");

                switch (tmp[1]){
                    case "01":
                    case "03":
                    case "05":
                    case "07":
                    case "08":
                    case "10":
                    case "12":
                        tmp[2] = "31";
                        break;
                    case "04":
                    case "06":
                    case "09":
                    case "11":
                        tmp[2] = "30";
                        break;
                    default:
                        tmp[2] = "27";
                        break;
                }

                finalDateS = tmp[0] + "-" + tmp[1] + "-" + tmp[2];
                finalDate = dateFormat.parse(finalDateS);
            }

            //finalDate = dateFormat.parse(finalDateS);
            //finalDate = dateFormat.parse("2017-12-31");
        } catch (ParseException | NullPointerException ex) {
            Logger.getLogger(CustomerVh.class.getName()).log(Level.SEVERE, null, ex);
        }

        // SalePerGenders
        SalesPerGenders salesPerGenders = new SalesPerGenders(new IntervalDates());

        switch(request.getParameter("operation")) {
            case "CREATE" :
                break;

            case "SAVE":
                break;

            case "LIST":
                ((IntervalDates) salesPerGenders.getSpecificationQuery()).setInicialDate(inicialDate);
                ((IntervalDates) salesPerGenders.getSpecificationQuery()).setFinalDate(finalDate);
                break;

            case "LIST-DISABLE":
                break;

            case "FIND":
                break;

            case "HISTORY":
                break;

            case "UPDATE":
                break;

            case "DISABLE":
                break;

            case "ENABLE":
                break;

            case "DELETE":
                break;
        }

        return salesPerGenders;
    }

    @Override
    public void setView(Result result, HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher;
        request.setAttribute("result", result);

        try {
            switch(request.getParameter("operation")) {
                case "CREATE" :
                    break;

                case "SAVE":
                    break;

                case "LIST":
                    dispatcher = request.getRequestDispatcher("/dashboard-admin.jsp");
                    dispatcher.forward(request, response);
                    break;

                case "LIST-DISABLE":
                    break;

                case "FIND":
                    break;

                case "HISTORY":
                    break;

                case "UPDATE":
                    break;

                case "DISABLE":
                    break;

                case "ENABLE":
                    break;

                case "DELETE":
                    break;
            }
        } catch (ServletException | IOException ex) {
            Logger.getLogger(BookVh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
