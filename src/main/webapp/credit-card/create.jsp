<%@ page import="br.com.talles.ecommercebooks.controll.Result" %>
<%@ page import="br.com.talles.ecommercebooks.domain.customer.CardCompany" %>
<%@ page import="br.com.talles.ecommercebooks.domain.Entity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Novo Cartão de Crédito</title>

    <%@include file="../commons/customer/menu-css.jsp"%>
</head>
<body>

<%
    Result result = (Result) request.getAttribute("result");

    if (result != null) {
        String back = (String) request.getAttribute("back");
        if (back == null)
            back = "";
%>
<%@include file="../commons/customer/menu-html.jsp"%>

<div class="container">
    <h1 id="list-credit-cart">Novo Cartão de Crédito</h1>

    <%
        if (result.hasMsg()) {
            String[] msgs = result.getMsg().split("\n");
            out.println("<p>");
            for(String msg : msgs)
                out.println("<i class='fa fa-times' aria-hidden='true' style='color: #FF0000;'></i> " + msg + "<br/>");
            out.println("</p>");
        }
    %>

    <div class="row">
        <div class="column">
            <form action="save" method="post">
                <fieldset>
                    <legend>Cartão de Crédito</legend>
                    <input type="hidden" name="idCustomer" id="idCustomer" value="<% out.print(id); %>" />
                    <div>
                        <label for="cardNumber">Número*: </label>
                        <input name="cardNumber" id="cardNumber" type="text" >
                    </div>
                    <div>
                        <label for="printedName">Nome Impresso*: </label>
                        <input name="printedName" id="printedName" type="text" >
                    </div>
                    <div>
                        <label for="securityCode">Código de Segurança*: </label>
                        <input name="securityCode" id="securityCode" type="text" >
                    </div>
                    <div>
                        <label for="expirationDate">Data Exp.*: </label>
                        <input name="expirationDate" id="expirationDate" type="date" />
                    </div>
                    <div>
                        <input type="hidden" name="idCardCompany" id="idCardCompany" >
                        <label for="cardCompany">Bandeira*: </label>
                        <select name="cardCompany" id="cardCompany">
                            <%
                                for(Entity entity : result.getEntities(CardCompany.class.getSimpleName())){
                                    CardCompany cardCompany = (CardCompany) entity;
                                    out.print("<option value='" + cardCompany.getId() + "'>" + cardCompany.getName() + "</option>");
                                }
                            %>
                        </select>
                    </div>
                </fieldset>

                <input type="hidden" name="back" id="back" value="<% out.print(back); %>" />
                <input type="hidden" name="operation" id="operation-credit-card" value="SAVE" />
                <button id="finish" type="submit">Salvar</button>
                <small>Todos os campos marcados com * são obrigatórios.</small>
            </form>
        </div>
    </div>
</div>
    <%
        }
    %>
</body>
</html>
