package com.mycompany.proyectfinal.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FrmVentas extends javax.swing.JFrame {
    
    //DefaultTableModel tablaVenta = new DefaultTableModel();
    
    public FrmVentas() {
        
        setTitle("Gestión de Ventas");
        
        initComponents();
        
        // Creamos las columnas de la tabla 
        /*tablaVenta.addColumn("Cliente");
        tablaVenta.addColumn("Medicamento");
        tablaVenta.addColumn("Cantidad");
        this.tbDetalles.setModel(tablaVenta);*/
        
        
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jCheckBox1 = new javax.swing.JCheckBox();
        PanelVentas = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblCliente = new javax.swing.JLabel();
        lblMedicamento = new javax.swing.JLabel();
        lblCantidad = new javax.swing.JLabel();
        cbCliente = new javax.swing.JComboBox<>();
        cbMedicamento = new javax.swing.JComboBox<>();
        txtCantidad = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDetalles = new javax.swing.JTable();
        lblTotal = new javax.swing.JLabel();
        btnGuardarVenta = new javax.swing.JButton();
        btnCancelarVenta = new javax.swing.JButton();
        btnVolverAlMenu = new javax.swing.JButton();
        lblPrecio = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        btnEditar = new javax.swing.JButton();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jCheckBox1.setText("jCheckBox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestion de Ventas");
        setBackground(new java.awt.Color(51, 255, 204));

        lblTitulo.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Gestion de Ventas");
        lblTitulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblCliente.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        lblCliente.setText("Cliente:");

        lblMedicamento.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        lblMedicamento.setText("Medicamento:");

        lblCantidad.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        lblCantidad.setText("Cantidad:");

        cbCliente.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        cbCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbClienteActionPerformed(evt);
            }
        });

        cbMedicamento.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        cbMedicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMedicamentoActionPerformed(evt);
            }
        });

        txtCantidad.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        txtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActionPerformed(evt);
            }
        });

        btnAgregar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        tbDetalles.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        tbDetalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cliente", "Medicamento", "Cantidad", "Subtotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbDetalles);

        lblTotal.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        lblTotal.setText("Total: $0.0");

        btnGuardarVenta.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnGuardarVenta.setText("Guardar");
        btnGuardarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarVentaActionPerformed(evt);
            }
        });

        btnCancelarVenta.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnCancelarVenta.setText("Cancelar");
        btnCancelarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarVentaActionPerformed(evt);
            }
        });

        btnVolverAlMenu.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnVolverAlMenu.setText("Menu Principal");
        btnVolverAlMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverAlMenuActionPerformed(evt);
            }
        });

        lblPrecio.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        lblPrecio.setText("Precio:");

        txtPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioActionPerformed(evt);
            }
        });

        btnEditar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnEditar.setText("Editar");

        javax.swing.GroupLayout PanelVentasLayout = new javax.swing.GroupLayout(PanelVentas);
        PanelVentas.setLayout(PanelVentasLayout);
        PanelVentasLayout.setHorizontalGroup(
            PanelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelVentasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnVolverAlMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PanelVentasLayout.createSequentialGroup()
                        .addGroup(PanelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblPrecio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCantidad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblMedicamento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                            .addComponent(lblCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(PanelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbCliente, 0, 195, Short.MAX_VALUE)
                            .addComponent(cbMedicamento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCantidad)
                            .addComponent(txtPrecio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(PanelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCancelarVenta, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                            .addComponent(btnGuardarVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        PanelVentasLayout.setVerticalGroup(
            PanelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelVentasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addGap(36, 36, 36)
                .addGroup(PanelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCliente)
                    .addComponent(btnAgregar))
                .addGap(18, 18, 18)
                .addGroup(PanelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMedicamento)
                    .addComponent(cbMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelarVenta))
                .addGap(18, 18, 18)
                .addGroup(PanelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCantidad)
                        .addComponent(btnEditar)))
                .addGap(22, 22, 22)
                .addGroup(PanelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPrecio)
                        .addComponent(btnGuardarVenta)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTotal)
                .addGap(16, 16, 16)
                .addComponent(btnVolverAlMenu)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbClienteActionPerformed
        
    }//GEN-LAST:event_cbClienteActionPerformed

    private void cbMedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMedicamentoActionPerformed
      
    }//GEN-LAST:event_cbMedicamentoActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        
        /*String []agregarDetalle = new String[3];
            agregarDetalle[0] = (String)cbCliente.getSelectedItem();
            agregarDetalle[1] = (String)cbMedicamento.getSelectedItem();
            agregarDetalle[2] = txtCantidad.getText();
            tablaVenta.addRow(agregarDetalle);
            
        
        if(txtCantidad.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "¡ATENCION! Complete todos los campos");   
        }*/
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadActionPerformed

    private void btnGuardarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarVentaActionPerformed

    private void btnVolverAlMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverAlMenuActionPerformed
        FrmMenu frmMenu = new FrmMenu();
        frmMenu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVolverAlMenuActionPerformed

    private void btnCancelarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarVentaActionPerformed

    private void txtPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelVentas;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelarVenta;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardarVenta;
    private javax.swing.JButton btnVolverAlMenu;
    private javax.swing.JComboBox<String> cbCliente;
    private javax.swing.JComboBox<String> cbMedicamento;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblMedicamento;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tbDetalles;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables

   
}
