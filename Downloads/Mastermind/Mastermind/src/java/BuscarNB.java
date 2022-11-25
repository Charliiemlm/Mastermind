
import java.util.Arrays;


public class BuscarNB {

    public static String buscarFichasNegras(int[] arrayJugada, int[] arrayMaquina) {
        

        String aciertoColoryPosicion = "<td class='td5'>";
        // este for compara si el jugador ha acertado el color y la posición.
        int[] aux1 = Arrays.copyOf(arrayJugada, arrayJugada.length);
        int[] aux2 = Arrays.copyOf(arrayMaquina, arrayMaquina.length);
        int i = 0;

        while (i < aux1.length) {
            int j = 0;
            while (j < aux1.length) {
                if (aux1[i] == aux2[j] && i == j) {
                    aciertoColoryPosicion = aciertoColoryPosicion + "<div  class=\"black\"></div>";
                    aux1[i] = 0;
                    aux2[j] = -1;
                }
                j++;
            }
            i++;
        }

        //aux1 = arrayJugada;
        //aux2 = arrayMaquina;
        System.out.println(arrayJugada[0]+arrayJugada[1]+arrayJugada[2]+arrayJugada[3]);
        System.out.println(arrayMaquina[0]+arrayMaquina[1]+arrayMaquina[2]+arrayMaquina[3]);

        return aciertoColoryPosicion + "</td>";
    }

    public static String buscarFichasBlancas(int[] arrayJugada, int[] arrayMaquina) {
        String aciertoColoryPosicion = "<td>";
        // este for compara si el jugador ha acertado el color y la posición.
        int[] aux1 = Arrays.copyOf(arrayJugada, arrayJugada.length);
        int[] aux2 = Arrays.copyOf(arrayMaquina, arrayMaquina.length);
        int i = 0;

        while (i < aux1.length) {
            int j = 0;
            while (j < aux2.length) {
                if (aux1[i] == aux2[j] && i == j) {
                    aux1[i] = 0;
                    aux2[j] = -1;
                }
                j++;
            }
            i++;
        }

        i = 0;

        while (i < aux1.length) {
            int j = 0;
            while (j < aux2.length) {
                if (aux1[i] == aux2[j] && i != j) {
                    aciertoColoryPosicion = aciertoColoryPosicion + "<div  class=\"white\"></div>";
                    aux1[i] = 0;
                    aux2[j] = -1;
                    System.out.println("Ficha blanca, distinto indice");
                }
                j++;
            }
            i++;
        }

        //aux1 = arrayJugada;
        //aux2 = arrayMaquina;

        return aciertoColoryPosicion + "</td><tr></tr>";

    }

}
