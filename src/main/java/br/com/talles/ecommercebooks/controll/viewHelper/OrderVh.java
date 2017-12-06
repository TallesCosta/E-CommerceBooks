package br.com.talles.ecommercebooks.controll.viewHelper;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.CreditCard;
import br.com.talles.ecommercebooks.domain.customer.DeliveryAddress;
import br.com.talles.ecommercebooks.domain.sale.Delivery;
import br.com.talles.ecommercebooks.domain.sale.Sale;
import br.com.talles.ecommercebooks.domain.sale.ShippingCost;
import br.com.talles.ecommercebooks.domain.sale.Status;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderVh implements IViewHelper {

    @Override
    public Entity getEntity(HttpServletRequest request) {
        // Sale datas
        String idS = request.getParameter("id");
        long id = 0L;
        if (!(idS == null || idS.equals("")))
            id = Long.valueOf(idS);

        // Sale
        Sale sale = new Sale();

        switch(request.getParameter("operation")) {
            case "CREATE" :
                break;

            case "SAVE":
                break;

            case "LIST":
                break;

            case "LIST-DISABLE":
                break;

            case "FIND":
                sale.setId(id);
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

        return sale;
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
                    dispatcher = request.getRequestDispatcher("/order/list.jsp");
                    dispatcher.forward(request, response);
                    break;

                case "LIST-DISABLE":
                    break;

                case "FIND":
                    dispatcher = request.getRequestDispatcher("/order/show.jsp");
                    dispatcher.forward(request, response);
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
