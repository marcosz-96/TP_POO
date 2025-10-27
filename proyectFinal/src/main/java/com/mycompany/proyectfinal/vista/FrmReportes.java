package com.mycompany.proyectfinal.vista;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

public class FrmReportes extends javax.swing.JFrame {

    public FrmReportes() {
        
        setTitle("Informes de Ventas");
        
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnVolverAlMenu = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblCantidadVentas = new javax.swing.JLabel();
        txtCantidadVentas = new javax.swing.JTextField();
        txtTotalGenerado = new javax.swing.JTextField();
        lblRegistrosTotales = new javax.swing.JLabel();
        pnlHistorialVentas = new javax.swing.JPanel();
        scrollHistorialVentas = new javax.swing.JScrollPane();
        tblHistorialVentas = new javax.swing.JTable();
        lblTitulo = new javax.swing.JLabel();
        pnlBusquedaHistorial = new javax.swing.JPanel();
        lblBuscarPorFecha = new javax.swing.JLabel();
        cbxBuscarPorFecha = new javax.swing.JComboBox<>();
        btnBuscarVentas = new javax.swing.JButton();

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnVolverAlMenu.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnVolverAlMenu.setText("Menu Principal");
        btnVolverAlMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverAlMenuActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Totales de Operaciones Registradas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Narrow", 0, 10))); // NOI18N

        lblCantidadVentas.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        lblCantidadVentas.setText("Cantidad de Ventas Realizadas:");

        txtCantidadVentas.setEditable(false);
        txtCantidadVentas.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        txtCantidadVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadVentasActionPerformed(evt);
            }
        });

        txtTotalGenerado.setEditable(false);
        txtTotalGenerado.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        txtTotalGenerado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalGeneradoActionPerformed(evt);
            }
        });

        lblRegistrosTotales.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        lblRegistrosTotales.setText("Total Generados en Ventas:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCantidadVentas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCantidadVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 277, Short.MAX_VALUE)
                .addComponent(lblRegistrosTotales)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotalGenerado, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCantidadVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCantidadVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalGenerado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRegistrosTotales, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnlHistorialVentas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Historial de Ventas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Narrow", 0, 10))); // NOI18N

        tblHistorialVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "venta_id", "fecha_hora", "cliente", "medicamento", "precio_unitario", "cantidad", "subtotal", "impuesto", "descuento", "total_final"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollHistorialVentas.setViewportView(tblHistorialVentas);

        javax.swing.GroupLayout pnlHistorialVentasLayout = new javax.swing.GroupLayout(pnlHistorialVentas);
        pnlHistorialVentas.setLayout(pnlHistorialVentasLayout);
        pnlHistorialVentasLayout.setHorizontalGroup(
            pnlHistorialVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHistorialVentasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollHistorialVentas)
                .addContainerGap())
        );
        pnlHistorialVentasLayout.setVerticalGroup(
            pnlHistorialVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHistorialVentasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollHistorialVentas, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                .addContainerGap())
        );

        lblTitulo.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Gestion de Informes");

        pnlBusquedaHistorial.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opciones de Busqueda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Narrow", 0, 10))); // NOI18N

        lblBuscarPorFecha.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        lblBuscarPorFecha.setText("Buscar Venta por Fecha:");

        cbxBuscarPorFecha.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        cbxBuscarPorFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxBuscarPorFechaActionPerformed(evt);
            }
        });

        btnBuscarVentas.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        btnBuscarVentas.setText("Buscar Ventas");
        btnBuscarVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarVentasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBusquedaHistorialLayout = new javax.swing.GroupLayout(pnlBusquedaHistorial);
        pnlBusquedaHistorial.setLayout(pnlBusquedaHistorialLayout);
        pnlBusquedaHistorialLayout.setHorizontalGroup(
            pnlBusquedaHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBusquedaHistorialLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBuscarPorFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxBuscarPorFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuscarVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlBusquedaHistorialLayout.setVerticalGroup(
            pnlBusquedaHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBusquedaHistorialLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlBusquedaHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBuscarPorFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxBuscarPorFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarVentas))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlHistorialVentas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnVolverAlMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlBusquedaHistorial, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBusquedaHistorial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlHistorialVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVolverAlMenu)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverAlMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverAlMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVolverAlMenuActionPerformed

    private void txtCantidadVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadVentasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadVentasActionPerformed

    private void txtTotalGeneradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalGeneradoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalGeneradoActionPerformed

    private void btnBuscarVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarVentasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarVentasActionPerformed

    private void cbxBuscarPorFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxBuscarPorFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxBuscarPorFechaActionPerformed

    public JButton getBtnVolverAlMenu() {return btnVolverAlMenu;}
    public JButton getBtnBuscarcarVentas() {return btnBuscarVentas;}
    public JTextField getCantidadVentas() {return txtCantidadVentas;}
    public JTextField getTotalGenerado() {return txtTotalGenerado;}
    public JComboBox getCbxBuscarPorFecha() {return cbxBuscarPorFecha;}
    public JTable getTblHistorialVentas() {return tblHistorialVentas;}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarVentas;
    private javax.swing.JButton btnVolverAlMenu;
    private javax.swing.JComboBox<String> cbxBuscarPorFecha;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblBuscarPorFecha;
    private javax.swing.JLabel lblCantidadVentas;
    private javax.swing.JLabel lblRegistrosTotales;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlBusquedaHistorial;
    private javax.swing.JPanel pnlHistorialVentas;
    private javax.swing.JScrollPane scrollHistorialVentas;
    private javax.swing.JTable tblHistorialVentas;
    private javax.swing.JTextField txtCantidadVentas;
    private javax.swing.JTextField txtTotalGenerado;
    // End of variables declaration//GEN-END:variables
}
