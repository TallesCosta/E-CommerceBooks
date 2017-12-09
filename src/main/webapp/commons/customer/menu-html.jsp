<%@ page import="br.com.talles.ecommercebooks.domain.customer.Customer" %>
<%@ page import="br.com.talles.ecommercebooks.domain.Entity" %>
<%
    long id = -1;

    if(request.getParameterMap().containsKey("idCustomer"))
        id = Long.valueOf(request.getParameter("idCustomer"));

    else if(result.hasEntities() && result.getKeys().contains(Customer.class.getSimpleName()))
        for(Entity entity : result.getEntities(Customer.class.getSimpleName())) {
            Customer customer = (Customer) entity;
            id = customer.getId();
        }
%>
<nav>
    <div class="container">
        <%
            if(request.getRequestURL().indexOf("/orders/list") != -1) {
        %>
        <div class="item">
            <a class="list-book" href="<% out.print(request.getContextPath().concat("/orders/list")); %>">
                Voltar
            </a>
        </div>
        <%
            }
        %>
        <div class="item">
            <a class="list-order-request" href="<% out.print(request.getContextPath().concat("/orders/list?operation=LIST")); %>">
                Pedidos
            </a>
        </div>
        <div class="item">
            <a class="list-charge-address" href="<% out.print(request.getContextPath().concat("/charge-addresses/list?operation=LIST&idCustomer=" + id)); %>">
                End. Cobranca
            </a>
        </div>
        <div class="item">
            <a class="list-delivery-address" href="<% out.print(request.getContextPath().concat("/delivery-addresses/list?operation=LIST&idCustomer=" + id)); %>">
                End. Entrega
            </a>
        </div>
        <div class="item">
            <a class="list-credit-card" href="<% out.print(request.getContextPath().concat("/credit-cards/list?operation=LIST&idCustomer=" + id)); %>">
                Meus Cartoes
            </a>
        </div>
        <div class="item">
            <a id="logout" class="logout" href="<% out.print(request.getContextPath() + "/log-out?operation=DELETE"); %>">
                Logout
            </a>
        </div>
    </div>
</nav>