package formularios;

import clases.Materia;
import java.sql.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class FormularioMaterias extends javax.swing.JFrame {

    private Connection conexion; 
    private int idMateriaAEditar;
    private boolean esEdicion = false;

    public FormularioMaterias(Connection conexion) {
        initComponents();
        this.conexion = conexion;
        this.esEdicion = false;
        this.setTitle("Nueva Materia");
        llenarCombos();
    }

    public FormularioMaterias(Connection conexion, int idMateria, String cupofMateria, String nombreMateria, 
                              int cargaHoraria, int anioMateria, String nombreCarrera, String nombreRegimen, String nombreCondicion) {
        initComponents();
        this.conexion = conexion;
        this.idMateriaAEditar = idMateria;
        this.esEdicion = true;
        this.setTitle("Editar Materia");
        llenarCombos();
        
        txtCupof.setText(cupofMateria);
        txtNombre.setText(nombreMateria);
        txtCargaHoraria.setText(String.valueOf(cargaHoraria));
        txtAnio.setText(String.valueOf(anioMateria));
        cboCarrera.setSelectedItem(nombreCarrera);
        cboRegimen.setSelectedItem(nombreRegimen);
        
        if (nombreCondicion.equalsIgnoreCase("Promocional")) {
            chkPromocional.setSelected(true);
            chkRegular.setSelected(false);
        } else {
            chkRegular.setSelected(true);
            chkPromocional.setSelected(false);
        }
    }

    private void llenarCombos() {
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT nombre_carrera FROM carreras WHERE estado_carrera = 1");
            cboCarrera.removeAllItems();
            cboCarrera.addItem("Seleccione Carrera");
            while (rs.next()) {
                cboCarrera.addItem(rs.getString("nombre_carrera"));
            }
            
            rs = st.executeQuery("SELECT nombre_regimen FROM regimen");
            cboRegimen.removeAllItems();
            cboRegimen.addItem("Seleccione Régimen");
            while (rs.next()) {
                cboRegimen.addItem(rs.getString("nombre_regimen"));
            }
        } catch (SQLException e) {
            System.out.println("Error al poblar combos: " + e.getMessage());
        }
    }

    private int obtenerIdCarrera(Connection con, String nombre) {
        String sql = "SELECT id_carrera FROM carreras WHERE nombre_carrera = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("id_carrera");
        } catch (Exception e) {}
        return -1;
    }

    private int obtenerIdRegimen(Connection con, String nombre) {
        String sql = "SELECT id_regimen FROM regimen WHERE nombre_regimen = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("id_regimen");
        } catch (Exception e) {}
        return -1;
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        txtCupof = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtCargaHoraria = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        chkPromocional = new javax.swing.JCheckBox();
        chkRegular = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtAnio = new javax.swing.JTextField();
        cboRegimen = new javax.swing.JComboBox<>();
        cboCarrera = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setText("CUPOF");

        txtCupof.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCupofActionPerformed(evt);
            }
        });
        txtCupof.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCupofKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setText("Nombre de la Materia");

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
        jLabel6.setText("Carga Horaria");

        txtCargaHoraria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCargaHorariaActionPerformed(evt);
            }
        });
        txtCargaHoraria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCargaHorariaKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel9.setText("Regimen");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel10.setText("Datos de la Materia");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel12.setText("Condiciones");

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

        chkPromocional.setText("Promocional");
        chkPromocional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPromocionalActionPerformed(evt);
            }
        });

        chkRegular.setText("Regular");
        chkRegular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRegularActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel7.setText("Carrera");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel8.setText("Año");

        txtAnio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAnioActionPerformed(evt);
            }
        });
        txtAnio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAnioKeyTyped(evt);
            }
        });

        cboRegimen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboCarrera.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboCarrera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCarreraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(chkPromocional)
                .addGap(62, 62, 62)
                .addComponent(chkRegular)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(txtCargaHoraria, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)
                                    .addComponent(cboCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8)
                                    .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboRegimen, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(77, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addComponent(txtCupof, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addContainerGap(235, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(227, 227, 227)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addGap(28, 28, 28))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCargaHoraria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboRegimen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkPromocional)
                    .addComponent(chkRegular))
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addGap(59, 59, 59))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(81, 81, 81)
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtCupof, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(509, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(159, 159, 159)
                    .addComponent(jLabel5)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(431, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jLabel10)
                    .addContainerGap(602, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCupofActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCupofActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCupofActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtCargaHorariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCargaHorariaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCargaHorariaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
  try {
            String cupof = txtCupof.getText();
            String nombre = txtNombre.getText();
            int cargaHoraria = Integer.parseInt(txtCargaHoraria.getText());
            int anio = Integer.parseInt(txtAnio.getText());
            
            int idCarrera = obtenerIdCarrera(conexion, (String) cboCarrera.getSelectedItem());
            int idRegimen = obtenerIdRegimen(conexion, (String) cboRegimen.getSelectedItem());
            
            int idCondicion = chkPromocional.isSelected() ? 2 : 1; 

            if (idCarrera == -1 || idRegimen == -1) {
                JOptionPane.showMessageDialog(this, "Por favor seleccione una Carrera y un Régimen válidos.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (esEdicion) {
                Materia.modificarMateria(conexion, cupof, nombre, cargaHoraria, idRegimen, idCarrera, anio, idCondicion, idMateriaAEditar);
            } else {
                Materia.insertarMateria(conexion, cupof, nombre, cargaHoraria, idRegimen, idCarrera, anio, idCondicion);
            }
            this.dispose();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La carga horaria y el año deben ser números enteros válidos.", "Error de formato", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error de persistencia: " + e.getMessage(), "Error en BD", JOptionPane.ERROR_MESSAGE);
        }
    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void chkPromocionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPromocionalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkPromocionalActionPerformed

    private void chkRegularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRegularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkRegularActionPerformed

    private void txtAnioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAnioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAnioActionPerformed

    private void cboCarreraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCarreraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboCarreraActionPerformed

    private void txtCupofKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCupofKeyTyped
        // TODO add your handling code here:
    char c = evt.getKeyChar();
        javax.swing.JTextField txt = (javax.swing.JTextField) evt.getSource();

        if (!Character.isLetterOrDigit(c) && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        } 
        else if (txt.getText().length() >= 10 && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCupofKeyTyped

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

    private void txtCargaHorariaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCargaHorariaKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        javax.swing.JTextField txt = (javax.swing.JTextField) evt.getSource();

        if (!Character.isDigit(c) && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        } else if (txt.getText().length() >= 4 && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCargaHorariaKeyTyped

    private void txtAnioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnioKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        javax.swing.JTextField txt = (javax.swing.JTextField) evt.getSource();

        if (!Character.isDigit(c) && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        } else if (txt.getText().length() >= 4 && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        }
    }//GEN-LAST:event_txtAnioKeyTyped

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
            java.util.logging.Logger.getLogger(FormularioMaterias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioMaterias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioMaterias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioMaterias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboCarrera;
    private javax.swing.JComboBox<String> cboRegimen;
    private javax.swing.JCheckBox chkPromocional;
    private javax.swing.JCheckBox chkRegular;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtAnio;
    private javax.swing.JTextField txtCargaHoraria;
    private javax.swing.JTextField txtCupof;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
