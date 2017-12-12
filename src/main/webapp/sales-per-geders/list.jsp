<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div style="width: 99%;">
        <canvas id="line-chart" width="800" height="450"></canvas>
    </div>

    <script src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
            crossorigin="anonymous">
    </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
    <script>
        console.log($(json).val())
        var data = {"type":"line","data":{"labels":["Janeiro/17","Fevereiro/17","Março/17","Março/17","Abril/17","Maio/17","Junho/17","Julho/17","Agosto/17","Setembro/17"],"datasets":[{"data":[86,41,20,30,70,41,53,22,78,24],"label":"Masculino","borderColor":"#f26430","fill":false},{"data":[28,35,41,50,63,80,94,24,37,52],"label":"Feminino","borderColor":"#6761a8","fill":false},{"data":[87,30,41,52,65,89,97,42,30,57],"label":"Outros","borderColor":"#009b72","fill":false}]},"options":{"title":{"display":true,"text":"Quantidadedevendasdeacordocomosgêneros(Janeiro/17-Setembro/17)"}}};
        new Chart(document.getElementById("line-chart"), data);
    </script>
</body>
</html>
