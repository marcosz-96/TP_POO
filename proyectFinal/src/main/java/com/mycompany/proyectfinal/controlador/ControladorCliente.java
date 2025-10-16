package com.mycompany.proyectfinal.controlador;

import com.mycompany.proyectfinal.modelo.Cliente;
import com.mycompany.proyectfinal.modelo.dao.ClienteDAO;
import com.mycompany.proyectfinal.modelo.excepciones.ErrorAccesoDatosExceptions;
import com.mycompany.proyectfinal.vista.FrmCliente;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;
import java.util.logging.Logger;

public class ControladorCliente implements ActionListener{
    
    private FrmCliente vistaClientes;
    private ClienteDAO clienteDAO;
    private ControladorMenu ctMenu;
    private DefaultTableModel tablaClientes;
    private int idClienteSeleccionado = -1;
    
    public ControladorCliente(FrmCliente vistaClientes, ClienteDAO clienteDAO, ControladorMenu ctMenu) throws ErrorAccesoDatosExceptions{
        this.vistaClientes = vistaClientes;
        this.clienteDAO = clienteDAO;
        this.ctMenu = ctMenu;
        
        this.vistaClientes.getBtnGuardar().addActionListener(this);
        this.vistaClientes.getBtnEliminar().addActionListener(this);
        this.vistaClientes.getBtnEditar().addActionListener(this);
        this.vistaClientes.getBtnCancelar().addActionListener(this);
        this.vistaClientes.getBtnMenuPrincipal().addActionListener(this);
        
        this.vistaClientes.setVisible(true);
        listarClientes();
    }
    
