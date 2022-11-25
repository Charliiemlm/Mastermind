
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Puntuacion {

    public static String[] consultarDatosPartida() {

        String[] pintarRanking = new String[10];
        pintarRanking[0] = "<tr><td>USUARIO</td><td>FECHA</td><td>PUNTUACIÓN</td></tr>";
        int i = 1;

        Connection myConn = null;
        try {
            myConn = ConexionBBDD.initializeDatabase();

            if (myConn != null) {
                System.out.println("conexion establecida");
            }
        } catch (Exception e) {
            System.out.println("no hay conexion");
        }

        try {
            //preparar consulta para sacar los datos de la base de datos

            Statement stmt = myConn.createStatement();
            String consultarTop10 = "(SELECT * FROM puntuaciones ORDER BY puntuacion DESC LIMIT 10) ORDER BY puntuacion DESC;";
            ResultSet resultado = stmt.executeQuery(consultarTop10);
            while (resultado.next()) {
                pintarRanking[i] = "<tr><td>" + resultado.getString("usuario") + "</td><td>" + resultado.getString("fecha") + "</td><td>" + resultado.getString("puntuacion") + "</td></tr>";
                System.out.println(pintarRanking[i]);
                i++;
            }

        } catch (Exception e) {
        }
        return pintarRanking;

    }

    public static void actualizarRanking(String nombre, int puntuacion) {
        Connection myConn = null;
        try {
            myConn = ConexionBBDD.initializeDatabase();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MastermindServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        Statement stmt = null;

        /*INSERTAR DATOS */
        try {
            stmt = myConn.createStatement();

            String introducirPuntuacion = "";
            ResultSet rs = stmt.executeQuery(introducirPuntuacion);
        } catch (SQLException ex) {
            Logger.getLogger(MastermindServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        String introducirPuntuacion = "INSERT into puntuaciones VALUES('" + nombre + "', '" + puntuacion + "', NOW());";

        try {
            stmt.execute(introducirPuntuacion);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
