<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="br.com.talles.ecommercebooks.controll.Result" %>
<%@ page import="br.com.talles.ecommercebooks.domain.Entity" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="br.com.talles.ecommercebooks.domain.sale.OrderRequest" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <title>Meus Pedidos</title>

    <%@include file="../commons/customer/menu-css.jsp"%>
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
<%@include file="../commons/customer/menu-html.jsp"%>

<div class="container">
    <h1 id="list-orderRequest">Seus Pedidos</h1>

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
                    int i = 0, k = 1;
                    if(result.hasEntities() && result.getKeys().contains(OrderRequest.class.getSimpleName())){
                        for(Entity entity : result.getEntities(OrderRequest.class.getSimpleName())){
                            OrderRequest orderRequest = (OrderRequest) entity;

                            out.println("<tr>");
                            out.println("<td>" + orderRequest.getStatus().getName() + "</td>");
                            out.println("<td>" + orderRequest.getDate().toString().replace("-","/") + "</td>");
                            out.println("<td>" + orderRequest.getSaleNumber() + "</td>");
                            out.println("<td>" + formatter.format( orderRequest.getPrice() ) + "</td>");
                            out.println("<td>"
                                    + "<a id='show" + k + "' href='" + request.getContextPath() + "/orders/find?operation=FIND&id=" + orderRequest.getId() + "'>"
                                    + "<i class='fa fa-eye' aria-hidden='true'></i>"
                                    + "</a>"
                                    + "</td>");
                            out.println("</tr>");
                            i++;
                            k++;
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
        </div>
    </div>
    <%
        }
    %>
</div>

</body>
</html>
