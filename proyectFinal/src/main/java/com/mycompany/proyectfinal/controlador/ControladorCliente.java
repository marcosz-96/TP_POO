package com.mycompany.proyectfinal.controlador;

import com.mycompany.proyectfinal.modelo.Cliente;
import com.mycompany.proyectfinal.modelo.dao.ClienteDAO;
import com.mycompany.proyectfinal.modelo.excepciones.ErrorAccesoDatosExceptions;
import com.mycompany.proyectfinal.vista.FrmCliente;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorCliente implements ActionListener{
    
    private final FrmCliente vistaClientes;
    private final ClienteDAO clienteDAO;
    private DefaultTableModel tablaClientes;
    private int idClienteSeleccionado = -1;
    
    public ControladorCliente(FrmCliente vistaClientes, ClienteDAO clienteDAO) throws ErrorAccesoDatosExceptions{
        this.vistaClientes = vistaClientes;
        this.clienteDAO = clienteDAO;
        
        this.vistaClientes.getBtnNuevo().addActionListener(this);
        this.vistaClientes.getBtnGuardar().addActionListener(this);
        this.vistaClientes.getBtnEliminar().addActionListener(this);
        this.vistaClientes.getBtnEditar().addActionListener(this);
        
        listarClientes();
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == vistaClientes.getBtnNuevo()){
            try {
                limpiarCampos();
            } catch (ErrorAccesoDatosExceptions ex) {
                Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            idClienteSeleccionado = -1;
        }
        if(e.getSource() == vistaClientes.getBtnGuardar()){
            if(idClienteSeleccionado == -1){
                try {
                    guardarClientes();
                } catch (ErrorAccesoDatosExceptions ex) {
                    Logger.getLogger(ControladorMedicamento.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                try {
                    actualizarCliente();
                } catch (ErrorAccesoDatosExceptions ex) {
                    Logger.getLogger(ControladorMedicamento.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if(e.getSource() == vistaClientes.getBtnEliminar()){
            try {
                eliminarCliente();
            } catch (ErrorAccesoDatosExceptions ex) {
                Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(e.getSource() == vistaClientes.getBtnEditar()){
            try {
                cargarClienteSeleccionado();
            } catch (ErrorAccesoDatosExceptions ex) {
                Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    // Metodo que trae la lista de clientes de la DB
    private void listarClientes() throws ErrorAccesoDatosExceptions{
        tablaClientes = (DefaultTableModel) vistaClientes.getTablaClientes().getModel();
        tablaClientes.setRowCount(0);
        
        List<Cliente> listaClientes = clienteDAO.listaDeClientes();
        for (Cliente c : listaClientes){
            tablaClientes.addRow(new Object[]{
                c.getId(), 
                c.getNombre(), 
                c.getApellido(),
                c.getDni(), 
                c.getDireccion(), 
                c.getTelefono()
            });
        }
    }
    
    // Metodo que limpia los campos de entrada para agregar nuevos datos
    private void limpiarCampos() throws ErrorAccesoDatosExceptions{
        vistaClientes.getTxtNombre().setText("");
        vistaClientes.getTxtApellido().setText("");
        vistaClientes.getTxtDni().setText("");
        vistaClientes.getTxtDireccion().setText("");
        vistaClientes.getTxtTelefono().setText("");
        vistaClientes.getTxtNombre().requestFocus();
    }
    
    // Metodo para guardar datos de clientes
    private void guardarClientes() throws ErrorAccesoDatosExceptions{
        
        String nombre = vistaClientes.getTxtNombre().getText();
        String apellido = vistaClientes.getTxtApellido().getText();
        String dniSrt = vistaClientes.getTxtDni().getText();
        String direccion = vistaClientes.getTxtDireccion().getText();
        String telefonoSrt = vistaClientes.getTxtTelefono().getText();
        
        // Verificamos que los campos no esten vacios
        while(nombre.trim().isEmpty() || apellido.trim().isEmpty() || dniSrt.trim().isEmpty() ||
                direccion.trim().isEmpty() || telefonoSrt.trim().isEmpty()){
            JOptionPane.showMessageDialog(vistaClientes, "¡ATENCION! Complete todos los campos");
            return;
        }
        vistaClientes.getTxtNombre().requestFocus();
        
        // Una vez verificado se realiza la operacion de guardar
        try{
            int dni = Integer.parseInt(dniSrt);
            long telefono = Long.parseLong(telefonoSrt);
            
            Cliente clientes = new Cliente();
            clientes.setNombre(nombre);
            clientes.setApellido(apellido);
            clientes.setDni(dni);
            clientes.setDireccion(direccion);
            clientes.setTelefono(telefono);
            
            clienteDAO.insert(clientes);
            JOptionPane.showMessageDialog(vistaClientes, "[ATENCION] Datos de clientes guardados.");
            listarClientes();
            limpiarCampos();
            
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(vistaClientes, "[ATENCION] Los campos DNI y TELEFONO deben ser numericos.");
        }   
    }
    
    // Metodo que elimina datos por ID
    private void eliminarCliente() throws ErrorAccesoDatosExceptions{
        int fila = vistaClientes.getTablaClientes().getSelectedRow();
        while(fila == -1){
            JOptionPane.showMessageDialog(vistaClientes, "Seleccione los datos que desea eliminar.");
            return;
        }
        
        int id = (int) vistaClientes.getTablaClientes().getValueAt(fila, 0);
        
        int confirmar = JOptionPane.showConfirmDialog(vistaClientes, "Seguro que desea eliminar este dato?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        
        if(confirmar == JOptionPane.YES_OPTION){
            clienteDAO.delete(id);
            JOptionPane.showMessageDialog(vistaClientes, "Datos eliminados.");
            listarClientes();
        }
    }
    
    // Metodo que selecciona los datos de la tabla que se van a modificar
    private void cargarClienteSeleccionado() throws ErrorAccesoDatosExceptions{
        int fila = vistaClientes.getTablaClientes().getSelectedRow();
        while(fila == -1){
            JOptionPane.showMessageDialog(vistaClientes, "Seleccione la fila que desea modificar.");
            return;
        }
        
        int id = (int) vistaClientes.getTablaClientes().getValueAt(fila, 0);
        String nombre = vistaClientes.getTablaClientes().getValueAt(fila, 1).toString();
        String apellido = vistaClientes.getTablaClientes().getValueAt(fila, 2).toString();
        int dni = Integer.parseInt(vistaClientes.getTablaClientes().getValueAt(fila, 3).toString());
        String direccion = vistaClientes.getTablaClientes().getValueAt(fila, 4).toString();
        long telefono = Long.parseLong(vistaClientes.getTablaClientes().getValueAt(fila, 5).toString());
     
        // Agregamos a los campos
        vistaClientes.getTxtNombre().setText(nombre);
        vistaClientes.getTxtApellido().setText(apellido);
        vistaClientes.getTxtDni().setText(String.valueOf(dni));
        vistaClientes.getTxtDireccion().setText(direccion);
        vistaClientes.getTxtTelefono().setText(String.valueOf(telefono));
     
        idClienteSeleccionado = id;
    }
    
    private void actualizarCliente()throws ErrorAccesoDatosExceptions{
        String nombre = vistaClientes.getTxtNombre().getText();
        String apellido = vistaClientes.getTxtApellido().getText();
        String dniSrt = vistaClientes.getTxtDni().getText();
        String direccion = vistaClientes.getTxtDireccion().getText();
        String telefonoSrt = vistaClientes.getTxtTelefono().getText();
        
        // Verificamos que los campos no esten vacios
        while(nombre.trim().isEmpty() || apellido.trim().isEmpty() || dniSrt.trim().isEmpty() ||
                direccion.trim().isEmpty() || telefonoSrt.trim().isEmpty()){
            JOptionPane.showMessageDialog(vistaClientes, "¡ATENCION! Complete todos los campos");
            return;
        }
        vistaClientes.getTxtNombre().requestFocus();
        
        // Una vez verificado se realiza la operacion de guardar
        try{
            int dni = Integer.parseInt(dniSrt);
            long telefono = Long.parseLong(telefonoSrt);
            
            Cliente clientes = new Cliente();
            clientes.setId(idClienteSeleccionado);
            clientes.setNombre(nombre);
            clientes.setApellido(apellido);
            clientes.setDni(dni);
            clientes.setDireccion(direccion);
            clientes.setTelefono(telefono);
            
            clienteDAO.update(clientes);
            JOptionPane.showMessageDialog(vistaClientes, "[ATENCION] Los datos del cliente han sido actualizados.");
            listarClientes();
            limpiarCampos();
            idClienteSeleccionado = -1;
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(vistaClientes, "[ATENCION] Los campos DNI y TELEFONO deben ser numericos.");
        }  
    }
}
