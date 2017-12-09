package br.com.talles.ecommercebooks.controll.viewHelper;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.ChargeAddress;
import br.com.talles.ecommercebooks.domain.customer.Country;
import br.com.talles.ecommercebooks.domain.customer.Customer;
import br.com.talles.ecommercebooks.domain.customer.State;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChargeAddressVh implements IViewHelper {

    @Override
    public Entity getEntity(HttpServletRequest request) {
        String alias = request.getParameter("homeAlias");
        String city = request.getParameter("homeCity");
        String district = request.getParameter("homeDistrict");
        String homeType = request.getParameter("homeHomeType");
        String number = request.getParameter("homeNumber");
        String observation = request.getParameter("homeObservation");
        String postalCode = request.getParameter("homePostalCode");
        String publicPlace = request.getParameter("homePublicPlace");
        String publicPlaceType = request.getParameter("homePublicPlaceType");

        String idStateS = request.getParameter("homeState");
        long idState = 0L;
        if (!(idStateS == null || idStateS.equals("")))
            idState = Long.valueOf(idStateS);

        String idCountryS = request.getParameter("homeCountry");
        long idCountry = 0L;
        if (!(idCountryS == null || idCountryS.equals("")))
            idCountry = Long.valueOf(idCountryS);

        String idCustomerS = request.getParameter("idCustomer");
        long idCustomer = 0L;
        if (!(idCustomerS == null || idCustomerS.equals("")))
            idCustomer = Long.valueOf(idCustomerS);

        // ChargeAddress
        ChargeAddress chargeAddress = new ChargeAddress();

        switch(request.getParameter("operation")) {
            case "CREATE" :
                chargeAddress.setCustomer(new Customer(idCustomer));
                break;

            case "SAVE":
                chargeAddress.setAlias(alias);
                chargeAddress.setCity(city);
                chargeAddress.setDistrict(district);
                chargeAddress.setHomeType(homeType);
                chargeAddress.setNumber(number);
                chargeAddress.setObservation(observation);
                chargeAddress.setPostalCode(postalCode);
                chargeAddress.setPublicPlace(publicPlace);
                chargeAddress.setPublicPlaceType(publicPlaceType);
                chargeAddress.setState(new State(idState, new Country(idCountry)));
                chargeAddress.setCustomer(new Customer(idCustomer));
                break;

            case "LIST":
                chargeAddress.setCustomer(new Customer(idCustomer));
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

        return chargeAddress;
    }

    @Override
    public void setView(Result result, HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher;
        request.setAttribute("result", result);

        try {
            switch(request.getParameter("operation")) {
                case "CREATE" :
                    dispatcher = request.getRequestDispatcher("/charge-address/create.jsp");
                    dispatcher.forward(request, response);
                    break;

                case "SAVE":
                    response.sendRedirect("/E-CommerceBooks/charge-addresses/list?operation=LIST" +
                            "&idCustomer=" + result.getEntities(Customer.class.getSimpleName()).get(0).getId());
                    break;

                case "LIST":
                    dispatcher = request.getRequestDispatcher("/charge-address/list.jsp");
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
