package br.com.talles.ecommercebooks.controll.viewHelper;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.sale.*;

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
        // OrderRequest datas
        String idS = request.getParameter("id");
        long id = 0L;
        if (!(idS == null || idS.equals("")))
            id = Long.valueOf(idS);

        String status = request.getParameter("status");

        // OrderRequest
        OrderRequest orderRequest = new OrderRequest();

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
                orderRequest.setId(id);
                break;

            case "HISTORY":
                break;

            case "UPDATE":
                orderRequest.setId(id);
                orderRequest.setStatus(new Status(status));
                break;

            case "DISABLE":
                break;

            case "ENABLE":
                break;

            case "DELETE":
                break;
        }

        return orderRequest;
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
                    response.sendRedirect("/E-CommerceBooks/orders/list?operation=LIST");
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
