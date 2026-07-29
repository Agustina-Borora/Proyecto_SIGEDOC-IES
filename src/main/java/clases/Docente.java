package clases;

import java.sql.*;
import javax.swing.*;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class Docente {

    public static void insertarDocente(Connection conexion, String nombreSolo, String apellidoSolo, String dni, String cuil,
                                       String domicilio, String telefono, Integer idLocalidad, int sexo,
                                       String fechaNac, String fechaAlta, String fechaBaja) throws SQLException {
        
        String sql = "INSERT INTO docentes (nombre_docente, apellido_docente, dni_docente, cuil_docente, " +
                     "domicilio_docente, telefono_docente, id_localidad, id_sexo, fecha_nacimiento_docente, " +
                     "fecha_alta_docente, fecha_baja_docente, estado_docente) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombreSolo);
            ps.setString(2, apellidoSolo);
            ps.setString(3, dni);
            ps.setString(4, cuil);
            ps.setString(5, domicilio);
            ps.setString(6, telefono);

            if (idLocalidad != null) ps.setInt(7, idLocalidad); else ps.setNull(7, Types.INTEGER);

            ps.setInt(8, sexo);

            if (fechaNac != null && !fechaNac.isEmpty()) ps.setString(9, fechaNac); else ps.setNull(9, Types.VARCHAR);
            if (fechaAlta != null && !fechaAlta.isEmpty()) ps.setString(10, fechaAlta); else ps.setNull(10, Types.VARCHAR);
            if (fechaBaja != null && !fechaBaja.isEmpty()) ps.setString(11, fechaBaja); else ps.setNull(11, Types.VARCHAR);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Docente insertado correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar el docente: " + e.getMessage(), "ERROR!!!...", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    public static void modificarDocente(Connection conexion, String nombreSolo, String apellidoSolo, String dni, String cuil,
                                        String domicilio, String telefono, Integer idLocalidad, int sexo, 
                                        String fechaNac, String fechaAlta, String fechaBaja, int idDocente) throws SQLException {

        String sql = "UPDATE docentes SET nombre_docente=?, apellido_docente=?, dni_docente=?, " +
                     "cuil_docente=?, domicilio_docente=?, telefono_docente=?, id_localidad=?, " +
                     "id_sexo=?, fecha_nacimiento_docente=?, fecha_alta_docente=?, fecha_baja_docente=? " +
                     "WHERE id_docente=?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombreSolo);
            ps.setString(2, apellidoSolo);
            ps.setString(3, dni);
            ps.setString(4, cuil);
            ps.setString(5, domicilio);
            ps.setString(6, telefono);

            if (idLocalidad != null && idLocalidad > 0) ps.setInt(7, idLocalidad); else ps.setNull(7, Types.INTEGER);

            ps.setInt(8, sexo);

            if (fechaNac != null && !fechaNac.isEmpty()) ps.setString(9, fechaNac); else ps.setNull(9, Types.VARCHAR);
            if (fechaAlta != null && !fechaAlta.isEmpty()) ps.setString(10, fechaAlta); else ps.setNull(10, Types.VARCHAR);
            if (fechaBaja != null && !fechaBaja.isEmpty()) ps.setString(11, fechaBaja); else ps.setNull(11, Types.VARCHAR);
            
            ps.setInt(12, idDocente);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Docente modificado correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar el docente: " + e.getMessage(), "ERROR!!!...", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    public static void eliminarDocenteLogico(Connection conexion, int idDocente) throws SQLException {
    
        String sql = "UPDATE docentes SET estado_docente = 0 WHERE id_docente = ?";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idDocente);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Docente eliminado correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el docente: " + e.getMessage(), "ERROR!!!...", JOptionPane.ERROR_MESSAGE);
        
        }
    }

    public static ResultSet mostrarTodos(Connection conexion) throws Exception {
        
        ResultSet rs = null;
        String sql = "SELECT d.id_docente, d.nombre_docente, d.apellido_docente, d.dni_docente, " +
                     "d.cuil_docente, d.domicilio_docente, d.fecha_nacimiento_docente, " +
                     "l.nombre_localidad, d.telefono_docente, s.nombre_sexo, d.fecha_alta_docente, p.nombre_provincia " +
                     "FROM docentes d " +
                     "LEFT JOIN localidades l ON d.id_localidad = l.id_localidad " +
                     "LEFT JOIN provincias p ON l.id_provincia = p.id_provincia " +
                     "LEFT JOIN sexos s ON d.id_sexo = s.id_sexo " +
                     "WHERE d.estado_docente = 1";
        
        try {
            PreparedStatement stmt = conexion.prepareStatement(sql);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los docentes", "ERROR!!!...", JOptionPane.ERROR_MESSAGE);
        }
        
        return rs;
    
    }

    public static int leerIdPorDni(Connection conexion, String dniDocente) throws SQLException {
        
        int id = 0;
        ResultSet rs = null;
        PreparedStatement st = conexion.prepareStatement("SELECT id_docente FROM docentes WHERE dni_docente = ?;");
        st.setString(1, dniDocente);
        
        try {
            rs = st.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id_docente");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al leer Id del docente", "ERROR!!!...", ERROR_MESSAGE);
        }
        
        return id;
    }

    public static int obtenerIdLocalidadPorNombre(Connection conexion, String nombreLocalidad) throws SQLException {
        
        int id = 0;
        ResultSet rs = null;
        PreparedStatement ps = conexion.prepareStatement("SELECT id_localidad FROM localidades WHERE nombre_localidad = ?");
        ps.setString(1, nombreLocalidad);
        
        try {
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id_localidad");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar localidad", "ERROR!!!...", ERROR_MESSAGE);
        }
        
        return id;
    
    }

    public static ResultSet mostrarSexo(Connection conexion) throws Exception {
        
        ResultSet rs = null;
        PreparedStatement stmt = conexion.prepareStatement("SELECT nombre_sexo FROM sexos");
        
        try {
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los sexos", "ERROR!!!...", JOptionPane.ERROR_MESSAGE);
        }
        
        return rs;
    
    }

    public static ResultSet mostrarProvincias(Connection conexion) throws Exception {
        
        ResultSet rs = null;
        PreparedStatement stmt = conexion.prepareStatement("SELECT nombre_provincia FROM provincias ORDER BY nombre_provincia");
        
        try {
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar las provincias", "ERROR!!!...", JOptionPane.ERROR_MESSAGE);
        }
        
        return rs;
    }

    public static ResultSet mostrarLocalidadesPorProvincia(Connection conexion, String nombreProvincia) throws Exception {
        
        ResultSet rs = null;
        String sql = "SELECT l.nombre_localidad FROM localidades l " +
                     "JOIN provincias p ON l.id_provincia = p.id_provincia " +
                     "WHERE p.nombre_provincia = ? ORDER BY l.nombre_localidad";
        
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, nombreProvincia);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar las localidades", "ERROR!!!...", JOptionPane.ERROR_MESSAGE);
        }
        
        return rs;
    
    }

}