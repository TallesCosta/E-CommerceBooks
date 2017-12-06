<%@ page import="br.com.talles.ecommercebooks.controll.Result" %>
<%@ page import="br.com.talles.ecommercebooks.domain.Entity" %>
<%@ page import="br.com.talles.ecommercebooks.domain.sale.Sale" %>
<%@ page import="java.text.NumberFormat" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Listagem de Pedidos</title>

        <%@include file="../commons/admin/menu-css.jsp"%>
    </head>
    <body>
        <%@include file="../commons/admin/menu-html.jsp"%>

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
            <h1 id="list-sale">Listagem de Vendas</h1>

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
                            if(result.hasEntities() && result.getKeys().contains(Sale.class.getSimpleName())){
                                for(Entity entity : result.getEntities(Sale.class.getSimpleName())){
                                    Sale sale = (Sale) entity;

                                    out.println("<tr>");
                                    out.println("<td>" + sale.getStatus().getName() + "</td>");
                                    out.println("<td>" + sale.getDate().toString().replace("-","/") + "</td>");
                                    out.println("<td>" + sale.getSaleNumber() + "</td>");
                                    out.println("<td>" + formatter.format( sale.getPrice() ) + "</td>");
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
