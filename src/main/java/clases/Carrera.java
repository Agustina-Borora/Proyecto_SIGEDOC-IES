package clases;

import java.sql.*;
import javax.swing.*;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class Carrera {

    public static void insertarCarrera(Connection conexion, String codigoCarrera, String nombreCarrera, int duracionAnios, String relucionCarrera) throws SQLException {
        
        try (PreparedStatement ps = conexion.prepareStatement(
                "INSERT INTO carreras (codigo_carrera, nombre_carrera, duracion_anios_carrera, estado_carrera, relucion_carrera) VALUES (?,?,?,1,?)")) {

            ps.setString(1, codigoCarrera);
            ps.setString(2, nombreCarrera);
            ps.setInt(3, duracionAnios);
            ps.setString(4, relucionCarrera);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Carrera insertada correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar la carrera: " + e.getMessage(), "ERROR!!!...", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    public static void modificarCarrera(Connection conexion, String codigoCarrera, String nombreCarrera, int duracionAnios, String relucionCarrera, int idCarrera) throws SQLException {
        
        try (PreparedStatement ps = conexion.prepareStatement(
                "UPDATE carreras SET codigo_carrera=?, nombre_carrera=?, duracion_anios_carrera=?, relucion_carrera=? WHERE id_carrera=?")) {

            ps.setString(1, codigoCarrera);
            ps.setString(2, nombreCarrera);
            ps.setInt(3, duracionAnios);
            ps.setString(4, relucionCarrera);
            ps.setInt(5, idCarrera);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Carrera modificada correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar la carrera: " + e.getMessage(), "ERROR!!!...", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    public static void eliminarCarreraLogico(Connection conexion, int idCarrera) throws SQLException {
        
        try (PreparedStatement ps = conexion.prepareStatement(
                "UPDATE carreras SET estado_carrera = 0 WHERE id_carrera = ?")) {
            
            ps.setInt(1, idCarrera);
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Carrera eliminada correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la carrera: " + e.getMessage(), "ERROR!!!...", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    public static ResultSet mostrarPorNombre(Connection conexion, String nombreCarrera) throws Exception {
        
        ResultSet rs = null;
        PreparedStatement stmt = conexion.prepareStatement(
                "SELECT id_carrera, codigo_carrera, nombre_carrera, duracion_anios_carrera, relucion_carrera " +
                "FROM carreras WHERE estado_carrera = 1 AND nombre_carrera LIKE '" + nombreCarrera + "%' ORDER BY nombre_carrera");
        
        try {
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar carreras", "ERROR!!!...", JOptionPane.ERROR_MESSAGE);
        }
        return rs;
    }

    public static int leerIdPorCodigo(Connection conexion, String codigoCarrera) throws SQLException {
        
        int id = 0;
        ResultSet rs = null;
        
        PreparedStatement st = conexion.prepareStatement("SELECT id_carrera FROM carreras WHERE codigo_carrera = ?;");
        st.setString(1, codigoCarrera);

        try {
            rs = st.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id_carrera");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al leer Id de la carrera", "ERROR!!!...", ERROR_MESSAGE);
        }

        return id;
        
    }
    
    public static ResultSet mostrarTodasLasCarreras(Connection conexion) throws Exception {
        
        ResultSet rs = null;
        PreparedStatement stmt = conexion.prepareStatement(
                "SELECT id_carrera, codigo_carrera, nombre_carrera, duracion_anios_carrera, relucion_carrera " +
                "FROM carreras WHERE estado_carrera = 1 ORDER BY nombre_carrera");
        
        try {
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar las carreras", "ERROR!!!...", JOptionPane.ERROR_MESSAGE);
        }
    
        return rs;
    
    }
}