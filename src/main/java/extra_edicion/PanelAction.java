
package extra_edicion;

public class PanelAction extends javax.swing.JPanel {

    public PanelAction() {
        initComponents();
    }
    public void initEvent(TableActionEvent event, int row) {
        imgeditar.addActionListener(e -> event.onEdit(row));
        imgeliminar.addActionListener(e -> event.onDelete(row));
        imgver.addActionListener(e -> event.onView(row));
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imgeditar = new extra_edicion.ButtonAction();
        imgeliminar = new extra_edicion.ButtonAction();
        imgver = new extra_edicion.ButtonAction();

        imgeditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/extra_edicion/lapiz.png"))); // NOI18N

        imgeliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/extra_edicion/basura.png"))); // NOI18N
        imgeliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imgeliminarActionPerformed(evt);
            }
        });

        imgver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/extra_edicion/ojo.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(imgeditar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imgver, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imgeliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgeditar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(imgeliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(imgver, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void imgeliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imgeliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_imgeliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private extra_edicion.ButtonAction imgeditar;
    private extra_edicion.ButtonAction imgeliminar;
    private extra_edicion.ButtonAction imgver;
    // End of variables declaration//GEN-END:variables
}
