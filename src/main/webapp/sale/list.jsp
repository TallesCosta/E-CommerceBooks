<%@ page import="br.com.talles.ecommercebooks.controll.Result" %>
<%@ page import="br.com.talles.ecommercebooks.domain.Entity" %>
<%@ page import="br.com.talles.ecommercebooks.domain.sale.Sale" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Listagem de Pedidos</title>
    </head>
    <body>
        <%
            Result result = new Result();
            result = (Result) request.getAttribute("result");

            if (result != null) {
                if (result.hasMsg()) {
                    String[] msgs = result.getMsg().split("\n");
                    out.println("<p>");
                    for(String msg : msgs)
                        out.println("<i class='fa fa-times' aria-hidden='true' style='color: #FF0000;'></i> " + msg + "<br/>");
                    out.println("</p>");
                }
        %>

        <div id="app">
            <h1 id="list-sale">Listagem de Vendas</h1>

            <div>
                <table class='u-full-width'>
                    <thead>
                    <tr>
                        <td>Status</td>
                        <td>Data</td>
                        <td>Código</td>
                        <td>Preço</td>
                        <td>Quantidade</td>
                        <td>Visualizar</td>
                    </tr>
                    </thead>

                    <tbody>
                    <%
                        int i = 0;
                        if(result.hasEntities() && result.getKeys().contains(Sale.class.getSimpleName())){
                            for(Entity entity : result.getEntities(Sale.class.getSimpleName())){
                                Sale sale = (Sale) entity;

                                out.println("<tr>");
                                out.println("<td>" + sale.getStatus().getName() + "</td>");
                                out.println("<td>" + sale.getDate() + "</td>");
                                out.println("<td>" + sale.getSaleNumber() + "</td>");
                                out.println("<td>" + sale.getPrice() + "</td>");
                                out.println("<td>" + sale.getTotalAmount() + "</td>");
                                out.println("<td>"
                                        + "<a href='" + request.getContextPath() + "/sales/find?operation=FIND&id=" + sale.getId() + "'>"
                                        + "<i class='fa fa-eye' aria-hidden='true'></i>"
                                        + "</a>"
                                        + "</td>");
                                out.println("</tr>");
                                i++;
                            }
                        } else {
                            out.println("<tr>");

                            for(int j = 0; j <= 5; j++){
                                out.println("<td> - </td>");
                            }

                            out.println("</tr>");
                        }
                    %>
                    </tbody>

                    <tfooter>
                        <tr>
                            <td>Status</td>
                            <td>Data</td>
                            <td>Código</td>
                            <td>Preço</td>
                            <td>Quantidade</td>
                            <td>Visualizar</td>
                        </tr>
                    </tfooter>
                </table>

                <p><% out.println(i); %> registros encontrados.</p>
            </div>
        <%
            }
        %>

            <a class="dashborad" href="<% out.print(request.getContextPath().concat("/dash-board.jsp")); %>">DashBoard</a>
        </div>

        <script src="https://use.fontawesome.com/51922b6b29.js"></script>
    </body>
</html>
