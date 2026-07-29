package formularios;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import extra_edicion.TableActionCellEditor;
import extra_edicion.TableActionCellRenderer;
import extra_edicion.TableActionEvent;
import clases.Docente;
import principales.Conexion;

public class docentes extends javax.swing.JFrame {

    private DefaultTableModel modeloTabla;
    private Connection conex;

    public docentes() {
        initComponents();
        modeloTabla = (DefaultTableModel) TablaDocentes.getModel();

        // Columnas ocultas (6 a 13) con los datos que la tabla no muestra
        // pero que EditarDocente necesita para poder editar.
        modeloTabla.addColumn("id_docente");        // 6
        modeloTabla.addColumn("nombre_docente");     // 7
        modeloTabla.addColumn("apellido_docente");   // 8
        modeloTabla.addColumn("domicilio_docente");  // 9
        modeloTabla.addColumn("fecha_nacimiento");   // 10
        modeloTabla.addColumn("fecha_alta");         // 11
        modeloTabla.addColumn("nombre_sexo");        // 12
        modeloTabla.addColumn("nombre_provincia");   // 13

        for (int i = 13; i >= 6; i--) {
            TablaDocentes.getColumnModel().removeColumn(
                TablaDocentes.getColumnModel().getColumn(i)
            );
        }

        try {
            conex = Conexion.conectar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectar: " + e.getMessage());
        }

        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                int idDocente = (int) modeloTabla.getValueAt(row, 6);
                String nombre = modeloTabla.getValueAt(row, 7).toString();
                String apellido = modeloTabla.getValueAt(row, 8).toString();
                String dni = modeloTabla.getValueAt(row, 1).toString();
                String cuil = modeloTabla.getValueAt(row, 2).toString();
                String domicilio = modeloTabla.getValueAt(row, 9).toString();
                String fechaNac = modeloTabla.getValueAt(row, 10).toString();
                String fechaAlta = modeloTabla.getValueAt(row, 11).toString();
                String fechaBaja = ""; // mostrarTodos() no trae este dato; ver nota arriba
                String telefono = modeloTabla.getValueAt(row, 4).toString();
                String nombreSexo = modeloTabla.getValueAt(row, 12).toString();
                String provincia = modeloTabla.getValueAt(row, 13).toString();
                String localidad = modeloTabla.getValueAt(row, 3).toString();

                int sexoIndex = obtenerIndiceSexo(nombreSexo);

                EditarDocente formEditar = new EditarDocente(conex, idDocente, apellido, nombre, dni, cuil,
                        fechaNac, fechaAlta, fechaBaja, domicilio, telefono, sexoIndex, provincia, localidad);
                formEditar.setLocationRelativeTo(null);
                formEditar.setVisible(true);
                formEditar.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent e) {
                        cargarDocentesDesdeBD();
                    }
                });
            }

            @Override
            public void onDelete(int row) {
                if (TablaDocentes.isEditing()) {
                    TablaDocentes.getCellEditor().stopCellEditing();
                }
                int idDocente = (int) modeloTabla.getValueAt(row, 6);
                String nombreCompleto = modeloTabla.getValueAt(row, 0).toString();

                int confirmacion = JOptionPane.showConfirmDialog(null,
                        "¿Eliminar al docente " + nombreCompleto + "?",
                        "Confirmar", JOptionPane.YES_NO_OPTION);

                if (confirmacion == JOptionPane.YES_OPTION) {
                    try {
                        Docente.eliminarDocenteLogico(conex, idDocente);
                        cargarDocentesDesdeBD();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "No se pudo eliminar: " + e.getMessage());
                    }
                }
            }

            @Override
            public void onView(int row) {
                String nombreCompleto = modeloTabla.getValueAt(row, 0).toString();
                String dni = modeloTabla.getValueAt(row, 1).toString();
                String cuil = modeloTabla.getValueAt(row, 2).toString();
                String localidad = modeloTabla.getValueAt(row, 3).toString();
                String telefono = modeloTabla.getValueAt(row, 4).toString();
                String domicilio = modeloTabla.getValueAt(row, 9).toString();
                String fechaNac = modeloTabla.getValueAt(row, 10).toString();

                String mensaje = "Nombre: " + nombreCompleto + "\n" +
                        "DNI: " + dni + "\n" +
                        "CUIL: " + cuil + "\n" +
                        "Domicilio: " + domicilio + "\n" +
                        "Localidad: " + localidad + "\n" +
                        "Teléfono: " + telefono + "\n" +
                        "Fecha de nacimiento: " + fechaNac;

                JOptionPane.showMessageDialog(null, mensaje, "Detalle del Docente",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        };

        TablaDocentes.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRenderer());
        TablaDocentes.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(event));
        cargarDocentesDesdeBD();
    }

    // Reproduce el mismo orden con el que EditarDocente arma su combo de sexo,
    // para poder pre-seleccionar el valor correcto al editar.
    private int obtenerIndiceSexo(String nombreSexo) {
        int index = 0; // 0 = "Seleccione Sexo" si no se encuentra
        try {
            ResultSet rs = Docente.mostrarSexo(conex);
            int i = 1;
            while (rs.next()) {
                if (rs.getString("nombre_sexo").equalsIgnoreCase(nombreSexo)) {
                    index = i;
                    break;
                }
                i++;
            }
        } catch (Exception e) {
            System.out.println("Error al calcular índice de sexo: " + e.getMessage());
        }
        return index;
    }

    private void cargarDocentesDesdeBD() {
        modeloTabla.setRowCount(0);

        try {
            ResultSet rs = Docente.mostrarTodos(conex);

            while (rs.next()) {
                String apellido = rs.getString("apellido_docente");
                String nombre = rs.getString("nombre_docente");

                Object[] fila = new Object[]{
                    apellido + " " + nombre,
                    rs.getString("dni_docente"),
                    rs.getString("cuil_docente"),
                    rs.getString("nombre_localidad"),
                    rs.getString("telefono_docente"),
                    "",                                        // Acción
                    rs.getInt("id_docente"),
                    nombre,
                    apellido,
                    rs.getString("domicilio_docente"),
                    rs.getString("fecha_nacimiento_docente"),
                    rs.getString("fecha_alta_docente"),
                    rs.getString("nombre_sexo"),
                    rs.getString("nombre_provincia")
                };
                modeloTabla.addRow(fila);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar docentes: " + e.getMessage());
        }
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaDocentes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel9.setText("Docentes");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1.setText("+ Nuevo Docente");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Materias");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("Docentes");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setText("Comisiones");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setText("Carreras");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setText("Horarios");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setText("Usuarios");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setText("Designaciones");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(75, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(186, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(196, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(584, Short.MAX_VALUE)))
        );

        TablaDocentes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Apellido y Nombre", "DNI", "CUIL", "Localidad", "Telefono", "Accion"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaDocentes.setRowHeight(40);
        jScrollPane1.setViewportView(TablaDocentes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 967, Short.MAX_VALUE)
                        .addGap(12, 12, 12))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(522, 522, 522))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 671, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        EditarDocente formNuevo = new EditarDocente(conex);
        formNuevo.setLocationRelativeTo(null);
        formNuevo.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                cargarDocentesDesdeBD();
            }
        });
        formNuevo.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(docentes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(docentes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(docentes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(docentes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new docentes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaDocentes;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
