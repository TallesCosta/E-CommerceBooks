package br.com.talles.ecommercebooks.controll.viewHelper;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.CardCompany;
import br.com.talles.ecommercebooks.domain.customer.CreditCard;
import br.com.talles.ecommercebooks.domain.customer.Customer;

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

public class CreditCardVh implements IViewHelper {

    @Override
    public Entity getEntity(HttpServletRequest request) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String number = request.getParameter("cardNumber");
        String printedName = request.getParameter("printedName");
        String securityCode = request.getParameter("securityCode");

        String idCardCompanyS = request.getParameter("cardCompany");
        long idCardCompany = 0L;
        if (!(idCardCompanyS == null || idCardCompanyS.equals("")))
            idCardCompany = Long.valueOf(idCardCompanyS);

        String expirationDateS = request.getParameter("expirationDate");
        Date expirationDate = new Date(0L);
        try {
            expirationDate = dateFormat.parse(expirationDateS);
        } catch (ParseException | NullPointerException ex) {
            Logger.getLogger(CreditCardVh.class.getName()).log(Level.SEVERE, null, ex);
        }

        String idCustomerS = request.getParameter("idCustomer");
        long idCustomer = 0L;
        if (!(idCustomerS == null || idCustomerS.equals("")))
            idCustomer = Long.valueOf(idCustomerS);

        // CreditCard
        CreditCard creditCard = new CreditCard();

        switch(request.getParameter("operation")) {
            case "CREATE" :
                creditCard.setCustomer(new Customer(idCustomer));
                break;

            case "SAVE":
                creditCard.setNumber(number);
                creditCard.setPrintedName(printedName);
                creditCard.setSecurityCode(securityCode);
                creditCard.setExpirationDate(expirationDate);
                creditCard.setCardCompany(new CardCompany(idCardCompany));
                creditCard.setCustomer(new Customer(idCustomer));
                break;

            case "LIST":
                creditCard.setCustomer(new Customer(idCustomer));
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

        return creditCard;
    }

    @Override
    public void setView(Result result, HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher;
        request.setAttribute("result", result);

        try {
            switch(request.getParameter("operation")) {
                case "CREATE" :
                    dispatcher = request.getRequestDispatcher("/credit-card/create.jsp");
                    dispatcher.forward(request, response);
                    break;

                case "SAVE":
                    response.sendRedirect("/E-CommerceBooks/credit-cards/list?operation=LIST" +
                            "&idCustomer=" + result.getEntities(Customer.class.getSimpleName()).get(0).getId());
                    break;

                case "LIST":
                    dispatcher = request.getRequestDispatcher("/credit-card/list.jsp");
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
