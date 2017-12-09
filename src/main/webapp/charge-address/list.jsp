<%@ page import="br.com.talles.ecommercebooks.controll.Result" %>
<%@ page import="br.com.talles.ecommercebooks.domain.Entity" %>
<%@ page import="br.com.talles.ecommercebooks.domain.customer.ChargeAddress" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Seus Endereços de Cobrança</title>

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
    <h1 id="list-charge-address">Seus Endereços de Cobrança</h1>

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
                    <th>Tipo Rediência</th>
                    <th>Estado</th>
                    <th>País</th>
                    <th>CEP</th>
                    <th>Obs.</th>
                </tr>
                </thead>

                <tbody>
                <%
                    int i = 0;
                    if(result.hasEntities() && result.getKeys().contains(ChargeAddress.class.getSimpleName())){
                        for(Entity entity : result.getEntities(ChargeAddress.class.getSimpleName())){
                            ChargeAddress chargeAddress = (ChargeAddress) entity;

                            out.println("<tr>");
                            out.println("<td>" + chargeAddress.getAlias() + "</td>");
                            out.println("<td>" + chargeAddress.getPublicPlaceType() + "</td>");
                            out.println("<td>" + chargeAddress.getPublicPlace() + "</td>");
                            out.println("<td>" + chargeAddress.getNumber() + "</td>");
                            out.println("<td>" + chargeAddress.getDistrict() + "</td>");
                            out.println("<td>" + chargeAddress.getCity() + "</td>");
                            out.println("<td>" + chargeAddress.getHomeType() + "</td>");
                            out.println("<td>" + chargeAddress.getState().getName() + "</td>");
                            out.println("<td>" + chargeAddress.getState().getCountry().getName() + "</td>");
                            out.println("<td>" + chargeAddress.getPostalCode() + "</td>");
                            out.println("<td>");
                            out.println(chargeAddress.getObservation() == null ? "-" : chargeAddress.getObservation());
                            out.println("</td>");
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

            <a class="pull-right button" href='<% out.print(request.getContextPath().concat("/charge-addresses/create?operation=CREATE&idCustomer=" + id)); %>'>Novo Endereços de Cobrança</a>
        </div>
    </div>
    <%
        }
    %>
</div>

</body>
</html>
