<%-- 
    Document   : derrota
    Created on : 9 nov 2022, 13:49:27
    Author     : charl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MASTERMIND</title>
        <!-- CSS only -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <h1 class="text-center text-danger my-5">Se te acabaron los intentos</h1>
        <%
            out.print("<h2 class=\"text-center\">Su puntuación ha sido: " + session.getAttribute("puntuacion").toString() + "</h2>");
        %>
        <br><br>
        <h1 class="text-center text-danger my-5">Jugada de la máquina</h1>
        <table border="1" class="mx-auto my-5">
            <%
                out.print(session.getAttribute("pintarJugadaMaquina").toString());
            %> 
        </table>
        <table border="1" class="mx-auto my-5">
            <%
                String[] pintarRanking = new String[10];

                pintarRanking = (String[]) session.getAttribute("ranking");
                if (pintarRanking != null) {
                    for (Object elem : pintarRanking) {
                        out.print(elem);
                    }
                }
            %>

        </table>
    </body>
</html>
