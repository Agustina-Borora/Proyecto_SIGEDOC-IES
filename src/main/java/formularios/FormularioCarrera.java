package formularios;

import clases.Carrera;
import java.sql.*;
import javax.swing.JOptionPane;

public class FormularioCarrera extends javax.swing.JFrame {

    private Connection conexion; 
    private int idCarreraAEditar; 
    private boolean esEdicion = false;

    public FormularioCarrera(Connection conexion) {
        initComponents();
        this.conexion = conexion;
        this.esEdicion = false;
        this.setTitle("Nueva Carrera");
    }

    public FormularioCarrera(Connection conexion, int idCarrera, String codigoCarrera, String nombreCarrera, int duracionAnios, String resolucionCarrera) {
        initComponents();
        this.conexion = conexion;
        this.idCarreraAEditar = idCarrera;
        this.esEdicion = true;
        this.setTitle("Editar Carrera");

        txtCodigo.setText(codigoCarrera);
        txtNombre.setText(nombreCarrera);
        txtDuracion.setText(String.valueOf(duracionAnios));
        txtResolucion.setText(resolucionCarrera);

        cargarTurnosAsociados(this.conexion, idCarrera);
    }

    private void cargarTurnosAsociados(Connection con, int idCarrera) {
        String sql = "SELECT id_turno FROM carrera_turno WHERE id_carrera = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCarrera);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int idTurno = rs.getInt("id_turno");
                    if (idTurno == 1) chkManana.setSelected(true);
                    if (idTurno == 2) chkTarde.setSelected(true);
                    if (idTurno == 3) chkNoche.setSelected(true);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar turnos: " + e.getMessage());
        }
    }

    private void guardarTurnos(Connection con, int idCarrera) throws SQLException {
        String sqlDelete = "DELETE FROM carrera_turno WHERE id_carrera = ?";
        try (PreparedStatement psDel = con.prepareStatement(sqlDelete)) {
            psDel.setInt(1, idCarrera);
            psDel.executeUpdate();
        }

        String sqlInsert = "INSERT INTO carrera_turno (id_carrera, id_turno) VALUES (?, ?)";
        try (PreparedStatement psIns = con.prepareStatement(sqlInsert)) {
            if (chkManana.isSelected()) {
                psIns.setInt(1, idCarrera); psIns.setInt(2, 1); psIns.addBatch();
            }
            if (chkTarde.isSelected()) {
                psIns.setInt(1, idCarrera); psIns.setInt(2, 2); psIns.addBatch();
            }
            if (chkNoche.isSelected()) {
                psIns.setInt(1, idCarrera); psIns.setInt(2, 3); psIns.addBatch();
            }
            psIns.executeBatch(); 
        }
    }                                 


  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtDuracion = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtResolucion = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        chkManana = new javax.swing.JCheckBox();
        chkNoche = new javax.swing.JCheckBox();
        chkTarde = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setText("Codigo de la Carrera");

        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setText("Nombre de la carrera");

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel6.setText("Duracion(años)");

        txtDuracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDuracionActionPerformed(evt);
            }
        });
        txtDuracion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDuracionKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel9.setText("Resolucion");

        txtResolucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtResolucionActionPerformed(evt);
            }
        });
        txtResolucion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtResolucionKeyTyped(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel10.setText("Datos de la Carrera");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel12.setText("Turnos Disponibles");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        chkManana.setText("Mañana");
        chkManana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkMananaActionPerformed(evt);
            }
        });

        chkNoche.setText("Noche");
        chkNoche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkNocheActionPerformed(evt);
            }
        });

        chkTarde.setText("Tarde");
        chkTarde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTardeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txtDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(txtResolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(77, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(chkManana)
                .addGap(44, 44, 44)
                .addComponent(chkTarde)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chkNoche)
                .addGap(106, 106, 106))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(54, 54, 54))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(202, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(31, 31, 31)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(63, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addComponent(jLabel10)
                    .addContainerGap(236, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(227, 227, 227)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtResolucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jLabel12)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkManana)
                    .addComponent(chkNoche)
                    .addComponent(chkTarde))
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(21, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(81, 81, 81)
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(358, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(159, 159, 159)
                    .addComponent(jLabel5)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(280, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jLabel10)
                    .addContainerGap(451, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtDuracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDuracionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDuracionActionPerformed

    private void txtResolucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtResolucionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtResolucionActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     try {
        String codigo = txtCodigo.getText();
        String nombre = txtNombre.getText();
        int duracion = Integer.parseInt(txtDuracion.getText());
        String resolucion = txtResolucion.getText();

        if (esEdicion) {
            Carrera.modificarCarrera(conexion, codigo, nombre, duracion, resolucion, idCarreraAEditar);
            guardarTurnos(conexion, idCarreraAEditar);
            this.dispose();
        } else {
            Carrera.insertarCarrera(conexion, codigo, nombre, duracion, resolucion);

            int nuevoId = Carrera.leerIdPorCodigo(conexion, codigo);

            if (nuevoId == 0) {
                
                return;
            }

            guardarTurnos(conexion, nuevoId);
            this.dispose();
        }

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Por favor, ingresa una duración válida en años.", "Error de formato", JOptionPane.ERROR_MESSAGE);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error en la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void chkMananaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkMananaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkMananaActionPerformed

    private void chkNocheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkNocheActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkNocheActionPerformed

    private void chkTardeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTardeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkTardeActionPerformed

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        javax.swing.JTextField txt = (javax.swing.JTextField) evt.getSource();

        if (!Character.isLetterOrDigit(c) && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        } else if (txt.getText().length() >= 10 && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCodigoKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        javax.swing.JTextField txt = (javax.swing.JTextField) evt.getSource();

        if (!Character.isLetter(c) && c != java.awt.event.KeyEvent.VK_SPACE && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        } else if (txt.getText().length() >= 50 && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        javax.swing.JTextField txt = (javax.swing.JTextField) evt.getSource();

        if (!Character.isDigit(c) && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        } else if (txt.getText().length() >= 4 && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        }
    }//GEN-LAST:event_formKeyTyped

    private void txtResolucionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtResolucionKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        javax.swing.JTextField txt = (javax.swing.JTextField) evt.getSource();

        if (!Character.isDigit(c) && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        } 
        else if (txt.getText().length() >= 10 && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        }
    }//GEN-LAST:event_txtResolucionKeyTyped

    private void txtDuracionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDuracionKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        javax.swing.JTextField txt = (javax.swing.JTextField) evt.getSource();

        if (!Character.isDigit(c) && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        } 
        else if (txt.getText().length() >= 1 && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDuracionKeyTyped

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
            java.util.logging.Logger.getLogger(FormularioCarrera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioCarrera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioCarrera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioCarrera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chkManana;
    private javax.swing.JCheckBox chkNoche;
    private javax.swing.JCheckBox chkTarde;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDuracion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtResolucion;
    // End of variables declaration//GEN-END:variables
}
