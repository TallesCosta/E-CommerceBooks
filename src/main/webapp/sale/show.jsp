<%@ page import="br.com.talles.ecommercebooks.controll.Result" %>
<%@ page import="br.com.talles.ecommercebooks.domain.sale.Sale" %>
<%@ page import="br.com.talles.ecommercebooks.domain.sale.SaleItem" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Exibição de Venda</title>
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

        <div id="app">
            <h1 id="show-sale">Exibição de Venda</h1>

            <div>
                <% out.println("<p>Status: " + sale.getStatus().getName() + "</p>"); %>
            <%
                switch (sale.getStatus().getName()) {
                    case "EM PROCESSAMENTO":
                        out.print("<a class='update-sale' href='" + request.getContextPath().concat("/sales/update?operation=UPDATE&id=" + sale.getId() + "&status=APROVADO") + "'>Aprovar</a><br>");
                        out.print("<a class='update-sale' href='" + request.getContextPath().concat("/sales/update?operation=UPDATE&id=" + sale.getId() + "&status=REPROVADO") + "'>Reprovar</a>");
                        break;
                    case "APROVADO":
                        out.print("<a class='update-sale' href='" + request.getContextPath().concat("/sales/update?operation=UPDATE&id=" + sale.getId() + "&status=EM TRANSITO") + "'>Despachar</a>");
                        break;
                    case "TROCA EM ANÁLISE":
                        out.print("<span>Motivo alegado: " + sale.getExchange().getJustification() + "</span><br>");
                        out.print("<a class='update-sale' href='" + request.getContextPath().concat("/exchanges/update?operation=UPDATE&idSale=" + sale.getId() + "&accepted=true&destination=stock") + "'>Aprovar e voltar ao estoque</a><br>");
                        out.print("<a class='update-sale' href='" + request.getContextPath().concat("/exchanges/update?operation=UPDATE&idSale=" + sale.getId() + "&accepted=true") + "'>Aprovar e descartar os livros</a><br>");
                        out.print("<a class='update-sale' href='" + request.getContextPath().concat("/exchanges/update?operation=UPDATE&idSale=" + sale.getId() + "&accepted=false") + "'>Reprovar solicitação</a>");
                        break;
                }
            %>

                <% out.println("<p>Data da Compra: " + sale.getDate().toString().replace("-","/") + "</p>"); %>
                <% out.println("<p>Previsão de Entrega: " + sale.getDelivery().getDeliveryForecast().toString().replace("-","/") + "</p>"); %>
                <% out.println("<p>Código: " + sale.getSaleNumber() + "</p>"); %>
                <% out.println("<p>Cliente: " + sale.getCustomer().getName() + "</p>"); %>
                <% out.println("<p>Cartão de Crédito: " + sale.getCreditCard().getNumber() + "</p>"); %>
                <hr>
            <%
                for (SaleItem saleItem : sale.getSaleItems()) {
                    out.println("<dt>" + saleItem.getBook().getTitle() + "</dt>");
                    out.println("<dd>Preço Unitário: R$ " + saleItem.getUnitaryPrice() + "</dd>");
                    out.println("<dd>Quantidade: " + saleItem.getAmount() + "</dd>");
                    out.println("<dd>Subtotal: R$ " + saleItem.getAmount() * saleItem.getUnitaryPrice() + "</dd>");
                }
            %>
                <% out.println("<p>Quantidade total: " + sale.getTotalAmount() + "</p>"); %>
                <% out.println("<p>Frete R$: " + sale.getDelivery().getShippingCost().getValue() + "</p>"); %>
                <% out.println("<p>Subtotal Geral R$: " + sale.getPrice() + "</p>"); %>
                <% out.println("<p>Preço Total R$: " + (sale.getPrice() + sale.getDelivery().getShippingCost().getValue()) + "</p>"); %>
            </div>
        <%
            }
        %>

            <a class="list-sale" href="<% out.print(request.getContextPath().concat("/sales/list?operation=LIST")); %>">Voltar</a>
        </div>


        <script src="https://use.fontawesome.com/51922b6b29.js"></script>
    </body>
</html>
