<%@ page import="br.com.talles.ecommercebooks.domain.Entity" %>
<%@ page import="br.com.talles.ecommercebooks.controll.Result" %>
<%@ page import="br.com.talles.ecommercebooks.domain.customer.CreditCard" %>
<%@ page import="br.com.talles.ecommercebooks.domain.customer.Customer" %>
<%@ page import="br.com.talles.ecommercebooks.domain.sale.*" %>

<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,300italic,700,700italic">
    <link rel="stylesheet" href="https://cdn.rawgit.com/necolas/normalize.css/master/normalize.css">
    <link rel="stylesheet" href="https://cdn.rawgit.com/milligram/milligram/master/dist/milligram.min.css">
</head>
<body>
    <%
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        Result result = (Result) request.getAttribute("result");

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
    <div class="container">
        <div class="row">
            <div class="column">
                <h1 id="create-sale">Finalizar Compras!</h1>
                <form action="<% out.print("save"); %>" method="POST" >
                    <fieldset>
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
                        <label for="idCreditCard">Cartão de Crédito*: </label>
                        <select name="idCreditCard" id="idCreditCard">
                            <%
                                for(Entity entity : result.getEntities(CreditCard.class.getSimpleName())){
                                    CreditCard creditCard = (CreditCard) entity;
                                    out.print("<option value='" + creditCard.getId() + "'>" + creditCard.getNumber() + "</option>");
                                }
                            %>
                        </select>

                        <%
                            Cart cart = (Cart) request.getSession().getAttribute("cart");
                            out.println("<input id='amount' type='hidden' value='" + cart.getTotalAmount() + "' />");
                        %>
                    </fieldset>

                    <input type="hidden" name="operation" id="operation-sale" value="SAVE" />
                    <button type="submit">Concluir</button>
                </form>
            </div>
            <div class="column">
                <%
                    out.println("<p id='subtotalSale'>Subtotal: " + formatter.format(cart.getPrice()) + "</p>");
                    out.println("<p id='shippingCostSale'>Frete: </p>");
                    out.println("<p id='totalSale'>Total: </p>");
                %>
            </div>
        </div>
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

            var subtotal = parseFloat( $("#subtotalSale").text().replace(/[A-Za-z: R$]*/, "").replace(",", ".") );
            var total = parseFloat(totalShippingCost + subtotal).toFixed(2);
            var textTotal = "Total: R$ " + total;

            $("#shippingCostSale").text(textShippingCost);
            $("#totalSale").text(textTotal);
            $("#total").val(total);
            $("#shippingCost").val(totalShippingCost);
        }

        $(function() {
            var firstAddress = $("#idDeliveryAddress option:first-child");
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
