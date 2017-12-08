<%@ page import="br.com.talles.ecommercebooks.controll.Result" %>
<%@ page import="br.com.talles.ecommercebooks.domain.Entity" %>
<%@ page import="br.com.talles.ecommercebooks.domain.customer.DeliveryAddress" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Seus Endereços de Entrega</title>

    <%@include file="../commons/customer/menu-css.jsp"%>
</head>
<body>

<%
    Result result = (Result) request.getAttribute("result");

    if (result != null) {
        if (result.hasMsg()) {
            String[] msgs = result.getMsg().split("\n");
            out.println("<p>");
            for(String msg : msgs)
                out.println("<i class='fa fa-times' aria-hidden='true' style='color: #FF0000;'></i> " + msg + "<br/>");
            out.println("</p>");
        }
%>
<%@include file="../commons/customer/menu-html.jsp"%>

<div class="container">
    <h1 id="list-delivery-address">Seus Endereços de Entrega</h1>

    <div class="row">
        <div class="column">
            <table>
                <thead>
                <tr>
                    <th>Apelido</th>
                    <th>Tipo Logra.</th>
                    <th>Logradouro</th>
                    <th>Número</th>
                    <th>Bairro</th>
                    <th>Cidade</th>
                    <th>Tipo Residência</th>
                    <th>Estado</th>
                    <th>País</th>
                    <th>CEP</th>
                    <th>Obs.</th>
                </tr>
                </thead>

                <tbody>
                <%
                    int i = 0;
                    if(result.hasEntities() && result.getKeys().contains(DeliveryAddress.class.getSimpleName())){
                        for(Entity entity : result.getEntities(DeliveryAddress.class.getSimpleName())){
                            DeliveryAddress deliveryAddress = (DeliveryAddress) entity;

                            out.println("<tr>");
                            out.println("<td>" + deliveryAddress.getAlias() + "</td>");
                            out.println("<td>" + deliveryAddress.getPublicPlaceType() + "</td>");
                            out.println("<td>" + deliveryAddress.getPublicPlace() + "</td>");
                            out.println("<td>" + deliveryAddress.getNumber() + "</td>");
                            out.println("<td>" + deliveryAddress.getDistrict() + "</td>");
                            out.println("<td>" + deliveryAddress.getCity() + "</td>");
                            out.println("<td>" + deliveryAddress.getHomeType() + "</td>");
                            out.println("<td>" + deliveryAddress.getState().getName() + "</td>");
                            out.println("<td>" + deliveryAddress.getState().getCountry().getName() + "</td>");
                            out.println("<td>" + deliveryAddress.getPostalCode() + "</td>");
                            out.println("<td>" + deliveryAddress.getObservation() == null ? "-" : deliveryAddress.getObservation() + "</td>");
                            out.println("</tr>");
                            i++;
                        }
                    } else {
                        out.println("<tr>");

                        for(int j = 0; j <= 10; j++){
                            out.println("<td> - </td>");
                        }

                        out.println("</tr>");
                    }
                %>
                </tbody>

                <tfoot>
                <tr>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <td><b><% out.println(i); %>registros encontrados.</b></td>
                </tr>
                </tfoot>
            </table>

            <a class="pull-right button" href='<% out.print(request.getContextPath().concat("/delivery-addresses/create?operation=CREATE")); %>'>Novo Endereço de Entrega</a>
        </div>
    </div>
    <%
        }
    %>
</div>

</body>
</html>