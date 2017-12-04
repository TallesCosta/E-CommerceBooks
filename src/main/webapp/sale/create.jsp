<%@ page import="br.com.talles.ecommercebooks.domain.Entity" %>
<%@ page import="br.com.talles.ecommercebooks.controll.Result" %>
<%@ page import="java.util.Date" %>
<%@ page import="br.com.talles.ecommercebooks.domain.customer.CreditCard" %>
<%@ page import="br.com.talles.ecommercebooks.domain.customer.Customer" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="br.com.talles.ecommercebooks.domain.sale.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        Result result = new Result();
        result = (Result) request.getAttribute("result");

        Sale sale = new Sale("", new Date(), 0.0, 0, new Status(), new Delivery(),
                new PromotionalCoupon(), new CreditCard(), new Customer(), new ArrayList<SaleItem>());

        if (result != null) {
            if (result.getKeys().contains(Sale.class.getSimpleName())) {
                sale = (Sale) result.getEntities(Sale.class.getSimpleName()).get(0);
            }

            if (result.hasMsg()) {
                String[] msgs = result.getMsg().split("\n");
                out.print("<p>");
                for(String msg : msgs)
                    out.print("<i class='fa fa-times' aria-hidden='true' style='color: #FF0000;'></i> " + msg + "<br/>");
                out.print("</p>");
            }
        }
    %>
    <div id="app">
        <h1 id="create-sale">Finalizar Compras!</h1>

        <form action="<% out.print("save"); %>" method="POST" >
            <fieldset>
                <div>
                    <label for="idDeliveryAddress">Endereço de Entrega*: </label>
                    <select name="idDeliveryAddress" id="idDeliveryAddress">
                        <%
                            int i = 0;
                            for (Entity entity : result.getEntities(Delivery.class.getSimpleName())) {
                                i++;
                                Delivery delivery = (Delivery) entity;
                                out.print("<option data-base-shipping-cost='" + delivery.getShippingCost().getBaseValue() + "' data-base-shipping-cost-item='" + delivery.getShippingCost().getBaseAddtionValue() + "' id='da" + i + "' value='" + delivery.getDeliveryAddress().getId() + "'>" + delivery.getDeliveryAddress().getAlias() + "</option>");
                            }
                        %>
                    </select>
                </div>

                <div>
                    <label for="idCreditCard">Cartão de Crédito*: </label>
                    <select name="idCreditCard" id="idCreditCard">
                        <%
                            for(Entity entity : result.getEntities(CreditCard.class.getSimpleName())){
                                CreditCard creditCard = (CreditCard) entity;
                                out.print("<option value='" + creditCard.getId() + "'>" + creditCard.getNumber() + "</option>");
                            }
                        %>
                    </select>
                </div>

                <%
                    Cart cart = (Cart) request.getSession().getAttribute("cart");
                    out.println("<input id='amount' type='hidden' value='" + cart.getTotalAmount() + "' />");
                    out.println("<input name='shippingCost' id='shippingCost' type='hidden' value='' />");
                    out.println("<input name='total' id='total' type='hidden' value='' />");

                    out.println("<p id='subtotalSale'>Subtotal: " + cart.getPrice() + "</p>");
                    out.println("<p id='shippingCostSale'>Frete: </p>");
                    out.println("<p id='totalSale'>Total: </p>");
                %>
            </fieldset>

            <input type="hidden" name="operation" id="operation-sale" value="SAVE" />
            <button type="submit">Concluir</button>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
            crossorigin="anonymous">
    </script>
    <script>
        function updateValues(options) {
            var base = parseFloat(options.base);
            var addition = parseFloat(options.addition);
            var quantity = parseInt(options.quantity);

            var totalShippingCost = base + (addition * quantity);
            var textShippingCost = "Frete: R$ " + totalShippingCost.toFixed(2);

            var subtotal = parseFloat( $("#subtotalSale").text().replace(/[A-Za-z: ]*/, "") );
            var total = parseFloat(totalShippingCost + subtotal).toFixed(2);
            var textTotal = "Total: R$ " + total;

            $("#shippingCostSale").text(textShippingCost);
            $("#totalSale").text(textTotal);
            $("#total").val(total);
            $("#shippingCost").val(totalShippingCost);
        }

        $(function() {
            var firstAddress = $("#idDeliveryAddress option:first-child")
            updateValues({
                base: firstAddress.data("baseShippingCost"),
                addition: firstAddress.data("baseShippingCostItem"),
                quantity: $("#amount").val()
            });

            $("#idDeliveryAddress").change(function() {
                var selected = $(this).find("option:selected");
                updateValues({
                    base: selected.data("baseShippingCost"),
                    addition: selected.data("baseShippingCostItem"),
                    quantity: $("#amount").val()
                });
            });
        });
    </script>
</body>
</html>
