package com.mycompany.proyectfinal.controlador;

import com.mycompany.proyectfinal.modelo.Medicamento;
import com.mycompany.proyectfinal.modelo.dao.MedicamentoDAO;
import com.mycompany.proyectfinal.modelo.excepciones.ErrorAccesoDatosExceptions;
import com.mycompany.proyectfinal.vista.FrmMedicamento;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class ControladorMedicamento implements ActionListener{
    
    private final FrmMedicamento vistaMedicamentos;
    private final MedicamentoDAO medicamentoDAO;
    private final ControladorMenu ctMenu;
    private DefaultTableModel tablaMedicamentos;
    private int idMedicamentoSeleccionado = -1;
    
    public ControladorMedicamento(FrmMedicamento vistaMedicamentos, MedicamentoDAO medicamentoDAO, ControladorMenu ctMenu)throws ErrorAccesoDatosExceptions{
        this.vistaMedicamentos = vistaMedicamentos;
        this.medicamentoDAO = medicamentoDAO;
        this.ctMenu = ctMenu;
        
        this.vistaMedicamentos.getBtnEditar().addActionListener(this);
        this.vistaMedicamentos.getBtnEliminar().addActionListener(this);
        this.vistaMedicamentos.getBtnGuardar().addActionListener(this);
        this.vistaMedicamentos.getBtnCancelar().addActionListener(this);
        this.vistaMedicamentos.getBtnMenuPrincipal().addActionListener(this);
       
        this.vistaMedicamentos.setVisible(true);
        listarMedicamentos();
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == vistaMedicamentos.getBtnGuardar()){
            if(idMedicamentoSeleccionado == -1){
                try {
                    guardarMedicamento();
                } catch (ErrorAccesoDatosExceptions ex) {
                    Logger.getLogger(ControladorMedicamento.class.getName());
                }
            }else{
                try {
                    actualizarMedicamento();
                } catch (ErrorAccesoDatosExceptions ex) {
                    Logger.getLogger(ControladorMedicamento.class.getName());
                }
            }
        }
        if(e.getSource() == vistaMedicamentos.getBtnCancelar()){
            try {
                limpiarCampos();
            } catch (ErrorAccesoDatosExceptions ex) {
                Logger.getLogger(ControladorMedicamento.class.getName());
            }
            idMedicamentoSeleccionado = -1;
        }
        if(e.getSource() == vistaMedicamentos.getBtnEditar()){
            try {
                cargarMedicamentoSeleccionado();
            } catch (ErrorAccesoDatosExceptions ex) {
                Logger.getLogger(ControladorMedicamento.class.getName());
            }
        }
        if(e.getSource() == vistaMedicamentos.getBtnEliminar()){
            try {
                eliminarMedicamento();
            } catch (ErrorAccesoDatosExceptions ex) {
                Logger.getLogger(ControladorMedicamento.class.getName());
            }
        }else if(e.getSource() == vistaMedicamentos.getBtnMenuPrincipal()){
            ctMenu.volverAlMenu();
        }
    }
    
    // Metodo que trae la lista de Medicamentos registrados
    private void listarMedicamentos() throws ErrorAccesoDatosExceptions{
        tablaMedicamentos = (DefaultTableModel) vistaMedicamentos.getTablaMedicamentos().getModel();
        tablaMedicamentos.setRowCount(0);
        
        List<Medicamento> listaMedicamentos = medicamentoDAO.listarMedicamentos();
        for(Medicamento m : listaMedicamentos){
            tablaMedicamentos.addRow(new Object[]{
                m.getId(),
                m.getNombre(),
                m.getPrecio(),
                m.getStock()
            });
        }
    }
    
    // Metodo que limpia los campos de entrada para agregar nuevos datos
    private void limpiarCampos() throws ErrorAccesoDatosExceptions{
        vistaMedicamentos.getTxtNombre().setText("");
        vistaMedicamentos.getTxtPrecio().setText("");
        vistaMedicamentos.getTxtStock().setText("");
        vistaMedicamentos.getTxtNombre().requestFocus();
    }
    
    // Metodo para registrar nuevos datos
    private void guardarMedicamento() throws ErrorAccesoDatosExceptions{
        String error = validarCampos();
        if(error != null) {
            JOptionPane.showMessageDialog(vistaMedicamentos, error, "¡ATENCION! Error de validación", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String nombre = vistaMedicamentos.getTxtNombre().getText();
        String precioStr = vistaMedicamentos.getTxtPrecio().getText();
        String stockStr = vistaMedicamentos.getTxtStock().getText();
        
        BigDecimal precio = new BigDecimal(precioStr);
        int stock = Integer.parseInt(stockStr);
            
        Medicamento medicamentos = new Medicamento();
        medicamentos.setNombre(nombre);
        medicamentos.setPrecio(precio);
        medicamentos.setStock(stock);
           
        medicamentoDAO.insert(medicamentos);
        JOptionPane.showMessageDialog(vistaMedicamentos, "¡ATENCION! Datos de medicamentos guardados.");
        listarMedicamentos();
        limpiarCampos();
    }
    
    // Metodo que selecciona los datos de la tabla que se van a aditar
    private void cargarMedicamentoSeleccionado() throws ErrorAccesoDatosExceptions{
        int fila = vistaMedicamentos.getTablaMedicamentos().getSelectedRow();
        if(fila == -1){
            JOptionPane.showMessageDialog(vistaMedicamentos, "¡ATENCION! Seleccione una fila de la tabla.");
            return;
        }
        
        int id = (int) vistaMedicamentos.getTablaMedicamentos().getValueAt(fila, 0);
        String nombre = vistaMedicamentos.getTablaMedicamentos().getValueAt(fila, 1).toString();
        double precio = Double.parseDouble(vistaMedicamentos.getTablaMedicamentos().getValueAt(fila, 2).toString());
        int stock = Integer.parseInt(vistaMedicamentos.getTablaMedicamentos().getValueAt(fila, 3).toString());
        
        // Agregamos a los campos
        vistaMedicamentos.getTxtNombre().setText(nombre);
        vistaMedicamentos.getTxtPrecio().setText(String.valueOf(precio));
        vistaMedicamentos.getTxtStock().setText(String.valueOf(stock));
        
        idMedicamentoSeleccionado = id;
    }
    
    // Metodo que actualiza los datos seleccionados
    private void actualizarMedicamento() throws ErrorAccesoDatosExceptions{
        String error = validarCampos();
        if(error != null) {
            JOptionPane.showMessageDialog(vistaMedicamentos, error, "¡ATENCION! Error de validación", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String nombre = vistaMedicamentos.getTxtNombre().getText();
        String precioStr = vistaMedicamentos.getTxtPrecio().getText();
        String stockStr = vistaMedicamentos.getTxtStock().getText();
        
        BigDecimal precio = new BigDecimal(precioStr);
        int stock = Integer.parseInt(stockStr);
            
        Medicamento medicamentos = new Medicamento();
        medicamentos.setId(idMedicamentoSeleccionado);
        medicamentos.setNombre(nombre);
        medicamentos.setPrecio(precio);
        medicamentos.setStock(stock);
            
        medicamentoDAO.update(medicamentos);
        JOptionPane.showMessageDialog(vistaMedicamentos, "¡ATENCION! Los datos del medicamento actualizados.");
        listarMedicamentos();
        limpiarCampos();
        idMedicamentoSeleccionado = -1;
    }
    
    // Metodo que elimina datos por ID
    private void eliminarMedicamento() throws ErrorAccesoDatosExceptions{
        int fila = vistaMedicamentos.getTablaMedicamentos().getSelectedRow();
        if(fila == -1){
            JOptionPane.showMessageDialog(vistaMedicamentos, "¡ATENCION! Seleccione el dato que desea eliminar.");
            return;
        }
        
        int id = (int) vistaMedicamentos.getTablaMedicamentos().getValueAt(fila, 0);
        
        int confirm = JOptionPane.showConfirmDialog(vistaMedicamentos, "¡ATENCION! Seguro que desea eliminar este dato?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        if(confirm == JOptionPane.YES_OPTION){
            medicamentoDAO.delete(id);
            JOptionPane.showMessageDialog(vistaMedicamentos, "¡ATENCION! Dato eliminado con exito.");
            listarMedicamentos();
        }
    }
    
    private String validarCampos() {
        String nombre = vistaMedicamentos.getTxtNombre().getText().trim();
        String precioStr = vistaMedicamentos.getTxtPrecio().getText().trim();
        String stockStr = vistaMedicamentos.getTxtStock().getText().trim();

        if(nombre.isEmpty()) return "¡ATENCION! El campo Nombre es obligatorio.";
        if(precioStr.isEmpty()) return "¡ATENCION! El campo Precio es obligatorio.";
        if(stockStr.isEmpty()) return "¡ATENCION! El campo Stock es obligatorio.";

        try {
            int precio = Integer.parseInt(precioStr);
            if(precio <= 0) return "¡ATENCION! El Precio debe ser un número positivo.";
        } catch(NumberFormatException e) {
            return "¡ATENCION! El Precio debe ser un número válido.";
        }

        try {
            long stock = Long.parseLong(stockStr);
            if(stock <= 0) return "¡ATENCION! El stock debe ser un número positivo.";
        } catch(NumberFormatException e) {
            return "¡ATENCION! El stock debe ser un número válido.";
        }

        return null; // todo bien
    }
}