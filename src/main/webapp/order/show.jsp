<%@ page import="br.com.talles.ecommercebooks.controll.Result" %>
<%@ page import="br.com.talles.ecommercebooks.domain.sale.SaleItem" %>
<%@ page import="br.com.talles.ecommercebooks.domain.sale.OrderRequest" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Exibição de Venda</title>

  <%@include file="../commons/customer/menu-css.jsp"%>
</head>
<body>
<%
  Result result = (Result) request.getAttribute("result");

  OrderRequest orderRequest = new OrderRequest();

  if (result != null) {
    if (result.getKeys().contains(OrderRequest.class.getSimpleName())) {
      orderRequest = (OrderRequest) result.getEntities(OrderRequest.class.getSimpleName()).get(0);
    }

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
  <h1 id="show-orderRequest">Exibição de Pedido</h1>

  <div class="row">

    <div class="column">
      <% out.println("<p>Status: " + orderRequest.getStatus().getName() + "</p>"); %>
      <%
        switch (orderRequest.getStatus().getName()) {
          case "EM TRANSITO":
            out.print("<a class='update-orderRequest' href='" + request.getContextPath().concat("/orders/update?operation=UPDATE&id=" + orderRequest.getId() + "&status=ENTREGUE") + "'>Recebido</a>");
            break;
          case "ENTREGUE":
            out.print("<a class='update-orderRequest' href='" + request.getContextPath().concat("/exchanges/create?operation=CREATE&idSale=" + orderRequest.getId() + "") + "'>Solicitar Troca</a>");
            break;
        }
      %>

      <% out.println("<p>Data: " + orderRequest.getDate().toString().replace("-","/") + "</p>"); %>
      <% out.println("<p>Previsão de Entrega: " + orderRequest.getDelivery().getDeliveryForecast().toString().replace("-","/") + "</p>"); %>
      <% out.println("<p>Código: " + orderRequest.getSaleNumber() + "</p>"); %>
      <% out.println("<p>Cliente: " + orderRequest.getCustomer().getName() + "</p>"); %>
      <% out.println("<p>Cartão de Crédito: " + orderRequest.getCreditCard().getNumber() + "</p>"); %>
      <hr>
      <%
        for (SaleItem saleItem : orderRequest.getSaleItems()) {
          out.println("<dt>" + saleItem.getBook().getTitle() + "</dt>");
          out.println("<dd>Preço Unitário: R$ " + saleItem.getUnitaryPrice() + "</dd>");
          out.println("<dd>Quantidade: " + saleItem.getAmount() + "</dd>");
          out.println("<dd>Subtotal: R$ " + saleItem.getAmount() * saleItem.getUnitaryPrice() + "</dd>");
        }
      %>
      <% out.println("<p>Quantidade total: " + orderRequest.getTotalAmount() + "</p>"); %>
      <% out.println("<p>Frete R$: " + orderRequest.getDelivery().getShippingCost().getValue() + "</p>"); %>
      <% out.println("<p>Subtotal Geral R$: " + orderRequest.getPrice() + "</p>"); %>
      <% out.println("<p>Preço Total R$: " + (orderRequest.getPrice() + orderRequest.getDelivery().getShippingCost().getValue()) + "</p>"); %>
    </div>
    <%
      }
    %>

    <a class="list-sale" href="<% out.print(request.getContextPath().concat("/orders/list?operation=LIST")); %>">Voltar</a>
  </div>
</div>

</body>
</html>
