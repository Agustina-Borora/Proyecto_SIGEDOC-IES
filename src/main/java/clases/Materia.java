package clases;

import java.sql.*;
import javax.swing.*;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class Materia {

    public static void insertarMateria(Connection conexion, String cupofMateria, String nombreMateria, int cargaHoraria, 
                                       int idRegimen, int idCarrera, int anioMateria, int idCondicion) throws SQLException {
        String sql = "INSERT INTO materias (cupof_materia, nombre_materia, carga_horaria_materia, estado_materia, " +
                     "regimen_materia, id_carrera, anio_materia, id_condicion) VALUES (?, ?, ?, 1, ?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, cupofMateria);
            ps.setString(2, nombreMateria);
            ps.setInt(3, cargaHoraria);
            ps.setInt(4, idRegimen);
            ps.setInt(5, idCarrera);
            ps.setInt(6, anioMateria);
            ps.setInt(7, idCondicion);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Materia insertada correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar la materia: " + e.getMessage(), "ERROR!!!...", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    public static void modificarMateria(Connection conexion, String cupofMateria, String nombreMateria, int cargaHoraria, 
                                        int idRegimen, int idCarrera, int anioMateria, int idCondicion, int idMateria) throws SQLException {
        String sql = "UPDATE materias SET cupof_materia=?, nombre_materia=?, carga_horaria_materia=?, " +
                     "regimen_materia=?, id_carrera=?, anio_materia=?, id_condicion=? WHERE id_materia=?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, cupofMateria);
            ps.setString(2, nombreMateria);
            ps.setInt(3, cargaHoraria);
            ps.setInt(4, idRegimen);
            ps.setInt(5, idCarrera);
            ps.setInt(6, anioMateria);
            ps.setInt(7, idCondicion);
            ps.setInt(8, idMateria);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Materia modificada correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar la materia: " + e.getMessage(), "ERROR!!!...", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    public static void eliminarMateriaLogico(Connection conexion, int idMateria) throws SQLException {
        
        String sql = "UPDATE materias SET estado_materia = 0 WHERE id_materia = ?";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idMateria);
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Materia eliminada correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la materia: " + e.getMessage(), "ERROR!!!...", JOptionPane.ERROR_MESSAGE);
        }
    
    }

    public static ResultSet mostrarTodas(Connection conexion) throws Exception {
   
        ResultSet rs = null;
        String sql = "SELECT m.id_materia, m.cupof_materia, m.nombre_materia, m.carga_horaria_materia, " +
                     "r.nombre_regimen, c.nombre_carrera, m.anio_materia, co.nombre_condicion " +
                     "FROM materias m " +
                     "JOIN regimen r ON m.regimen_materia = r.id_regimen " +
                     "JOIN carreras c ON m.id_carrera = c.id_carrera " +
                     "JOIN condiciones co ON m.id_condicion = co.id_condicion " +
                     "WHERE m.estado_materia = 1 " +
                     "ORDER BY c.nombre_carrera, m.anio_materia, m.nombre_materia";
        
        try {
            PreparedStatement stmt = conexion.prepareStatement(sql);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar las materias", "ERROR!!!...", JOptionPane.ERROR_MESSAGE);
        }
        
        return rs;
    
    }

    public static ResultSet buscarPorNombre(Connection conexion, String nombreMateria) throws Exception {
        ResultSet rs = null;
        String sql = "SELECT m.id_materia, m.cupof_materia, m.nombre_materia, m.carga_horaria_materia, " +
                     "r.nombre_regimen, c.nombre_carrera, m.anio_materia, co.nombre_condicion " +
                     "FROM materias m " +
                     "JOIN regimen r ON m.regimen_materia = r.id_regimen " +
                     "JOIN carreras c ON m.id_carrera = c.id_carrera " +
                     "JOIN condiciones co ON m.id_condicion = co.id_condicion " +
                     "WHERE m.estado_materia = 1 AND m.nombre_materia LIKE '" + nombreMateria + "%' " +
                     "ORDER BY c.nombre_carrera, m.anio_materia, m.nombre_materia";
        
        try {
            PreparedStatement stmt = conexion.prepareStatement(sql);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar materias", "ERROR!!!...", JOptionPane.ERROR_MESSAGE);
        }
        
        return rs;
    
    }

    public static int leerIdPorCupof(Connection conexion, String cupofMateria) throws SQLException {
    
        int id = 0;
        ResultSet rs = null;
        PreparedStatement st = conexion.prepareStatement("SELECT id_materia FROM materias WHERE cupof_materia = ?;");
        st.setString(1, cupofMateria);
        
        try {
            rs = st.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id_materia");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al leer Id de la materia", "ERROR!!!...", ERROR_MESSAGE);
        }
        
        return id;
    
    }

}