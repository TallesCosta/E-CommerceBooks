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

        Order sale = new Order("", new Date(), 0.0, 0, new Status(), new Delivery(), new PromotionalCoupon(),
                new Customer(), new Exchange(), new ArrayList<CreditCard>(), new ArrayList<SaleItem>(), new ArrayList<ExchangeCoupon>());

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
                <form action="save" method="POST" >
                    <fieldset>
                        <label for="idDeliveryAddress">Endereço de Entrega*: </label>
                        <select name="idDeliveryAddress" id="idDeliveryAddress">
                            <%
                                int i = 0;
                                if (result.hasEntities() && result.getKeys().contains(Delivery.class.getSimpleName())) {
                                    for (Entity entity : result.getEntities(Delivery.class.getSimpleName())) {
                                        i++;
                                        Delivery delivery = (Delivery) entity;
                                        out.print("<option data-base-shipping-cost='" + delivery.getShippingCost().getBaseValue() + "' data-base-shipping-cost-item='" + delivery.getShippingCost().getBaseAddtionValue() + "' id='da" + i + "' value='" + delivery.getDeliveryAddress().getId() + "'>" + delivery.getDeliveryAddress().getAlias() + "</option>");
                                    }
                                }
                            %>
                        </select>
                        <%
                            if (result.hasEntities() && result.getKeys().contains(Customer.class.getSimpleName())) {
                                for(Entity entity : result.getEntities(Customer.class.getSimpleName())){
                        %>
                        <a class="pull-right button" href='<% out.print(request.getContextPath().concat("/delivery-addresses/create?operation=CREATE&idCustomer=" + entity.getId() + "&back=http://localhost:8080/E-CommerceBooks/sales/create?operation=CREATE")); %>'>Novo Endereço de Entrega</a>
                        <%
                                }
                            }
                        %>

                        <label>Cartão de Crédito: </label>
                        <span>Divida entre o valor entre os cartões como preferir c:</span>
                        <%
                            if (result.hasEntities() && result.getKeys().contains(CreditCard.class.getSimpleName())) {
                                int k = 0;
                                for (Entity entity : result.getEntities(CreditCard.class.getSimpleName())) {
                                    k++;
                                    CreditCard creditCard = (CreditCard) entity;
                        %>
                        <label for="creditCard<% out.print(k); %>">Cartão <% out.print(k + ": " + creditCard.getNumber()); %></label>
                        <input type="number" step="0.01" name="creditCard<% out.print(k); %>" id="creditCard<% out.print(k); %>" class="creditCard" value="0" placeholder="R$" />
                        <input type="hidden" name="idCreditCard<% out.print(k); %>" id="idCreditCard<% out.print(k); %>" class="idCreditCard" value="<% out.print(creditCard.getId()); %>" />
                        <%
                                }
                            }
                        %>
                        <%
                            if (result.hasEntities() && result.getKeys().contains(Customer.class.getSimpleName())) {
                                for(Entity entity : result.getEntities(Customer.class.getSimpleName())){
                        %>
                        <a class="pull-right button" href='<% out.print(request.getContextPath().concat("/credit-cards/create?operation=CREATE&idCustomer=" + entity.getId() + "&back=http://localhost:8080/E-CommerceBooks/sales/create?operation=CREATE")); %>'>Novo Cartão de Crédito</a>
                        <%
                                }
                            }
                        %>

                        <!--<select name="idCreditCard" id="idCreditCard">
                            <%
                                /*if (result.hasEntities() && result.getKeys().contains(CreditCard.class.getSimpleName())) {
                                    for (Entity entity : result.getEntities(CreditCard.class.getSimpleName())) {
                                        CreditCard creditCard = (CreditCard) entity;
                                        out.print("<option value='" + creditCard.getId() + "'>" + creditCard.getNumber() + "</option>");
                                    }
                                }*/
                            %>
                        </select>-->

                        <label for="promotionalCoupon">Cupom de Descontro: </label>
                        <input type="text" id="promotionalCoupon" name="promotionalCoupon" />
                        <input type="hidden" id="idPromotionalCoupon" name="idPromotionalCoupon" value="0" />
                        <button id="validateCoupon" type="button">Validar</button>

                        <%
                            if (result.hasEntities() && result.getKeys().contains(PromotionalCoupon.class.getSimpleName())) {
                                for (Entity entity : result.getEntities(PromotionalCoupon.class.getSimpleName())) {
                                    PromotionalCoupon promotionalCoupon = (PromotionalCoupon) entity;
                                    out.print("<input type='hidden' id='" + promotionalCoupon.getCode() + "' class='coupons' data-id='" + promotionalCoupon.getId() + "' data-code='" + promotionalCoupon.getCode() + "' data-value='" + promotionalCoupon.getValue() + "' />");
                                }
                            }
                        %>

                        <label for="exchangeCoupon">Cupom de Troca: </label>
                        <select multiple name="exchangeCoupon" id="exchangeCoupon">
                            <%
                                if (result.hasEntities() && result.getKeys().contains(ExchangeCoupon.class.getSimpleName())) {
                                    int j = 0;
                                    for (Entity entity : result.getEntities(ExchangeCoupon.class.getSimpleName())) {
                                        ExchangeCoupon exchangeCoupon = (ExchangeCoupon) entity;
                                        out.print("<option value='" + exchangeCoupon.getId() + "-" + exchangeCoupon.getValue() + "'>" + "Cupon " + ++j + ": R$ " + exchangeCoupon.getValue() + "</option>");
                                    }
                                }
                            %>
                        </select>

                        <%
                            Cart cart = (Cart) request.getSession().getAttribute("cart");
                            out.println("<input id='amount' type='hidden' value='" + cart.getTotalAmount() + "' />");
                        %>
                    </fieldset>

                    <input type="hidden" name="shippingCost" id="shippingCost" />
                    <input type="hidden" name="total" id="total" />
                    <input type="hidden" name="operation" id="operation-sale" value="SAVE" />
                    <button type="submit">Concluir</button>
                </form>
            </div>

            <div class="column">
                <%
                    out.println("<p id='subtotalSale'>Subtotal: " + formatter.format(cart.getPrice()) + "</p>");
                    out.print("<p id='promotionalCoupon'>Desconto: <span id='promotionalCouponValue'>R$ 0.00</span></p>");
                    out.println("<p id='shippingCostSale'>Frete: </p>");
                    out.println("<p id='totalSale'>Total: </p>");
                %>
            </div>
        </div>
    </div>

    <script src="https://use.fontawesome.com/51922b6b29.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
            crossorigin="anonymous">
    </script>
    <script>
        function updateValues(options) {
            var discount = options.discount || "0.00";
            discount = discount.replace(/[A-Za-z: R$]*/, "");

            discount = parseFloat(discount);

            var base = parseFloat(options.base);
            var addition = parseFloat(options.addition);
            var quantity = parseInt(options.quantity);

            var totalShippingCost = base + (addition * quantity);
            var textShippingCost = "Frete: R$ " + totalShippingCost.toFixed(2);

            var subtotal = parseFloat( $("#subtotalSale").text().replace(/[A-Za-z: R$]*/, "").replace(",", ".") );
            var total = ( subtotal - discount + totalShippingCost ).toFixed(2);
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
                    quantity: $("#amount").val(),
                    discount: $("#promotionalCouponValue").text()
                });
            });

            $("#validateCoupon").click(function() {
                var selected = $("#idDeliveryAddress option:selected");
                var discount = 0.00;
                var cupomId = "";
                var $promotionCouponValue = $("#promotionalCouponValue");
                var $promotionCouponInput = $("#promotionalCoupon");
                var $promotionalCoupon = $("#idPromotionalCoupon");

                var c = $promotionCouponInput.val();
                console.log("CLICOU e DIGITOU " + c);

                $(".coupons").each(function (_, obj) {
                    obj = $(obj);
                    console.log(obj);

                    if(obj.data("code") == c) {
                        discount = parseFloat(obj.data("value"));
                        cupomId = ($("#" + c).data("id"));
                    }
                });

                $promotionCouponValue.text("R$ " + discount.toFixed(2));
                $promotionalCoupon.val(cupomId);

                updateValues({
                    base: selected.data("baseShippingCost"),
                    addition: selected.data("baseShippingCostItem"),
                    quantity: $("#amount").val(),
                    discount: $promotionCouponValue.text()
                });
            });
        });
    </script>
</body>
</html>
