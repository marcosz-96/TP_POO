package com.mycompany.proyectfinal.controlador;

import com.mycompany.proyectfinal.modelo.dao.ClienteDAO;
import com.mycompany.proyectfinal.modelo.dao.MedicamentoDAO;
import com.mycompany.proyectfinal.modelo.dao.VentaDAO;
import com.mycompany.proyectfinal.modelo.excepciones.ErrorAccesoDatosExceptions;
import com.mycompany.proyectfinal.vista.FrmCliente;
import com.mycompany.proyectfinal.vista.FrmMedicamento;
import com.mycompany.proyectfinal.vista.FrmMenu;
import com.mycompany.proyectfinal.vista.FrmVenta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ControladorMenu implements ActionListener{
    private FrmMenu vista;
    private JFrame vistaActual;
    
    public ControladorMenu(FrmMenu vista)throws ErrorAccesoDatosExceptions{
        this.vista = vista;
        
        this.vista.getBtnCliente().addActionListener(this);
        this.vista.getBtnInventario().addActionListener(this);
        this.vista.getBtnVentas().addActionListener(this);
        this.vista.getBtnInformes().addActionListener(this);
     
        this.vista.setVisible(true);
    }

    public ControladorMenu() {}
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == vista.getBtnCliente()){
            ventanaCliente();
        } else if(e.getSource() == vista.getBtnInventario()){
            ventanaInventario();
        }
    }
    
    private void ventanaCliente(){
        try{
            vista.setVisible(false); 
            
            FrmCliente vtCliente = new FrmCliente();
            ClienteDAO clienteDAO = new ClienteDAO();
            ControladorCliente ctCliente = new ControladorCliente(vtCliente, clienteDAO, this);
            
            vistaActual = vtCliente;
            vtCliente.setVisible(true);
        } catch(ErrorAccesoDatosExceptions ex){
            Logger.getLogger(ControladorMenu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(vista, "¡ERROR! Al intentar abrir ventana cliente." + ex.getMessage(),
                "ERROR", 
                JOptionPane.ERROR_MESSAGE);
            vista.setVisible(true);
        }
    }
    
    private void ventanaInventario(){
        try{
            vista.setVisible(false);
        
            FrmMedicamento vtMedicamento = new FrmMedicamento();
            MedicamentoDAO medicamentoDAO = new MedicamentoDAO();
            ControladorMedicamento ctMedicamento = new ControladorMedicamento(vtMedicamento, medicamentoDAO, this);
        
            vistaActual = vtMedicamento;
            vtMedicamento.setVisible(true);
        }catch(ErrorAccesoDatosExceptions ex){
            Logger.getLogger(ControladorMenu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(vista, "¡ERROR! Al intentar abrir ventana medicamentos." + ex.getMessage(),
                "ERROR", 
                JOptionPane.ERROR_MESSAGE);
            vista.setVisible(true);  
        }
    }
    
    /*private void VentanaVentas(){
        try{
            vista.setVisible(false);
            
            FrmVenta vtVentas = new FrmVenta();
            VentaDAO ventaDAO = new VentaDAO();
            ControladorVenta ctVentas = new ControladorVenta(vtVentas, ventaDAO, this);
            
            vistaActual = vtVentas;
            vtVentas.setVisible(true);
        }catch(ErrorAccesoDatosExceptions ex){
            Logger.getLogger(ControladorMenu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(vista, "¡ERROR! Al intentar abrir ventana Ventas." + ex.getMessage(),
                "ERROR", 
                JOptionPane.ERROR_MESSAGE);
            vista.setVisible(true);  
        }
    }*/
    
    /*private void VentanaInformes(){
        try{
            vista.setVisible(false);
            
            FrmReportes vtReportes = new FrmReportes();
            DetalleVentaDAO dtVentaDAO = new DetalleVentaDAO();
            ControladorReporte ctReporte = new ControladorReporte(vtReportes, dtVentaDAO, this);
            
            vistaActual = vtReportes;
            vtReportes.setVisible(true);
        } catch(ErrorAccesoDatosExceptions ex){
            Logger.getLogger(ControladorMenu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(vista, "¡ERROR! Al intentar abrir ventana de Informes." + ex.getMessage(),
                "ERROR", 
                JOptionPane.ERROR_MESSAGE);
            vista.setVisible(true);    
        }
    }*/
    
    /**
     * Método para volver al menú desde cualquier ventana
     */
    
    public void volverAlMenu(){
        if(vistaActual != null){
            vistaActual.dispose();
            vistaActual = null;
        }
        vista.setVisible(true);
    }
}
