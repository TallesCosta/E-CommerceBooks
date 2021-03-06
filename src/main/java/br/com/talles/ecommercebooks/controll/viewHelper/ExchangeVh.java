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

        String acceptedS = request.getParameter("accepted");
        Boolean accepted = null;
        if (!(acceptedS == null || acceptedS.equals("")))
            accepted = acceptedS.equals("true") ? true : false;


        String justification = request.getParameter("justification");
        String destination = request.getParameter("destination");

        // Exchange datas
        Exchange exchange = new Exchange();

        switch(request.getParameter("operation")) {
            case "CREATE" :
                exchange.setOrder(new Order(idOrder));
                break;

            case "SAVE":
                exchange.setOrder(new Order(idOrder));
                exchange.setJustification(justification);
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
                exchange.setOrder(new Order(idOrder));
                exchange.setAccepted(accepted);
                exchange.setDestination(destination);
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
                    response.sendRedirect("/E-CommerceBooks/orders/list?operation=LIST");
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
                    response.sendRedirect("/E-CommerceBooks/sales/list?operation=LIST");
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
