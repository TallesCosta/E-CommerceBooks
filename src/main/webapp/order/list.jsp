<%@ page import="br.com.talles.ecommercebooks.controll.Result" %>
<%@ page import="br.com.talles.ecommercebooks.domain.Entity" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="br.com.talles.ecommercebooks.domain.sale.OrderRequest" %>
<%@ page import="br.com.talles.ecommercebooks.domain.customer.Customer" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Meus Pedidos</title>

    <%@include file="../commons/admin/menu-css.jsp"%>
</head>
<body>

<%
    Result result = (Result) request.getAttribute("result");
    NumberFormat formatter = NumberFormat.getCurrencyInstance();

    if (result != null) {
        if (result.hasMsg()) {
            String[] msgs = result.getMsg().split("\n");
            out.println("<p>");
            for(String msg : msgs)
                out.println("<i class='fa fa-times' aria-hidden='true' style='color: #FF0000;'></i> " + msg + "<br/>");
            out.println("</p>");
        }
%>

<div class="container">
    <h1 id="list-orderRequest">Seus Pedidos!</h1>

    <div class="row">
        <div class="column">
            <table>
                <thead>
                <tr>
                    <th>Status</th>
                    <th>Data</th>
                    <th>Código</th>
                    <th>Valor</th>
                    <th>Visualizar</th>
                </tr>
                </thead>

                <tbody>
                <%
                    int i = 0;
                    if(result.hasEntities() && result.getKeys().contains(OrderRequest.class.getSimpleName())){
                        for(Entity entity : result.getEntities(OrderRequest.class.getSimpleName())){
                            OrderRequest orderRequest = (OrderRequest) entity;

                            out.println("<tr>");
                            out.println("<td>" + orderRequest.getStatus().getName() + "</td>");
                            out.println("<td>" + orderRequest.getDate().toString().replace("-","/") + "</td>");
                            out.println("<td>" + orderRequest.getSaleNumber() + "</td>");
                            out.println("<td>" + formatter.format( orderRequest.getPrice() ) + "</td>");
                            out.println("<td>"
                                    + "<a href='" + request.getContextPath() + "/orders/find?operation=FIND&id=" + orderRequest.getId() + "'>"
                                    + "<i class='fa fa-eye' aria-hidden='true'></i>"
                                    + "</a>"
                                    + "</td>");
                            out.println("</tr>");
                            i++;
                        }
                    } else {
                        out.println("<tr>");

                        for(int j = 0; j < 5; j++){
                            out.println("<td> - </td>");
                        }

                        out.println("</tr>");
                    }
                %>
                </tbody>

                <tfoot>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><b><% out.println(i); %>registros encontrados.</b></td>
                </tr>
                </tfoot>
            </table>

        <%
            if(result.hasEntities() && result.getKeys().contains(Customer.class.getSimpleName())){
                for(Entity entity : result.getEntities(Customer.class.getSimpleName())) {
                    Customer customer = (Customer) entity;
        %>
            <a href='<% out.print(request.getContextPath().concat("/charge-addresses/list?operation=LIST&idCustomer=") + customer.getId()); %>'>Gerenciar Endereços de Cobrança</a><br><br>
            <a href='<% out.print(request.getContextPath().concat("/delivery-addresses/list?operation=LIST&idCustomer=") + customer.getId()); %>'>Gerenciar Endereços de Entrega</a><br><br>
            <a href='<% out.print(request.getContextPath().concat("/credit-cards/list?operation=LIST&idCustomer=") + customer.getId()); %>'>Gerenciar Cartões de Crédito</a>
        <%
                }
            }
        %>
        </div>
    </div>
    <%
        }
    %>
</div>

<script src="https://use.fontawesome.com/51922b6b29.js"></script>

</body>
</html>
