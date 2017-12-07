<%@ page import="br.com.talles.ecommercebooks.controll.Result" %>
<%@ page import="br.com.talles.ecommercebooks.domain.sale.SaleItem" %>
<%@ page import="br.com.talles.ecommercebooks.domain.sale.Order" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Solicitação de Troca</title>
</head>
<body>
<%
    Result result = (Result) request.getAttribute("result");
    Order order = new Order();

    if (result != null) {
        if (result.getKeys().contains(Order.class.getSimpleName())) {
            order = (Order) result.getEntities(Order.class.getSimpleName()).get(0);
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

    <div>
        <h1>Dados do Pedido</h1>
        <%
            out.println("<p>Status: " + order.getStatus().getName() + "</p>");
            out.println("<p>Data da Compra: " + order.getDate().toString().replace("-","/") + "</p>");
            out.println("<p>Código: " + order.getSaleNumber() + "</p>");
        %>
        <hr>
        <%
            for (SaleItem saleItem : order.getSaleItems()) {
                out.println("<dt>" + saleItem.getBook().getTitle() + "</dt>");
                out.println("<dd>Preço Unitário: R$ " + saleItem.getUnitaryPrice() + "</dd>");
                out.println("<dd>Quantidade: " + saleItem.getAmount() + "</dd>");
                out.println("<dd>Subtotal: R$ " + saleItem.getAmount() * saleItem.getUnitaryPrice() + "</dd>");
            }

            out.println("<p>Quantidade total: " + order.getTotalAmount() + "</p>");
            out.println("<p>Frete R$: " + order.getDelivery().getShippingCost().getValue() + "</p>");
            out.println("<p>Subtotal Geral R$: " + order.getPrice() + "</p>");
            out.println("<p>Preço Total R$: " + (order.getPrice() + order.getDelivery().getShippingCost().getValue()) + "</p>");
        %>
    </div>

    <hr>

    <div>
        <h1 id="create-exchange">Solicitação de Troca</h1>
        <form action="<% /*out.print(request.getContextPath().concat("/exchanges/save"));*/ %>save" method="post">
            <input type="hidden" name="idSale" id="idSale" value="<% out.print(order.getId()); %>" />
            <label for="justification">Justificativa: </label>
            <textarea name="justification" id="justification" maxlength="255"></textarea>

            <input type="hidden" name="operation" id="operation-exchange" value="SAVE" />
            <button type="submit">Solicitar</button>
        </form>
    </div>
    <%
        }
    %>
</div>
</body>
</html>
