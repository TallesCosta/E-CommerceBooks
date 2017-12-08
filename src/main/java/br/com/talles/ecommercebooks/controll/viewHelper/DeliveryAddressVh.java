package br.com.talles.ecommercebooks.controll.viewHelper;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.DeliveryAddress;
import br.com.talles.ecommercebooks.domain.customer.State;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeliveryAddressVh implements IViewHelper {

    @Override
    public Entity getEntity(HttpServletRequest request) {
        String alias = request.getParameter("alias");
        String city = request.getParameter("city");
        String district = request.getParameter("district");
        String homeType = request.getParameter("homeType");
        String number = request.getParameter("number");
        String observation = request.getParameter("observation");
        String postalCode = request.getParameter("postalCode");
        String publicPlace = request.getParameter("publicPlace");
        String publicPlaceType = request.getParameter("publicPlaceType");

        String idStateS = request.getParameter("idState");
        long idState = 0L;
        if (!(idStateS == null || idStateS.equals("")))
            idState = Long.valueOf(idStateS);

        // DeliveryAddress
        DeliveryAddress deliveryAddress = new DeliveryAddress();

        switch(request.getParameter("operation")) {
            case "CREATE" :
                break;

            case "SAVE":
                deliveryAddress.setAlias(alias);
                deliveryAddress.setCity(city);
                deliveryAddress.setDistrict(district);
                deliveryAddress.setHomeType(homeType);
                deliveryAddress.setNumber(number);
                deliveryAddress.setObservation(observation);
                deliveryAddress.setPostalCode(postalCode);
                deliveryAddress.setPublicPlace(publicPlace);
                deliveryAddress.setPublicPlaceType(publicPlaceType);
                deliveryAddress.setState(new State(idState));
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

        return deliveryAddress;
    }

    @Override
    public void setView(Result result, HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher;
        request.setAttribute("result", result);

        try {
            switch(request.getParameter("operation")) {
                case "CREATE" :
                    dispatcher = request.getRequestDispatcher("/delivery-address/create.jsp");
                    dispatcher.forward(request, response);
                    break;

                case "SAVE":
                    response.sendRedirect("/E-CommerceBooks/delivery-addresses/list?operation=LIST");
                    break;

                case "LIST":
                    dispatcher = request.getRequestDispatcher("/delivery-address/list.jsp");
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
