
public class Tablero {

    public static String pintarTablero(int rondaPartida, String rondaCompleta) {
        String tableroSinCerrar = "";
        int CANTIDAD_FICHAS = 4;

        if (rondaPartida == 0) {
            return rondaCompleta;
        }
        for (int i = rondaPartida; i > 0; i--) {
            tableroSinCerrar = tableroSinCerrar + "<tr>";
            //si son iguales pintamos la rondaCompleta en el tablero
            if (i == rondaPartida) {
                tableroSinCerrar = tableroSinCerrar + rondaCompleta;

            }
            for (int j = (CANTIDAD_FICHAS + 2); j > 0; j--) {
                if (j == 2) {
                    tableroSinCerrar = tableroSinCerrar + "<td class='td5'></td>";
                } else {
                    tableroSinCerrar = tableroSinCerrar + "<td></td>";
                }
            }

            tableroSinCerrar = tableroSinCerrar + "</tr>";

        }

        return tableroSinCerrar;
    }

    public static String crearStringPintar(int[] miArray, String cadenaFinal) {
        for (Object elem : miArray) {
            int elemento = (int) elem;
            switch (elemento) {
                case 1:
                    cadenaFinal = cadenaFinal + "<td><div class=\"cl1\"></div></td>";
                    break;
                case 2:
                    cadenaFinal = cadenaFinal + "<td><div class=\"cl2\"></div></td>";

                    break;
                case 3:
                    cadenaFinal = cadenaFinal + "<td><div class=\"cl3\"></div></td>";
                    break;
                case 4:
                    cadenaFinal = cadenaFinal + "<td><div class=\"cl4\"></div></td>";
                    break;
                case 5:
                    cadenaFinal = cadenaFinal + "<td><div class=\"cl5\"></div></td>";
                    break;

                case 6:
                    cadenaFinal = cadenaFinal + "<td><div class=\"cl6\"></div></td>";
                    break;
            }

        }
        //pintar borde negro entre
        // cadenaFinal=cadenaFinal+"<td class='td5'></td>";
        return cadenaFinal;

    }

}
