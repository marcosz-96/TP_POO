package com.mycompany.proyectfinal.controlador;

import com.mycompany.proyectfinal.modelo.Medicamento;
import com.mycompany.proyectfinal.modelo.dao.MedicamentoDAO;
import com.mycompany.proyectfinal.modelo.excepciones.ErrorAccesoDatosExceptions;
import com.mycompany.proyectfinal.vista.FrmMedicamento;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class ControladorMedicamento implements ActionListener{
    
    private final FrmMedicamento vistaMedicamentos;
    private final MedicamentoDAO medicamentoDAO;
    private DefaultTableModel tablaMedicamentos;
    private int idMedicamentoSeleccionado = -1;
    
    public ControladorMedicamento(FrmMedicamento vistaMedicamentos, MedicamentoDAO medicamentoDAO)throws ErrorAccesoDatosExceptions{
        this.vistaMedicamentos = vistaMedicamentos;
        this.medicamentoDAO = medicamentoDAO;
        
        this.vistaMedicamentos.getBtnEditar().addActionListener(this);
        this.vistaMedicamentos.getBtnEliminar().addActionListener(this);
        this.vistaMedicamentos.getBtnGuardar().addActionListener(this);
        this.vistaMedicamentos.getBtnNuevo().addActionListener(this);
        
        listarMedicamentos();
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == vistaMedicamentos.getBtnGuardar()){
            if(idMedicamentoSeleccionado == -1){
                try {
                    guardarMedicamento();
                } catch (ErrorAccesoDatosExceptions ex) {
                    Logger.getLogger(ControladorMedicamento.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                try {
                    actualizarMedicamento();
                } catch (ErrorAccesoDatosExceptions ex) {
                    Logger.getLogger(ControladorMedicamento.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if(e.getSource() == vistaMedicamentos.getBtnNuevo()){
            try {
                limpiarCampos();
            } catch (ErrorAccesoDatosExceptions ex) {
                Logger.getLogger(ControladorMedicamento.class.getName()).log(Level.SEVERE, null, ex);
            }
            idMedicamentoSeleccionado = -1;
        }
        if(e.getSource() == vistaMedicamentos.getBtnEditar()){
            try {
                cargarMedicamentoSeleccionado();
            } catch (ErrorAccesoDatosExceptions ex) {
                Logger.getLogger(ControladorMedicamento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(e.getSource() == vistaMedicamentos.getBtnEliminar()){
            try {
                eliminarMedicamento();
            } catch (ErrorAccesoDatosExceptions ex) {
                Logger.getLogger(ControladorMedicamento.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        String nombre = vistaMedicamentos.getTxtNombre().getText();
        String precioStr = vistaMedicamentos.getTxtPrecio().getText();
        String stockStr = vistaMedicamentos.getTxtStock().getText();
        
        // Verificamos que no hayan espacios en blanco y campos vacios.
        while(nombre.trim().isEmpty() || precioStr.trim().isEmpty() || stockStr.trim().isEmpty()){
            JOptionPane.showMessageDialog(vistaMedicamentos, "[ATENCION] Complete todos los campos.");
            return;
        }
        
        // Una vez verificado, se convierten algunos tipos de datos y se registran
        try{
            double precio = Double.parseDouble(precioStr);
            int stock = Integer.parseInt(stockStr);
            
            Medicamento medicamentos = new Medicamento();
            medicamentos.setNombre(nombre);
            medicamentos.setPrecio(precio);
            medicamentos.setStock(stock);
            
            medicamentoDAO.insert(medicamentos);
            JOptionPane.showMessageDialog(vistaMedicamentos, "[ATENCION] Datos de medicamentos guardados.");
            listarMedicamentos();
            limpiarCampos();
            
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(vistaMedicamentos, "[ATENCION] Los datos de Precio y Stock deben ser numericos.");
        }
    }
    
    // Metodo que selecciona los datos de la tabla que se van a aditar
    private void cargarMedicamentoSeleccionado() throws ErrorAccesoDatosExceptions{
        int fila = vistaMedicamentos.getTablaMedicamentos().getSelectedRow();
        if(fila == -1){
            JOptionPane.showMessageDialog(vistaMedicamentos, "[ATENCION] Seleccione una fila de la tabla.");
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
        String nombre = vistaMedicamentos.getTxtNombre().getText();
        String precioStr = vistaMedicamentos.getTxtPrecio().getText();
        String stockStr = vistaMedicamentos.getTxtStock().getText();
        
        // Verificamos que no hayan espacios en blanco y campos vacios.
        while(nombre.trim().isEmpty() || precioStr.trim().isEmpty() || stockStr.trim().isEmpty()){
            JOptionPane.showMessageDialog(vistaMedicamentos, "[ATENCION] Complete todos los campos.");
            return;
        }
        
        // Una vez verificado, se convierten algunos tipos de datos y se registran
        try{
            double precio = Double.parseDouble(precioStr);
            int stock = Integer.parseInt(stockStr);
            
            Medicamento medicamentos = new Medicamento();
            medicamentos.setId(idMedicamentoSeleccionado);
            medicamentos.setNombre(nombre);
            medicamentos.setPrecio(precio);
            medicamentos.setStock(stock);
            
            medicamentoDAO.update(medicamentos);
            JOptionPane.showMessageDialog(vistaMedicamentos, "[ATENCION] Los datos del medicamento actualizados.");
            listarMedicamentos();
            limpiarCampos();
            idMedicamentoSeleccionado = -1;
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(vistaMedicamentos, "[ATENCION] Los datos de Precio y Stock deben ser numericos.");
        }
    }
    
    // Metodo que elimina datos por ID
    private void eliminarMedicamento() throws ErrorAccesoDatosExceptions{
        int fila = vistaMedicamentos.getTablaMedicamentos().getSelectedRow();
        while(fila == -1){
            JOptionPane.showMessageDialog(vistaMedicamentos, "[ATENCION] Seleccione el dato que desea eliminar.");
            return;
        }
        
        int id = (int) vistaMedicamentos.getTablaMedicamentos().getValueAt(fila, 0);
        
        int confirm = JOptionPane.showConfirmDialog(vistaMedicamentos, "[ATENCION] Seguro que desea eliminar este dato?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        if(confirm == JOptionPane.YES_OPTION){
            medicamentoDAO.delete(id);
            JOptionPane.showMessageDialog(vistaMedicamentos, "[ATENCION] Dato eliminado con exito.");
            listarMedicamentos();
        }
    }
}
