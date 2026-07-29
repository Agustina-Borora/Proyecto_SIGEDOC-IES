package formularios;

import clases.Docente;
import java.sql.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class EditarDocente extends javax.swing.JFrame {

    private Connection conexion; 
    private int idDocenteAEditar;
    private boolean esEdicion;
 
    public EditarDocente(Connection conexion) {
        initComponents();
        
        this.conexion = conexion;
        this.esEdicion = false;
        inicializarFormulario();
    }

    public EditarDocente(Connection conexion, int idDocente, String apellido, String nombre, String dni, String cuil, 
                         String fechaNac, String fechaAlta, String fechaBaja, String domicilio, String telefono, 
                         int sexo, String provincia, String localidad) {
        initComponents();
        this.conexion = conexion;
        this.idDocenteAEditar = idDocente;
        this.esEdicion = true;
        
        inicializarFormulario();
        cargarDatosDocente(apellido, nombre, dni, cuil, fechaNac, fechaAlta, fechaBaja, domicilio, telefono, sexo, provincia, localidad);
    
    }

    private void inicializarFormulario() {
        
        cargarCombos();
        configurarListaProvincia();
    
    }

    private void cargarDatosDocente(String apellido, String nombre, String dni, String cuil, 
                                                         String fechaNac, String fechaAlta, String fechaBaja, 
                                                         String domicilio, String telefono, int sexo, String provincia, 
                                                         String localidad) {
        
        TXTapellido.setText(apellido);
        txtNombre.setText(nombre);
        txtDNI.setText(dni);
        txtCUIL.setText(cuil);
        txtFechaNacimiento.setText(fechaNac);
        txtFechaAlta.setText(fechaAlta);
        txtFechaBaja.setText(fechaBaja);
        txtDireccion.setText(domicilio);
        txtTelefono.setText(telefono);
        cboSexo.setSelectedIndex(sexo);
        cboProvincia.setSelectedItem(provincia);
        cboLocalidad.setSelectedItem(localidad);
        
        
    }

    private void cargarCombos() {
        
        DefaultComboBoxModel<String> modeloSexo = new DefaultComboBoxModel<>();
        modeloSexo.addElement("Seleccione Sexo");
        
        try (ResultSet rs = Docente.mostrarSexo(conexion)) {
            while (rs != null && rs.next()) {
                modeloSexo.addElement(rs.getString("nombre_sexo"));
            }
        } catch (Exception e) {
            System.out.println("Error al rellenar combo sexo: " + e.getMessage());
        }
        cboSexo.setModel(modeloSexo);

        
        DefaultComboBoxModel<String> modeloProvincia = new DefaultComboBoxModel<>();
        modeloProvincia.addElement("Seleccione Provincia");
        
        try (ResultSet rs = Docente.mostrarProvincias(conexion)) {
            while (rs != null && rs.next()) {
                modeloProvincia.addElement(rs.getString("nombre_provincia"));
            }
        } catch (Exception e) {
            System.out.println("Error al rellenar combo provincia: " + e.getMessage());
        }
        
        cboProvincia.setModel(modeloProvincia);
        
        cboLocalidad.setModel(new DefaultComboBoxModel<String>());
    
    }

    private void configurarListaProvincia() {

        cboProvincia.addActionListener(e -> {
            cargarListaLocalidad((String) cboProvincia.getSelectedItem());

        });
    }

    private void cargarListaLocalidad(String provincia) {
        
        DefaultComboBoxModel<String> listaLocalidad = new DefaultComboBoxModel<>();
        listaLocalidad.addElement("Seleccione Localidad");
        
        if (provincia != null && !provincia.equals("Seleccione Provincia")) {
            try (ResultSet rs = Docente.mostrarLocalidadesPorProvincia(conexion, provincia)) {
                while (rs != null && rs.next()) {
                    listaLocalidad.addElement(rs.getString("nombre_localidad"));
                }
            } catch (Exception e) {
                System.out.println("Error al rellenar localidades: " + e.getMessage());
            }
        }
        cboLocalidad.setModel(listaLocalidad);
        
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TXTapellido = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCUIL = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDNI = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtFechaNacimiento = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtFechaAlta = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtFechaBaja = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        cboSexo = new javax.swing.JComboBox<>();
        cboProvincia = new javax.swing.JComboBox<>();
        cboLocalidad = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setText("Nombre");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel2.setText("Contacto");

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

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setText("Apellido");

        TXTapellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTapellidoActionPerformed(evt);
            }
        });
        TXTapellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTapellidoKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel4.setText("CUIL");

        txtCUIL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCUILActionPerformed(evt);
            }
        });
        txtCUIL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCUILKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setText("DNI");

        txtDNI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDNIActionPerformed(evt);
            }
        });
        txtDNI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDNIKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel6.setText("Fecha de nacimiento");

        txtFechaNacimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaNacimientoActionPerformed(evt);
            }
        });
        txtFechaNacimiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFechaNacimientoKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel7.setText("Sexo");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel8.setText("Fecha de alta");

        txtFechaAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaAltaActionPerformed(evt);
            }
        });
        txtFechaAlta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFechaAltaKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel9.setText("Fecha de baja");

        txtFechaBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaBajaActionPerformed(evt);
            }
        });
        txtFechaBaja.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFechaBajaKeyTyped(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel10.setText("Datos Personales");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel11.setText("Provincia");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel12.setText("Localidad");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel13.setText("Direccion");

        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionKeyTyped(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel14.setText("Telefono");

        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        cboSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboProvincia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboProvincia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboProvinciaActionPerformed(evt);
            }
        });

        cboLocalidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel8)
                                .addComponent(txtFechaAlta, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11)
                                .addComponent(jLabel13)
                                .addComponent(cboProvincia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel1)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtCUIL, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel9)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(cboSexo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtFechaBaja, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)))
                                .addGap(53, 53, 53))
                            .addComponent(cboLocalidad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(292, 292, 292))
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(21, 21, 21)))))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addComponent(TXTapellido, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(286, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(31, 31, 31)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5)
                        .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(277, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addComponent(jLabel10)
                    .addContainerGap(259, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(44, 44, 44)
                    .addComponent(jButton2)
                    .addContainerGap(292, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCUIL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFechaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel11)
                        .addGap(2, 2, 2)
                        .addComponent(cboProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)))
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(37, 37, 37))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(81, 81, 81)
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(TXTapellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(605, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(159, 159, 159)
                    .addComponent(jLabel5)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(527, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jLabel10)
                    .addContainerGap(698, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(659, Short.MAX_VALUE)
                    .addComponent(jButton2)
                    .addGap(39, 39, 39)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void TXTapellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTapellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTapellidoActionPerformed

    private void txtCUILActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCUILActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCUILActionPerformed

    private void txtDNIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDNIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDNIActionPerformed

    private void txtFechaNacimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaNacimientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaNacimientoActionPerformed

    private void txtFechaAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaAltaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaAltaActionPerformed

    private void txtFechaBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaBajaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaBajaActionPerformed

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionActionPerformed

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            String apellido = TXTapellido.getText();
            String nombre = txtNombre.getText();
            String dni = txtDNI.getText();
            String cuil = txtCUIL.getText();
            String fechaNac = txtFechaNacimiento.getText();
            String fechaAlta = txtFechaAlta.getText();
            String fechaBaja = txtFechaBaja.getText();
            String domicilio = txtDireccion.getText();
            String telefono = txtTelefono.getText();
            int sexo = cboSexo.getSelectedIndex();
            
            String nombreLocalidad = (String) cboLocalidad.getSelectedItem();
            int idLocalidad = Docente.obtenerIdLocalidadPorNombre(conexion, nombreLocalidad);

            if (esEdicion) {
                Docente.modificarDocente(conexion, nombre, apellido, dni, cuil, domicilio, telefono, idLocalidad, sexo, fechaNac, fechaAlta, fechaBaja, idDocenteAEditar);
            } else {
                Docente.insertarDocente(conexion, nombre, apellido, dni, cuil, domicilio, telefono, idLocalidad, sexo, fechaNac, fechaAlta, fechaBaja);
            }
            this.dispose(); 
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al procesar la operación: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cboProvinciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboProvinciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboProvinciaActionPerformed

    private void TXTapellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTapellidoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
            javax.swing.JTextField txt = (javax.swing.JTextField) evt.getSource();

            if (!Character.isLetter(c) && c != java.awt.event.KeyEvent.VK_SPACE && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
                evt.consume();
            } else if (txt.getText().length() >= 50 && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
                evt.consume();
            }  
    }//GEN-LAST:event_TXTapellidoKeyTyped

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

    private void txtDNIKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDNIKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        javax.swing.JTextField txt = (javax.swing.JTextField) evt.getSource();

        if (!Character.isDigit(c) && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        } else if (txt.getText().length() >= 8 && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDNIKeyTyped

    private void txtCUILKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCUILKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        javax.swing.JTextField txt = (javax.swing.JTextField) evt.getSource();

        if (!Character.isDigit(c) && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        } else if (txt.getText().length() >= 11 && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCUILKeyTyped

    private void txtFechaNacimientoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaNacimientoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        javax.swing.JTextField txt = (javax.swing.JTextField) evt.getSource();

        if (!Character.isDigit(c) && c != '/' && c != '-' && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        } else if (txt.getText().length() >= 10 && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        }
    }//GEN-LAST:event_txtFechaNacimientoKeyTyped

    private void txtFechaAltaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaAltaKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        javax.swing.JTextField txt = (javax.swing.JTextField) evt.getSource();

        if (!Character.isDigit(c) && c != '/' && c != '-' && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        } else if (txt.getText().length() >= 10 && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        }
    }//GEN-LAST:event_txtFechaAltaKeyTyped

    private void txtFechaBajaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaBajaKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        javax.swing.JTextField txt = (javax.swing.JTextField) evt.getSource();

        if (!Character.isDigit(c) && c != '/' && c != '-' && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        } else if (txt.getText().length() >= 10 && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        }
    }//GEN-LAST:event_txtFechaBajaKeyTyped

    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
        // TODO add your handling code here:
        
        char c = evt.getKeyChar();
    javax.swing.JTextField txt = (javax.swing.JTextField) evt.getSource();
    
    if (txt.getText().length() >= 50 && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
        evt.consume();
    }
    }//GEN-LAST:event_txtDireccionKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        javax.swing.JTextField txt = (javax.swing.JTextField) evt.getSource();

        if (!Character.isDigit(c) && c != '+' && c != '-' && c != ' ' && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        } else if (txt.getText().length() >= 20 && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
            evt.consume();
        }
    }//GEN-LAST:event_txtTelefonoKeyTyped

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
            java.util.logging.Logger.getLogger(EditarDocente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarDocente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarDocente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarDocente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TXTapellido;
    private javax.swing.JComboBox<String> cboLocalidad;
    private javax.swing.JComboBox<String> cboProvincia;
    private javax.swing.JComboBox<String> cboSexo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtCUIL;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtFechaAlta;
    private javax.swing.JTextField txtFechaBaja;
    private javax.swing.JTextField txtFechaNacimiento;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
