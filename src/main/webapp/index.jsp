<%@page import="br.com.talles.ecommercebooks.controll.Result"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Início</title>
    </head>
    <body>
        <h1>Início</h1>
	<%
		Result result = new Result();
		result = (Result) request.getAttribute("result");
		
		if (result != null) {
			out.println("<span id='request' style='display: none;'>true</span>");
			out.println("PASSOU!");
	%>
		
	<%
		}
	%>
		
		<script src="https://code.jquery.com/jquery-3.2.1.min.js"
			integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
			crossorigin="anonymous">
		</script>
		
		<script>
			$(document).ready(function() {
				if ( $("#request").text() =! "true" )
					document.location.href = "/E-CommerceBooks/books/list?operation=LIST";
			});
		</script>
    </body>
</html>
