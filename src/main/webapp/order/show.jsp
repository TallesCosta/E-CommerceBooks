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
  <div class="row">
    <div class="column">
      <% out.println("<p><b>Data:</b> " + orderRequest.getDate().toString().replace("-","/") + "</p>"); %>
      <% out.println("<p><b>Previsão de Entrega:</b> " + orderRequest.getDelivery().getDeliveryForecast().toString().replace("-","/") + "</p>"); %>
      <% out.println("<p><b>Código:</b> " + orderRequest.getSaleNumber() + "</p>"); %>
      <% out.println("<p><b>Cliente:</b> " + orderRequest.getCustomer().getName() + "</p>"); %>
      <% out.println("<p><b>Quantidade total:<b> " + orderRequest.getTotalAmount() + "</p>"); %>
      <% out.println("<p><b>Subtotal Geral R$:<b> " + (orderRequest.getPrice() - orderRequest.getDelivery().getShippingCost().getValue()) + "</p>"); %>
      <% out.println("<p><b>Frete R$:<b> " + orderRequest.getDelivery().getShippingCost().getValue() + "</p>"); %>
      <% out.println("<p><b>Preço Total R$:<b> " + orderRequest.getPrice() + "</p>"); %>
    </div>
    <div class="column">
        <h1 id="show-orderRequest">Exibição de Pedido</h1>

        <% out.println("<p><b>Status:</b> " + orderRequest.getStatus().getName() + "</p>"); %>
        <%
            switch (orderRequest.getStatus().getName()) {
                case "EM TRANSITO":
                    out.print("<a id='received' class='update-orderRequest' href='" + request.getContextPath().concat("/orders/update?operation=UPDATE&id=" + orderRequest.getId() + "&status=ENTREGUE") + "'>Recebido</a>");
                    break;
                case "ENTREGUE":
                    out.print("<a id='exchange-request' class='update-orderRequest' href='" + request.getContextPath().concat("/exchanges/create?operation=CREATE&idSale=" + orderRequest.getId() + "") + "'>Solicitar Troca</a>");
                    break;
            }
        %>
      <hr>
    </div>
    <%
      }
    %>
  </div>
  <div class="row">
      <div class="column">
          <table>
              <thead>
                  <th>Título</th>
                  <th>Valor Unitário</th>
                  <th>Quantidade</th>
                  <th>Subtotal</th>
              </thead>
                <%
                    for (SaleItem saleItem : orderRequest.getSaleItems()) {
                        out.println("<tr><td>" + saleItem.getBook().getTitle() + "</td>");
                        out.println("<td>R$ " + saleItem.getUnitaryPrice() + "</td>");
                        out.println("<td>" + saleItem.getAmount() + "</td>");
                        out.println("<td>R$ " + saleItem.getAmount() * saleItem.getUnitaryPrice() + "</td></tr>");
                    }
                %>
          </table>

          <a class="list-sale" href="<% out.print(request.getContextPath().concat("/orders/list?operation=LIST")); %>">Voltar</a>
      </div>
  </div>
</div>

</body>
</html>
