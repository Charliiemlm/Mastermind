
import java.io.IOException;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MastermindServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //variables jugada maquina
        int maquinaFicha1, maquinaFicha2, maquinaFicha3, maquinaFicha4;
        //array guardar las jugada usuario
        int[] jugadaMaestraJugador = new int[4];
        //array guardar las jugada maquina
        int[] jugadaMaquina = new int[4];

        //array para pintar la tabla del ranking de jugadores con puntuacion de la base de datos
        String[] pintarRanking = new String[10];

        int ficha1Usuario = 0;
        int ficha2Usuario = 0;
        int ficha3Usuario = 0;
        int ficha4Usuario = 0;

        //contador jugadas
        int contador = 1;
        int rondasRestantes = 0;
        String cadenaFinal = "";
        String fichasNegras = "";
        String fichasBlancas = "";

        String pintarTablero_String = "";

        //victoria
        Boolean victoria = false;

        //puntuacion del jugador:por cada ronda perderá 1000 puntos
        int puntuacion = 0;

        HttpSession sesion = request.getSession(true);

        if (sesion.isNew()) {
            //iniciamos contador
            contador = 9;
            rondasRestantes = 8;
            sesion.setAttribute("rondasRestantes", rondasRestantes);
            sesion.setAttribute("contador", contador);
            sesion.setAttribute("jugadas", cadenaFinal);

            //puntuacion
            sesion.setAttribute("puntuacion", puntuacion);

            //JUGADA DEL JUGADOR 1 (PC)
            int MINIMO = 1;
            int MAXIMO = 6;
            //numero aleatorio para conseguir la jugada de la maquina
            Random aleatorio = new Random();
            maquinaFicha1 = aleatorio.nextInt(MINIMO, MAXIMO);
            Random aleatorio2 = new Random();
            maquinaFicha2 = aleatorio2.nextInt(MINIMO, MAXIMO);
            Random aleatorio3 = new Random();
            maquinaFicha3 = aleatorio3.nextInt(MINIMO, MAXIMO);
            Random aleatorio4 = new Random();
            maquinaFicha4 = aleatorio4.nextInt(MINIMO, MAXIMO);
            //inicializamos 
            String pintarJugadaMaquina = "";
            //guardamos la jugada en un array para pintar los colores
            jugadaMaquina = Jugada.guardarJugadaEnArray(jugadaMaquina, maquinaFicha1, maquinaFicha2, maquinaFicha3, maquinaFicha4);
            //guardamos jugada Array de la maquina para recuperarla siempre
            sesion.setAttribute("arrayJugadaMaquina", jugadaMaquina);
            //pintamos los colores de la jugada de la maquina
            pintarJugadaMaquina = Tablero.crearStringPintar(jugadaMaquina, pintarJugadaMaquina);
            //guardamos jugada pintada para mostrarla cuando sea necesario
            sesion.setAttribute("pintarJugadaMaquina", pintarJugadaMaquina);

            //nombre del jugador
            String nombreUsuario = request.getParameter("nombre");
            sesion.setAttribute("nombre", nombreUsuario);

        } else {
            //recuperamos contador para saber por que ronda vamos
            rondasRestantes = Integer.parseInt(sesion.getAttribute("rondasRestantes").toString());
            contador = Integer.parseInt(sesion.getAttribute("contador").toString());
            //recuperamos la jugada anterior para concatenar la nueva jugada y pintar otro bloque
            cadenaFinal = sesion.getAttribute("jugadas").toString();

            //puntuacion
            puntuacion = Integer.parseInt(sesion.getAttribute("puntuacion").toString());

            //recuperamos la jugada de la maquina
            jugadaMaquina = (int[]) sesion.getAttribute("arrayJugadaMaquina");

            ficha1Usuario = Integer.parseInt(request.getParameter("ficha1"));
            ficha2Usuario = Integer.parseInt(request.getParameter("ficha2"));
            ficha3Usuario = Integer.parseInt(request.getParameter("ficha3"));
            ficha4Usuario = Integer.parseInt(request.getParameter("ficha4"));

            //guardamos la jugada del jugador en un Array que usaremos para compararlo con el de la maquins
            jugadaMaestraJugador = Jugada.guardarJugadaEnArray(jugadaMaestraJugador, ficha1Usuario, ficha2Usuario, ficha3Usuario, ficha4Usuario);

            //comprobar si el jugador ha ganado o se le han acabado los intentos
            victoria = Jugada.comprobarVictoria(jugadaMaestraJugador, jugadaMaquina);

            if (victoria == true) {
                String nombreUsuario = sesion.getAttribute("nombre").toString();
                puntuacion = Integer.parseInt(sesion.getAttribute("puntuacion").toString());
                Puntuacion.actualizarRanking(nombreUsuario, puntuacion);
                pintarRanking = Puntuacion.consultarDatosPartida();
                sesion.setAttribute("ranking", pintarRanking);
                pintarRanking = (String[]) sesion.getAttribute("ranking");
                RequestDispatcher rd = request.getRequestDispatcher("victoria.jsp");
                rd.forward(request, response);
            } else if (rondasRestantes == 0) {
                String nombreUsuario = sesion.getAttribute("nombre").toString();
                //puntuación es 0
                puntuacion = 0;
                sesion.setAttribute("puntuacion", puntuacion);
                Puntuacion.actualizarRanking(nombreUsuario, puntuacion);
                pintarRanking = Puntuacion.consultarDatosPartida();
                sesion.setAttribute("ranking", pintarRanking);
                pintarRanking = (String[]) sesion.getAttribute("ranking");
                //llamada a derrota
                RequestDispatcher rd = request.getRequestDispatcher("derrota.jsp");
                rd.forward(request, response);

            }

            //pintar jugada del jugador
            cadenaFinal = Tablero.crearStringPintar(jugadaMaestraJugador, cadenaFinal);
            //recoger cuantas fichas negras hay
            fichasNegras = BuscarNB.buscarFichasNegras(jugadaMaestraJugador, jugadaMaquina);
            //concatenar con los colores a pintar 
            cadenaFinal = cadenaFinal + fichasNegras;
            //recoger cuantas fichas blancas hay
            fichasBlancas = BuscarNB.buscarFichasBlancas(jugadaMaestraJugador, jugadaMaquina);
            //concatenar el primer bloque completo
            cadenaFinal = cadenaFinal + fichasBlancas;
            //guardar el primer bloque completo en la sesion para recogerlo luego y volver a pintarlo
            sesion.setAttribute("jugadas", cadenaFinal);

            //pintamos el tablero correspondiente con el rondasRestantes
            pintarTablero_String = Tablero.pintarTablero(rondasRestantes, cadenaFinal);
            sesion.setAttribute("tablero", pintarTablero_String);

        }

        switch (contador) {
            case 1:
                puntuacion = puntuacion - 1000;
                sesion.setAttribute("puntuacion", puntuacion);

                //llamar al jsp         
                RequestDispatcher rd = request.getRequestDispatcher("juego.jsp");
                rd.forward(request, response);
                break;
            case 2:
                puntuacion = puntuacion - 1000;
                sesion.setAttribute("puntuacion", puntuacion);

                //llamar al jsp
                rd = request.getRequestDispatcher("juego.jsp");
                rd.forward(request, response);
                break;
            case 3:
                puntuacion = puntuacion - 1000;
                sesion.setAttribute("puntuacion", puntuacion);

                //llamar al jsp
                rd = request.getRequestDispatcher("juego.jsp");
                rd.forward(request, response);
                break;
            case 4:
                puntuacion = puntuacion - 1000;
                sesion.setAttribute("puntuacion", puntuacion);

                //llamar al jsp
                rd = request.getRequestDispatcher("juego.jsp");
                rd.forward(request, response);
                break;
            case 5:
                puntuacion = puntuacion - 1000;
                sesion.setAttribute("puntuacion", puntuacion);

                //llamar al jsp
                rd = request.getRequestDispatcher("juego.jsp");
                rd.forward(request, response);
                break;
            case 6:
                puntuacion = puntuacion - 1000;
                sesion.setAttribute("puntuacion", puntuacion);

                //llamar al jsp
                rd = request.getRequestDispatcher("juego.jsp");
                rd.forward(request, response);
                break;
            case 7:
                puntuacion = puntuacion - 1000;
                sesion.setAttribute("puntuacion", puntuacion);

                //llamar al jsp
                rd = request.getRequestDispatcher("juego.jsp");
                rd.forward(request, response);
                break;

            case 8:
                puntuacion = puntuacion - 1000;
                sesion.setAttribute("puntuacion", puntuacion);

                //llamar al jsp
                rd = request.getRequestDispatcher("juego.jsp");
                rd.forward(request, response);
                break;

            case 9:
                pintarTablero_String = Tablero.pintarTablero(rondasRestantes, cadenaFinal);
                sesion.setAttribute("tablero", pintarTablero_String);
                puntuacion = 8000;
                sesion.setAttribute("puntuacion", puntuacion);
                //llamar al jsp
                rd = request.getRequestDispatcher("juego.jsp");
                rd.forward(request, response);

                break;

        }

        contador--;
        sesion.setAttribute("contador", contador);

        rondasRestantes--;
        sesion.setAttribute("rondasRestantes", rondasRestantes);

      

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
