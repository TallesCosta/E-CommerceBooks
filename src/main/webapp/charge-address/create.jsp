<%@ page import="br.com.talles.ecommercebooks.controll.Result" %>
<%@ page import="br.com.talles.ecommercebooks.domain.Entity" %>
<%@ page import="br.com.talles.ecommercebooks.domain.customer.Country" %>
<%@ page import="br.com.talles.ecommercebooks.domain.customer.State" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Novo Endereço de Cobrança</title>

    <%@include file="../commons/customer/menu-css.jsp"%>
</head>
<body>

<%
    Result result = (Result) request.getAttribute("result");

    if (result != null) {
%>
<%@include file="../commons/customer/menu-html.jsp"%>

<div class="container">
    <h1 id="list-credit-cart">Novo Endereço de Cobrança</h1>

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
                    <legend>Endereço</legend>
                    <input type="hidden" name="idCustomer" id="idCustomer" value="<% out.print(id); %>" />
                    <div>
                        <label for="homeAlias">Apelido*: </label>
                        <input name="homeAlias" id="homeAlias" type="text">
                    </div>
                    <div>
                        <label for="homeObservation">Observações: </label>
                        <textarea name="homeObservation" id="homeObservation"></textarea>
                    </div>
                    <div>
                        <label for="homePublicPlaceType">Tipo de Logradouro*: </label>
                        <input name="homePublicPlaceType" id="homePublicPlaceType" type="text"
                               placeholder="Rua, Av., Tr., etc.">
                    </div>
                    <div>
                        <label for="homePublicPlace">Logradouro*: </label>
                        <input name="homePublicPlace" id="homePublicPlace" type="text" >
                    </div>
                    <div>
                        <label for="homeNumber">Número*: </label>
                        <input name="homeNumber" id="homeNumber" type="text" >
                    </div>
                    <div>
                        <label for="homeDistrict">Bairro*: </label>
                        <input name="homeDistrict" id="homeDistrict" type="text" >
                    </div>
                    <div>
                        <label for="homePostalCode">CEP*: </label>
                        <input name="homePostalCode" id="homePostalCode" type="text" >
                    </div>
                    <div>
                        <label for="homeHomeType">Tipo de Residência*: </label>
                        <input name="homeHomeType" id="homeHomeType" type="text"
                               placeholder="Casa, Apartamento, etc.">
                    </div>
                    <div>
                        <label for="homeCity">Cidade*: </label>
                        <input name="homeCity" id="homeCity" type="text" >
                    </div>
                    <div>
                        <input type="hidden" name="idHomeState" id="idHomeState" >
                        <label for="homeState">Estado*: </label>
                        <select name="homeState" id="homeState">
                            <%
                                for(Entity entity : result.getEntities(State.class.getSimpleName())){
                                    State state = (State) entity;
                                    out.print("<option value='" + state.getId() + "'>" + state.getName() + "</option>");
                                }
                            %>
                        </select>
                    </div>
                    <div>
                        <input type="hidden" name="idHomeCountry" id="idHomeCountry" >
                        <label for="homeCountry">País*: </label>
                        <select name="homeCountry" id="homeCountry">
                            <%
                                for(Entity entity : result.getEntities(Country.class.getSimpleName())){
                                    Country country = (Country) entity;
                                    out.print("<option value='" + country.getId() + "'>" + country.getName() + "</option>");
                                }
                            %>
                        </select>
                    </div>
                </fieldset>

                <input type="hidden" name="operation" id="operation-charge-address" value="SAVE" />
                <button type="submit">Salvar</button>
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
