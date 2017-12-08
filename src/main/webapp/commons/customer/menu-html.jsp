<%@ page import="br.com.talles.ecommercebooks.domain.customer.Customer" %>
<%@ page import="br.com.talles.ecommercebooks.domain.Entity" %>
<%@ page import="br.com.talles.ecommercebooks.controll.Result" %>
<%
    Result result = (Result) request.getAttribute("result");
    if(result.hasEntities() && result.getKeys().contains(Customer.class.getSimpleName())) {
        for(Entity entity : result.getEntities(Customer.class.getSimpleName())) {
            Customer customer = (Customer) entity;
            long id = customer.getId();
%>
<nav>
    <div class="container">
        <div class="item">
            <a class="list-book" href="<% out.print(request.getContextPath().concat("/charge-addresses/list?operation=LIST&idCustomer=" + id)); %>">
                End. Cobrança
            </a>
        </div>
        <div class="item">
            <a class="list-shipping" href="<% out.print(request.getContextPath().concat("/delivery-addresses/list?operation=LIST&idCustomer=" + id)); %>">
                End. Entrega
            </a>
        </div>
        <div class="item">
            <a class="list-cards" href="<% out.print(request.getContextPath().concat("/credit-cards/list?operation=LIST&idCustomer=" + id)); %>">
                Meus Cartões
            </a>
        </div>
    </div>
</nav>
<%
        }
    }
%>