package br.com.talles.ecommercebooks.controll.viewHelper;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.sale.Exchange;
import br.com.talles.ecommercebooks.domain.sale.Order;
import br.com.talles.ecommercebooks.domain.sale.Sale;
import br.com.talles.ecommercebooks.domain.sale.Status;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExchangeVh implements IViewHelper {

    @Override
    public Entity getEntity(HttpServletRequest request) {
        String idSaleS = request.getParameter("idSale");
        long idOrder = 0L;
        if (!(idSaleS == null || idSaleS.equals("")))
            idOrder = Long.valueOf(idSaleS);

        // Exchange datas
        Exchange exchange = new Exchange();

        switch(request.getParameter("operation")) {
            case "CREATE" :
                exchange.setOrder(new Order(idOrder));
                break;

            case "SAVE":
                exchange.setOrder(new Order(idOrder));
                break;

            case "LIST":
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

        return exchange;
    }

    @Override
    public void setView(Result result, HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher;
        request.setAttribute("result", result);

        try {
            switch(request.getParameter("operation")) {
                case "CREATE" :
                    dispatcher = request.getRequestDispatcher("/exchange/create.jsp");
                    dispatcher.forward(request, response);
                    break;

                case "SAVE":
                    break;

                case "LIST":
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
