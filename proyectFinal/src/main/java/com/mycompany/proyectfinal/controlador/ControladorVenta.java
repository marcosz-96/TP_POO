package com.mycompany.proyectfinal.controlador;

import com.mycompany.proyectfinal.modelo.DetalleVenta;
import com.mycompany.proyectfinal.modelo.dao.ClienteDAO;
import com.mycompany.proyectfinal.modelo.dao.DetalleVentaDAO;
import com.mycompany.proyectfinal.modelo.dao.MedicamentoDAO;
import com.mycompany.proyectfinal.vista.FrmVentas;
import com.mycompany.proyectfinal.modelo.dao.VentaDAO;

import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ControladorVenta implements ActionListener{
    private FrmVentas vistaVentas;
    private VentaDAO ventaDAO;
    private DetalleVentaDAO detalleDAO;
    private ClienteDAO clienteDAO;
    private MedicamentoDAO medicamentoDAO;
    
    private List<DetalleVenta> detallesVentas = new ArrayList();
    private double total = 0.0;
    
    public ControladorVenta(FrmVentas vistaVentas, VentaDAO ventaDAO, DetalleVentaDAO detalleDAO, ClienteDAO clienteDAO, MedicamentoDAO medicamentoDAO){
        this.vistaVentas = vistaVentas;
        this.ventaDAO = ventaDAO;
        this.detalleDAO = detalleDAO;
        this.clienteDAO = clienteDAO;
        this.medicamentoDAO = medicamentoDAO;
        
        this.vistaVentas.btnAgregar.addActionListener(this);
        this.vistaVentas.btnFinalizarVenta.addActionListener(this);
        this.vistaVentas.btn
        
    }
    
    
}
