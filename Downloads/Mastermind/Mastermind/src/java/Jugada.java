
public class Jugada {
    
    
    
    public static int[] guardarJugadaEnArray(int[] guardarJugadaEnArray, int ficha1, int ficha2, int ficha3, int ficha4) {
        guardarJugadaEnArray[0] = ficha1;
        guardarJugadaEnArray[1] = ficha2;
        guardarJugadaEnArray[2] = ficha3;
        guardarJugadaEnArray[3] = ficha4;
        return guardarJugadaEnArray;

    }

    public static Boolean comprobarVictoria(int[] arrayJugada, int[] arrayMaquina) {
        int contador = 0;
        for (int i = 0; i < arrayJugada.length; i++) {
            //System.out.println("Jugada usuario num: "+i+" "+arrayJugada[i]);
            // System.out.println("Jugada maquina num: "+i+" "+arrayMaquina[i]);
            if (arrayJugada[i] == arrayMaquina[i]) {
                contador++;

            }

        }
        if (contador == 4) {
            return true;
        }
        return false;

    }
}
