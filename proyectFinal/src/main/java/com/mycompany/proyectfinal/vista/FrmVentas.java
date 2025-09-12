package com.mycompany.proyectfinal.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FrmVentas extends javax.swing.JFrame {
    
    public FrmVentas() {
        
        setTitle("Gestión de Ventas");
        setSize(400,200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initComponents();
        
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
        btnFinalizarVenta = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnListaVenta = new javax.swing.JButton();

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

        lblTitulo.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        lblTitulo.setText("Gestion de Ventas");
        lblTitulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblCliente.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        lblCliente.setText("Cliente:");

        lblMedicamento.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        lblMedicamento.setText("Medicamento:");

        lblCantidad.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
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
        txtCantidad.setText("N°");

        btnAgregar.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        tbDetalles.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        tbDetalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Cliente", "Medicamento", "Cantidad", "Subtotal"
            }
        ));
        jScrollPane2.setViewportView(tbDetalles);

        lblTotal.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        lblTotal.setText("Total: $0.0");

        btnFinalizarVenta.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnFinalizarVenta.setText("Finalizar");

        btnCancelar.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnCancelar.setText("Cancelar");

        btnListaVenta.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnListaVenta.setText("Lista de Ventas");
        btnListaVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListaVentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelVentasLayout = new javax.swing.GroupLayout(PanelVentas);
        PanelVentas.setLayout(PanelVentasLayout);
        PanelVentasLayout.setHorizontalGroup(
            PanelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelVentasLayout.createSequentialGroup()
                .addGap(166, 166, 166)
                .addComponent(lblTitulo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(PanelVentasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelVentasLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(PanelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTotal, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelVentasLayout.createSequentialGroup()
                                .addComponent(btnCancelar)
                                .addGap(18, 18, 18)
                                .addComponent(btnFinalizarVenta))))
                    .addGroup(PanelVentasLayout.createSequentialGroup()
                        .addGroup(PanelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelVentasLayout.createSequentialGroup()
                                .addComponent(lblCliente)
                                .addGap(18, 18, 18)
                                .addComponent(cbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelVentasLayout.createSequentialGroup()
                                .addComponent(lblMedicamento)
                                .addGap(18, 18, 18)
                                .addComponent(cbMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelVentasLayout.createSequentialGroup()
                                .addComponent(lblCantidad)
                                .addGap(18, 18, 18)
                                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(PanelVentasLayout.createSequentialGroup()
                        .addComponent(btnAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnListaVenta)))
                .addContainerGap())
        );
        PanelVentasLayout.setVerticalGroup(
            PanelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelVentasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(PanelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCliente)
                    .addComponent(cbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMedicamento)
                    .addComponent(cbMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCantidad)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnListaVenta))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(lblTotal)
                .addGap(18, 18, 18)
                .addGroup(PanelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFinalizarVenta)
                    .addComponent(btnCancelar))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void btnListaVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaVentaActionPerformed
       DefaultTableModel modelo = (DefaultTableModel) tbDetalles.getModel();
       int filas = modelo.getRowCount();

       if(filas == 0) {
           JOptionPane.showMessageDialog(this, "No hay productos agregados");
           return;
       }

       StringBuilder lista = new StringBuilder("Productos agregados:\n");
       for(int i = 0; i < filas; i++) {
           String producto = modelo.getValueAt(i, 0).toString();
           String cantidad = modelo.getValueAt(i, 1).toString();
           lista.append(producto).append(" - Cantidad: ").append(cantidad).append("\n");
       }

       JOptionPane.showMessageDialog(this, lista.toString());
   
    }//GEN-LAST:event_btnListaVentaActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
       String producto = lblMedicamento.getText();
       String cantidad = lblCantidad.getText();

       if(producto.isEmpty() || cantidad.isEmpty()) {
           JOptionPane.showMessageDialog(this, "Por favor ingrese producto y cantidad");
           return;
       }

       // Obtener el modelo de la tabla
       DefaultTableModel modelo = (DefaultTableModel) tbDetalles.getModel();

       // Agregar fila a la tabla (por ahora sin precio ni subtotal)
       modelo.addRow(new Object[]{producto, cantidad});

       // Limpiar campos
       lblMedicamento.setText("");
       lblCantidad.setText("");
   
   
    }//GEN-LAST:event_btnAgregarActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelVentas;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnFinalizarVenta;
    private javax.swing.JButton btnListaVenta;
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
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tbDetalles;
    private javax.swing.JTextField txtCantidad;
    // End of variables declaration//GEN-END:variables

   
}
