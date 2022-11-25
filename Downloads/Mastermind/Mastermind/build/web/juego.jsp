<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
        <div class="container">
            <h1 class="text-center my-5 text-info">¡MasterMind!</h1>
            <%

                out.print("<h3 class=\"text-danger text-center\">Puntuación actual: " + session.getAttribute("puntuacion").toString() + " pts</h3>");

            %>

            <table border="1" class="mx-auto my-5">
                <tr><td colspan="4" class="cab">TÚ</td><td class="cab td5" colspan="2">CPU</td></tr>     
                <%            out.print("Rondas restantes: " + session.getAttribute("rondasRestantes").toString());

                %>

                <%            out.print(session.getAttribute("tablero").toString());
                %> 

            </table>


            <form action="MastermindServlet" method="post" class="text-center my-5">
                <select name="ficha1" required>
                    <option value="">Seleccione</option>
                    <option value="1" class="rojo">rojo</option>
                    <option value="2" class="azul">azul</option>
                    <option value="3" class="verde">verde</option>
                    <option value="4" class="amarillo">amarillo</option>
                    <option value="5" class="naranja">naranja</option>
                    <option value="6" class="rosa">rosa</option>
                </select>

                <select name="ficha2" required>
                    <option value="">Seleccione</option>
                    <option value="1" class="rojo">rojo</option>
                    <option value="2" class="azul">azul</option>
                    <option value="3" class="verde">verde</option>
                    <option value="4" class="amarillo">amarillo</option>
                    <option value="5" class="naranja">naranja</option>
                    <option value="6" class="rosa">rosa</option>
                </select>

                <select name="ficha3" required>
                    <option value="">Seleccione</option>
                    <option value="1" class="rojo">rojo</option>
                    <option value="2" class="azul">azul</option>
                    <option value="3" class="verde">verde</option>
                    <option value="4" class="amarillo">amarillo</option>
                    <option value="5" class="naranja">naranja</option>
                    <option value="6" class="rosa">rosa</option>
                </select>

                <select name="ficha4" required>
                    <option value="">Seleccione</option>
                    <option value="1" class="rojo">rojo</option>
                    <option value="2" class="azul">azul</option>
                    <option value="3" class="verde">verde</option>
                    <option value="4" class="amarillo">amarillo</option>
                    <option value="5" class="naranja">naranja</option>
                    <option value="6" class="rosa">rosa</option>
                </select>
                <input type="submit" value="Enviar" class="btn btn-info my-5">

            </form>
        </div>
    </body>
</html>
