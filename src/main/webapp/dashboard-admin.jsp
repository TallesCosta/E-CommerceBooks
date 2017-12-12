<%@ page import="br.com.talles.ecommercebooks.controll.Result" %>
<%@ page import="br.com.talles.ecommercebooks.domain.report.salesPerGenders.SalesPerGenders" %>
<%@ page import="br.com.talles.ecommercebooks.domain.report.Report" %>
<%@ page import="br.com.talles.ecommercebooks.domain.Entity" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Início</title>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">

		<%@include file="commons/admin/menu-css.jsp"%>
    </head>
    <body>
		<%@include file="commons/admin/menu-html.jsp"%>
		<%
			Result result = (Result) request.getAttribute("result");
		%>

		<div class="container">
			<h1 id="index">Página Inicial</h1>
			<h3>Seja bem-vindo, administrador!</h3>
		</div>

		<br><br>

		<div style="width: 50%;">
			<canvas id="line-chart" width="800" height="450"></canvas>
		</div>
    </body>

	<script src="https://code.jquery.com/jquery-3.2.1.min.js"
			integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
			crossorigin="anonymous">
	</script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
	<script>
        <%
            String data = "";
            if (result.getEntities(SalesPerGenders.class.getSimpleName()) != null) {
                Entity entity = result.getEntities(SalesPerGenders.class.getSimpleName()).get(0);
                Report report = (Report) entity;
                data = report.getJson();
            }
        %>
        var data = <% out.print(data); %>;
        console.log(data);
        if (data == "") {
            // TODO: Mostrar informação ao usuário.
        } else
            new Chart(document.getElementById("line-chart"), data);
	</script>
</html>
