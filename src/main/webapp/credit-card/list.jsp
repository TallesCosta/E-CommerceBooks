<%@ page import="br.com.talles.ecommercebooks.controll.Result" %>
<%@ page import="br.com.talles.ecommercebooks.domain.Entity" %>
<%@ page import="br.com.talles.ecommercebooks.domain.customer.CreditCard" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Seus Carteõs de Crédito</title>
</head>
<body>

<%
    Result result = (Result) request.getAttribute("result");

    if (result != null) {
        if (result.hasMsg()) {
            String[] msgs = result.getMsg().split("\n");
            out.println("<p>");
            for(String msg : msgs)
                out.println("<i class='fa fa-times' aria-hidden='true' style='color: #FF0000;'></i> " + msg + "<br/>");
            out.println("</p>");
        }
%>

<div class="container">
    <h1 id="list-credit-cart">Seus Carteõs de Crédito</h1>

    <div class="row">
        <div class="column">
            <table>
                <thead>
                <tr>
                    <th>Nome Impresso</th>
                    <th>Número</th>
                    <th>Cód. Segurança</th>
                    <th>Data de Expiração</th>
                    <th>Bandeira</th>
                </tr>
                </thead>

                <tbody>
                <%
                    int i = 0;
                    if(result.hasEntities() && result.getKeys().contains(CreditCard.class.getSimpleName())){
                        for(Entity entity : result.getEntities(CreditCard.class.getSimpleName())){
                            CreditCard creditCard = (CreditCard) entity;

                            out.println("<tr>");
                            out.println("<td>" + creditCard.getPrintedName() + "</td>");
                            out.println("<td>" + creditCard.getNumber() + "</td>");
                            out.println("<td>" + creditCard.getSecurityCode() + "</td>");
                            out.println("<td>" + creditCard.getExpirationDate() + "</td>");
                            out.println("<td>" + creditCard.getCardCompany().getName() + "</td>");
                            out.println("</tr>");
                            i++;
                        }
                    } else {
                        out.println("<tr>");

                        for(int j = 0; j <= 4; j++){
                            out.println("<td> - </td>");
                        }

                        out.println("</tr>");
                    }
                %>
                </tbody>

                <tfoot>
                <tr>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <td><b><% out.println(i); %>registros encontrados.</b></td>
                </tr>
                </tfoot>
            </table>

            <a href='<% out.print(request.getContextPath().concat("/credit-cards/create?operation=CREATE")); %>'>Novo Endereços de Entrega</a>
        </div>
    </div>
    <%
        }
    %>
</div>

</body>
</html>