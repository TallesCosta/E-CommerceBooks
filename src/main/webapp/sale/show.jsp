<%@ page import="br.com.talles.ecommercebooks.controll.Result" %>
<%@ page import="br.com.talles.ecommercebooks.domain.sale.Sale" %>
<%@ page import="br.com.talles.ecommercebooks.domain.sale.SaleItem" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Exibição de Venda</title>

        <%@include file="../commons/admin/menu-css.jsp"%>
    </head>
    <body>
        <%
            Result result = new Result();
            result = (Result) request.getAttribute("result");

            Sale sale = new Sale();

            if (result != null) {
                if (result.getKeys().contains(Sale.class.getSimpleName())) {
                    sale = (Sale) result.getEntities(Sale.class.getSimpleName()).get(0);
                }

                if (result.hasMsg()) {
                    String[] msgs = result.getMsg().split("\n");
                    out.println("<p>");
                    for(String msg : msgs)
                        out.println("<i class='fa fa-times' aria-hidden='true' style='color: #FF0000;'></i> " + msg + "<br/>");
                    out.println("</p>");
                }
        %>
        <%@include file="../commons/admin/menu-html.jsp"%>

        <div class="container">
            <div class="row">
                <div class="column">

                    <h1 id="show-sale">Exibição de Venda</h1>

                    <% out.println("<p>Status: " + sale.getStatus().getName() + "</p>"); %>
                    <%
                        switch (sale.getStatus().getName()) {
                            case "EM PROCESSAMENTO":
                                out.print("<a class='button update-sale' href='" + request.getContextPath().concat("/sales/update?operation=UPDATE&id=" + sale.getId() + "&status=APROVADO") + "'>Aprovar</a><br>");
                                out.print("<a class='button button-outline update-sale' href='" + request.getContextPath().concat("/sales/update?operation=UPDATE&id=" + sale.getId() + "&status=REPROVADO") + "'>Reprovar</a>");
                                break;
                            case "APROVADO":
                                out.print("<a class='button update-sale' href='" + request.getContextPath().concat("/sales/update?operation=UPDATE&id=" + sale.getId() + "&status=EM TRANSITO") + "'>Despachar</a>");
                                break;
                            case "TROCA EM ANÁLISE":
                                out.print("<span>Motivo alegado: " + sale.getExchange().getJustification() + "</span><br>");
                                out.print("<a class='button update-sale' href='" + request.getContextPath().concat("/exchanges/update?operation=UPDATE&idSale=" + sale.getId() + "&accepted=true&destination=stock") + "'>Aprovar e voltar ao estoque</a><br>");
                                out.print("<a class='button update-sale' href='" + request.getContextPath().concat("/exchanges/update?operation=UPDATE&idSale=" + sale.getId() + "&accepted=true") + "'>Aprovar e descartar os livros</a><br>");
                                out.print("<a class='button button-outline update-sale' href='" + request.getContextPath().concat("/exchanges/update?operation=UPDATE&idSale=" + sale.getId() + "&accepted=false") + "'>Reprovar solicitação</a>");
                                break;
                        }
                    %>

                    <% out.println("<p><b>Data da Compra:</b> " + sale.getDate().toString().replace("-","/") + "</p>"); %>
                    <% out.println("<p><b>Previsão de Entrega:</b> " + sale.getDelivery().getDeliveryForecast().toString().replace("-","/") + "</p>"); %>
                    <% out.println("<p><b>Código:</b> " + sale.getSaleNumber() + "</p>"); %>
                    <% out.println("<p><b>Cliente:</b> " + sale.getCustomer().getName() + "</p>"); %>
                    <% out.println("<p><b>Cartão de Crédito:</b> " + sale.getCreditCard().getNumber() + "</p>"); %>
                    <hr>
                    <table>
                        <thead>
                            <th>Título</th>
                            <th>Valor Unitário</th>
                            <th>Quantidade</th>
                            <th>Subtotal</th>
                        </thead>
                        <%
                            for (SaleItem saleItem : sale.getSaleItems()) {
                                out.println("<tr><td>" + saleItem.getBook().getTitle() + "</td>");
                                out.println("<td>R$ " + saleItem.getUnitaryPrice() + "</td>");
                                out.println("<td>" + saleItem.getAmount() + "</td>");
                                out.println("<td>R$ " + saleItem.getAmount() * saleItem.getUnitaryPrice() + "</td></tr>");
                            }
                        %>
                        <tfoot>
                            <% out.println("<td><b>Quantidade total:</b> " + sale.getTotalAmount() + "</td>"); %>
                            <% out.println("<td><b>Frete</b> R$: " + sale.getDelivery().getShippingCost().getValue() + "</td>"); %>
                            <% out.println("<td><b>Subtotal Geral</b> R$: " + sale.getPrice() + "</td>"); %>
                            <% out.println("<td><b>Preço Total</b> R$: " + (sale.getPrice() + sale.getDelivery().getShippingCost().getValue()) + "</td>"); %>
                        </tfoot>
                    </table>
                </div>
                <%
                    }
                %>

                <a class="list-sale" href="<% out.print(request.getContextPath().concat("/sales/list?operation=LIST")); %>">Voltar</a>
                </div>
            </div>
        </div>


        <script src="https://use.fontawesome.com/51922b6b29.js"></script>
    </body>
</html>