    public ControladorCliente(ClienteDAO ctDAO){}
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == vistaClientes.getBtnCancelar()){
            try {
                limpiarCampos();
            } catch (ErrorAccesoDatosExceptions ex) {
                Logger.getLogger(ControladorCliente.class.getName());
            }
            idClienteSeleccionado = -1;
        }else if(e.getSource() == vistaClientes.getBtnGuardar()){
            if(idClienteSeleccionado == -1){
                try {
                    guardarClientes();
                } catch (ErrorAccesoDatosExceptions ex) {
                    Logger.getLogger(ControladorCliente.class.getName());
                }
            }else{
                try {
                    actualizarCliente();
                } catch (ErrorAccesoDatosExceptions ex) {
                    Logger.getLogger(ControladorCliente.class.getName());
                }
            }
        }else if(e.getSource() == vistaClientes.getBtnEliminar()){
            try {
                eliminarCliente();
            } catch (ErrorAccesoDatosExceptions ex) {
                Logger.getLogger(ControladorCliente.class.getName());
            }
        }else if(e.getSource() == vistaClientes.getBtnEditar()){
            try {
                cargarClienteSeleccionado();
            } catch (ErrorAccesoDatosExceptions ex) {
                Logger.getLogger(ControladorCliente.class.getName());
            }
        }else if(e.getSource() == vistaClientes.getBtnMenuPrincipal()){
            this.vistaClientes.setVisible(false);
            ctMenu.volverAlMenu();
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
    private void limpiarCampos()throws ErrorAccesoDatosExceptions{
        vistaClientes.getTxtNombre().setText("");
        vistaClientes.getTxtApellido().setText("");
        vistaClientes.getTxtDni().setText("");
        vistaClientes.getTxtDireccion().setText("");
        vistaClientes.getTxtTelefono().setText("");
        vistaClientes.getTxtNombre().requestFocus();
    }
    
    
    
    // Metodo que elimina datos por ID
    private void eliminarCliente() throws ErrorAccesoDatosExceptions{
        int fila = vistaClientes.getTablaClientes().getSelectedRow();
        if(fila == -1){
            JOptionPane.showMessageDialog(vistaClientes, "¡ATENCION! Seleccione los datos que desea eliminar.");
            return;
        }
        
        int id = (int) vistaClientes.getTablaClientes().getValueAt(fila, 0);
        
        int confirmar = JOptionPane.showConfirmDialog(vistaClientes, "¡ATENCION! Seguro que desea eliminar este dato?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        
        if(confirmar == JOptionPane.YES_OPTION){
            clienteDAO.delete(id);
            JOptionPane.showMessageDialog(vistaClientes, "¡ATENCION! Datos eliminados.");
            listarClientes();
        }
    }
    
    // Metodo que selecciona los datos de la tabla que se van a modificar
    private void cargarClienteSeleccionado() throws ErrorAccesoDatosExceptions{
        int fila = vistaClientes.getTablaClientes().getSelectedRow();
        if(fila == -1){
            JOptionPane.showMessageDialog(vistaClientes, "¡ATENCION! Seleccione la fila que desea modificar.");
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
    
    private String validarCampos() {
        String nombre = vistaClientes.getTxtNombre().getText().trim();
        String apellido = vistaClientes.getTxtApellido().getText().trim();
        String dniStr = vistaClientes.getTxtDni().getText().trim();
        String direccion = vistaClientes.getTxtDireccion().getText().trim();
        String telefonoStr = vistaClientes.getTxtTelefono().getText().trim();

        if(nombre.isEmpty()) return "¡ATENCION! El campo Nombre es obligatorio.";
        if(apellido.isEmpty()) return "¡ATENCION! El campo Apellido es obligatorio.";
        if(dniStr.isEmpty()) return "¡ATENCION! El campo DNI es obligatorio.";
        if(direccion.isEmpty()) return "¡ATENCION! El campo Dirección es obligatorio.";
        if(telefonoStr.isEmpty()) return "¡ATENCION! El campo Teléfono es obligatorio.";

        try {
            int dni = Integer.parseInt(dniStr);
            if(dni <= 0) return "¡ATENCION! El DNI debe ser un número positivo.";
        } catch(NumberFormatException e) {
            return "¡ATENCION! El DNI debe ser un número válido.";
        }

        try {
            long telefono = Long.parseLong(telefonoStr);
            if(telefono <= 0) return "¡ATENCION! El Teléfono debe ser un número positivo.";
        } catch(NumberFormatException e) {
            return "¡ATENCION! El Teléfono debe ser un número válido.";
        }

        return null; // todo bien
    }

    private void guardarClientes() throws ErrorAccesoDatosExceptions {
        String error = validarCampos();
        if(error != null) {
            JOptionPane.showMessageDialog(vistaClientes, error, "¡ATENCION! Error de validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Si pasa validación, extraemos valores
        String nombre = vistaClientes.getTxtNombre().getText().trim();
        String apellido = vistaClientes.getTxtApellido().getText().trim();
        int dni = Integer.parseInt(vistaClientes.getTxtDni().getText().trim());
        String direccion = vistaClientes.getTxtDireccion().getText().trim();
        long telefono = Long.parseLong(vistaClientes.getTxtTelefono().getText().trim());

        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setDni(dni);
        cliente.setDireccion(direccion);
        cliente.setTelefono(telefono);

        clienteDAO.insert(cliente);
        JOptionPane.showMessageDialog(vistaClientes, "¡ATENCION! Datos de cliente guardados correctamente.");
        listarClientes();
        limpiarCampos();
    }

    private void actualizarCliente() throws ErrorAccesoDatosExceptions {
        String error = validarCampos();
        if(error != null) {
            JOptionPane.showMessageDialog(vistaClientes, error, "¡ATENCION! Error de validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nombre = vistaClientes.getTxtNombre().getText().trim();
        String apellido = vistaClientes.getTxtApellido().getText().trim();
        int dni = Integer.parseInt(vistaClientes.getTxtDni().getText().trim());
        String direccion = vistaClientes.getTxtDireccion().getText().trim();
        long telefono = Long.parseLong(vistaClientes.getTxtTelefono().getText().trim());

        Cliente cliente = new Cliente();
        cliente.setId(idClienteSeleccionado);
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setDni(dni);
        cliente.setDireccion(direccion);
        cliente.setTelefono(telefono);

        clienteDAO.update(cliente);
        JOptionPane.showMessageDialog(vistaClientes, "¡ATENCION! Datos del cliente actualizados correctamente.");
        listarClientes();
        limpiarCampos();
        idClienteSeleccionado = -1;
    }

}
