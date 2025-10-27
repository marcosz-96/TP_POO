package com.mycompany.proyectfinal.controlador;

import com.mycompany.proyectfinal.modelo.Cliente;
import com.mycompany.proyectfinal.modelo.DetalleVenta;
import com.mycompany.proyectfinal.modelo.Medicamento;
import com.mycompany.proyectfinal.modelo.Venta;
import com.mycompany.proyectfinal.modelo.dao.ClienteDAO;
import com.mycompany.proyectfinal.modelo.dao.DetalleVentaDAO;
import com.mycompany.proyectfinal.modelo.dao.MedicamentoDAO;
import com.mycompany.proyectfinal.vista.FrmVenta;
import com.mycompany.proyectfinal.modelo.dao.VentaDAO;
import com.mycompany.proyectfinal.modelo.excepciones.ErrorAccesoDatosExceptions;
import com.mycompany.proyectfinal.modelo.servicio.ServiceVenta;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Controlador para la gestion de Ventas en la interfaz Swing
 * Coordina entre la vista, los DAOs y la capa de servicio.
 * @author Gomez Marco, Courel Brian, Lairana Rocio
 */

public class ControladorVenta implements ActionListener{
    private FrmVenta vistaVentas;
    private VentaDAO ventaDAO;
    private DetalleVentaDAO detalleDAO;
    private ClienteDAO clienteDAO;
    private MedicamentoDAO medicamentoDAO;
    private ControladorMenu ctMenu;
    private ServiceVenta ventaService;
    
    private List<DetalleVenta> detallesVentas = new ArrayList();
    private Map<String, Integer> clientesMap = new HashMap<>();
    private Map<String, Integer> medicamentosMap = new HashMap<>();
    
    /**
     * Contructor del Controlador
     * @param vistaVentas 
     */
    
    public ControladorVenta(FrmVenta vistaVentas, ControladorMenu ctMenu) throws ErrorAccesoDatosExceptions{
        this.vistaVentas = vistaVentas;
        this.ventaDAO = new VentaDAO();
        this.detalleDAO = new DetalleVentaDAO();
        this.clienteDAO = new ClienteDAO();
        this.medicamentoDAO = new MedicamentoDAO();
        
        this.ventaService = new ServiceVenta();
        
        this.ctMenu = ctMenu;
        
        inicializarVista();
        registrarEventos();
    }
    
    private void inicializarVista(){
       DateTimeFormatter formatoFH = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
       vistaVentas.getTxtFecha().setText(LocalDateTime.now().format(formatoFH));
       
       vistaVentas.getTxtImpuesto().setText(ventaService.getIvaDefault()
                                            .multiply(BigDecimal.valueOf(100))
                                            .toString()
       );
       
       vistaVentas.getTxtDescuento().setText(ventaService.getDescuentoDefault()
                                             .multiply(BigDecimal.valueOf(100))
                                             .toString()
       );
       //vistaVentas.getTxtDescuento().setText("00.00");
       
       cargarClientes();
       cargarMedicamentos();
    }
    
    /**
     * Registra los eventos de los botones
     */
    
    private void registrarEventos(){
       vistaVentas.getBtnAgregarDetalle().addActionListener(this);
       vistaVentas.getBtnEliminarDetalle().addActionListener(this);
       vistaVentas.getBtnGuardarVenta().addActionListener(this);
       vistaVentas.getBtnNuevaVenta().addActionListener(this);
       vistaVentas.getBtnCancelar().addActionListener(this);
       vistaVentas.getBtnMenuPrincipal().addActionListener(this);
       
       vistaVentas.getCbxMedicamento().addItemListener((ItemEvent e) -> {
           if(e.getStateChange() == ItemEvent.SELECTED){
               mostrarPrecioUnitario();
           }
       });
    }
    
    /**
     * Método que muestra el precio unitario cuando se selecciona un Medicamento
     */
    
    private void mostrarPrecioUnitario(){
        try{
            String nombreMed = (String) vistaVentas.getCbxMedicamento().getSelectedItem();
            if(nombreMed == null || nombreMed.isEmpty()){
                vistaVentas.getTxtPrecio().setText("0.00");
                return;
            }
            
            Integer medicamentoId = medicamentosMap.get(nombreMed);
            if(medicamentoId == null){
                vistaVentas.getTxtPrecio().setText("0.00");
                return;
            }
            
            Medicamento med = medicamentoDAO.buscarPorId(medicamentoId);
            if(med != null && med.getPrecio() != null){
                vistaVentas.getTxtPrecio().setText(med.getPrecio().toString());
            }else{
                vistaVentas.getTxtPrecio().setText("0.00");
            }
        }catch(ErrorAccesoDatosExceptions e){
            vistaVentas.getTxtPrecio().setText("0.00");
        }
    }
    
    /**
     * Cargar clientes en el ComboBox
     */
    
    private void cargarClientes(){
        try{
            vistaVentas.getCbxClientes().removeAllItems();
            clientesMap.clear();
            
            List<Cliente> clientes = clienteDAO.listaDeClientes();
            
            if(clientes.isEmpty()){
                JOptionPane.showMessageDialog(vistaVentas, 
                        "¡ATENCION!", "Seleccione un Cliente.", 
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            for(Cliente c : clientes){
                String nombre = c.getNombre() + " " + c.getApellido();
                vistaVentas.getCbxClientes().addItem(nombre);
                clientesMap.put(nombre, c.getId());
            }
        }catch(ErrorAccesoDatosExceptions e){
            JOptionPane.showMessageDialog(vistaVentas, "Error al cargar Cliente: " + e.getMessage(),
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Cargar medicamento en el ComboBox
     */
    
    private void cargarMedicamentos(){
        try{
            vistaVentas.getCbxMedicamento().removeAllItems();
            medicamentosMap.clear();
            
            List<Medicamento> medicamentos = medicamentoDAO.listarMedicamentos();
            
            if(medicamentos.isEmpty()){
                JOptionPane.showMessageDialog(vistaVentas, 
                        "¡ATENCION!", "Seleccione un Medicamento.", 
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            for(Medicamento m : medicamentos){
                String nombre = m.getNombre();
                vistaVentas.getCbxMedicamento().addItem(nombre);
                medicamentosMap.put(nombre, m.getId());
            }
        }catch(ErrorAccesoDatosExceptions e){
            JOptionPane.showMessageDialog(vistaVentas, "Error al cargar Medicamento: " + e.getMessage(),
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == vistaVentas.getBtnAgregarDetalle()){
            agregarDetalle();
        }
        if(e.getSource() == vistaVentas.getBtnEliminarDetalle()){
            eliminarDetalle();
        }
        if(e.getSource() == vistaVentas.getBtnGuardarVenta()){
            guardarVentas();
        }
        if(e.getSource() == vistaVentas.getBtnCancelar() || e.getSource() == vistaVentas.getBtnNuevaVenta()){
            limpiarCampos();
        }
        if(e.getSource() == vistaVentas.getBtnMenuPrincipal()){
            this.vistaVentas.setVisible(false);
            ctMenu.volverAlMenu();
        }
    }
    
    private void agregarDetalle(){
        try{
            // Obtenemos los datos
            String nombreMed = (String) vistaVentas.getCbxMedicamento().getSelectedItem();
            String cantidadStr = vistaVentas.getTxtCantidad().getText().trim();
            
            // Validaciones
            if(nombreMed == null || nombreMed.isEmpty()){
                JOptionPane.showMessageDialog(vistaVentas, "¡ATENCION!", 
                        "Seleccione un Medicamento.", 
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if(cantidadStr.isEmpty()){
                JOptionPane.showMessageDialog(vistaVentas, "¡ATENCION!", 
                        "Ingrese una cantidad.", 
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int cantidad;
            
            try{
                cantidad = Integer.parseInt(cantidadStr);
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(vistaVentas, "¡ATENCION!", 
                        "Ingrese un numero positivo.", 
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Búsqueda de medicamento
            Integer medicamentoId = medicamentosMap.get(nombreMed);
            
            if(medicamentoId == null){
                JOptionPane.showMessageDialog(vistaVentas, "¡ATENCION!", 
                        "No se encontro el Medicamento seleccionado.", 
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Medicamento med = medicamentoDAO.buscarPorId(medicamentoId);
            DetalleVenta detalle = new DetalleVenta(med, cantidad);
            
            if(!ventaService.validarDetalles(detalle, med)){
                String mensaje = "Error en los datos del detalle:\n";
                
                if(cantidad <= 0){
                    mensaje +=  "- La cantidad debe ser mayor a cero\n";
                }
                if(med.getStock() < cantidad){
                    mensaje += "- Stock Insuficiente. Stock Disponible: " + med.getStock();
                }
                
                JOptionPane.showMessageDialog(vistaVentas, mensaje,
                        "Validacion", 
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Métodos que solo calcula subtotal e impuesto, sin descuento
            detalle = ventaService.calculoTotalDetallesDefualt(detalle);
            
            // Una vez hechos las validaciones necesarias se agregan los detalles a la lista.
            detallesVentas.add(detalle);
            
            actualizarTabla();
            calcularTotales();
            
            // Verificamos y mostramos un mensaje descuento positivo
            BigDecimal subtotalActual = obtenerSubtotalActual();
            if(ventaService.calificaParaDescuento(subtotalActual)){
                JOptionPane.showMessageDialog(vistaVentas, "¡ATENCION!",
                        "¡Compra apta para descuento!", 
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(ErrorAccesoDatosExceptions e){
            JOptionPane.showMessageDialog(vistaVentas, 
                    "¡ATENCION!",  
                    "Hubo un error al cargar detalles." + e.getMessage(),
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Método que elimina un detalle seleccionado de la tabla.
     */
    
    private void eliminarDetalle(){
        int fila = vistaVentas.getTblDetalles().getSelectedRow();
        
        if(fila < 0){
            JOptionPane.showMessageDialog(vistaVentas, "¡ATENCION!", 
                    "Seleccione el Detalle que desea eliminar", 
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirmacion = JOptionPane.showConfirmDialog(vistaVentas, 
                "¿Esta seguro que desea eliminar este detalle?", 
                "Confimar eliminacion",
                JOptionPane.YES_NO_OPTION);
        
        if(confirmacion == JOptionPane.YES_OPTION){
            detallesVentas.remove(fila);
            actualizarTabla();
            calcularTotales();
        }
    }
    
    /**
     * Método que actualiza la tabla con los detalles
     */
    
    private void actualizarTabla(){
        DefaultTableModel tablaDetalle = (DefaultTableModel) vistaVentas.getTblDetalles().getModel();
        tablaDetalle.setRowCount(0);
        
        for(DetalleVenta dv : detallesVentas){
            tablaDetalle.addRow(new Object[]{
               dv.getMedicamento().getNombre(),
                dv.getCantidad(),
                dv.getPrecioUnitario(),
                dv.getSubtotal(),
                dv.getImpuesto(),
                dv.getDescuento(),
                dv.getPrecioFinal()
            });
        }
    }
    
    /**
     * Método que utiliza VentaService para calcular los totales de ventas.
     * No aplica el descuento correspondiente hasta confirmar la venta.
     */
    
    private void calcularTotales(){
        try{
            BigDecimal subtotal = BigDecimal.ZERO;
            BigDecimal totalImpuesto = BigDecimal.ZERO;
            BigDecimal totalFinal = BigDecimal.ZERO;
            
            // Sumamos todos los totales
            for(DetalleVenta dv : detallesVentas){
                subtotal = subtotal.add(dv.getSubtotal());
                totalImpuesto = totalImpuesto.add(dv.getImpuesto());
                totalFinal = totalFinal.add(dv.getPrecioFinal());
            }
            
            /**Se actualizan los campos correspondientes**/
            vistaVentas.getTxtSubtotal().setText(subtotal.setScale(2, RoundingMode.HALF_UP).toString());
            vistaVentas.getTxtImpuesto().setText(totalImpuesto.setScale(2, RoundingMode.HALF_UP).toString());
            vistaVentas.getTxtTotal().setText(totalFinal.setScale(2, RoundingMode.HALF_UP).toString());
            
            // Mostramos los posibles descuentos
            if(ventaService.calificaParaDescuento(subtotal)){
                BigDecimal descuentoPotencial = subtotal.multiply(ventaService.getDescuentoDefault())
                        .setScale(2, RoundingMode.HALF_UP);
                vistaVentas.getTxtDescuentoTotal().setText("" + descuentoPotencial);
            }else{
                vistaVentas.getTxtDescuentoTotal().setText("0.00");
            }
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(vistaVentas, 
                    "¡ATENCION!" + e.getMessage(),
                    "Hubo un error al calcular los totales.",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Método que guarda las ventas en la Base de Datos
     */
    
    private void guardarVentas(){
        try{
            /** Se validan que hayan Ventas cargadas*/
            if(detallesVentas.isEmpty()){
                JOptionPane.showMessageDialog(vistaVentas, 
                        "¡ATENCION!", 
                        "¡Agregue al menos un medicamento a la lista.!",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            /** Se obtienen los datos del Cliente*/
            String nombreCliente = (String) vistaVentas.getCbxClientes().getSelectedItem();
            if(nombreCliente == null){
                JOptionPane.showMessageDialog(vistaVentas,
                        "¡ATENCION!",
                        "¡Seleccione un Cliente!",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            /** Se obtiene el ID del Cliente*/
            Integer clienteId = clientesMap.get(nombreCliente);
            if(clienteId == null){
                JOptionPane.showMessageDialog(vistaVentas,
                        "¡ATENCION!",
                        "El cliente seleccionado no existe en la Base de Datos.",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            /** Se aplican los descuentos si corresponden*/
            detallesVentas = ventaService.aplicarDescuentoDefault(detallesVentas);
            
            /** Se crea la venta y se agregan los detalles*/
            Venta ventas = new Venta(clienteId, new Timestamp(System.currentTimeMillis()));
            
            /** Se agregan y recalculan automaticamente cada detalle a la venta*/
            for(DetalleVenta detalles : detallesVentas){
                ventas.agregarDetalle(detalles);
            }
            
            /** Se utiliza el servicio para validar la venta*/
            if(!ventaService.validarVenta(ventas)){
                JOptionPane.showMessageDialog(vistaVentas, "¡ATENCION!",
                        "La venta contiene datos invalidos"
                                + "- Verifique los detalles.",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            /** Se obtiene y muestra un resumen de venta antes de confirmar*/
            String resumen = ventaService.obtenerResumenVenta(ventas);
            int confirmacion = JOptionPane.showConfirmDialog(vistaVentas, 
                    resumen + "\n\n ¿Desea confirmar esta venta?",
                    "Confirmar venta",
                    JOptionPane.YES_NO_OPTION);
            
            if(confirmacion != JOptionPane.YES_OPTION){
                return;
            }
            
            /** Se guarda la venta en la Base de Datos*/
            int idVenta = ventaDAO.insert(ventas);
            
            if(idVenta > 0){
                /** Una vez que se guarda la venta, se actualiza el stock*/
                for(DetalleVenta dv : detallesVentas){
                    dv.setId(idVenta);
                    detalleDAO.registrarVenta(dv);
                
                    Medicamento med = dv.getMedicamento();
                    int nuevoStock = med.getStock() - dv.getCantidad();
                    med.setStock(nuevoStock);
                    medicamentoDAO.update(med);
                }
            
                JOptionPane.showMessageDialog(vistaVentas,
                        "¡ATENCION!",
                        "Venta guardada con exito. ID de la venta: " + idVenta,
                        JOptionPane.INFORMATION_MESSAGE);
            
                /** Se limpian los campos*/
                limpiarCampos();
            }else{
                JOptionPane.showMessageDialog(vistaVentas,
                        "¡ATENCION!",
                        "Hubo un error al guardar la venta en la Base de Datos",
                        JOptionPane.ERROR_MESSAGE);
            }
        }catch(ErrorAccesoDatosExceptions e){
            JOptionPane.showMessageDialog(vistaVentas,
                    "¡ATENCION!",
                    "Hubo un error al guardar la venta" + e.getMessage(),
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Método para limpiar los campos de entrada
     */
    
    private void limpiarCampos(){
        vistaVentas.getTxtCantidad().setText("");
        vistaVentas.getTxtPrecio().setText("0.00");
        vistaVentas.getTxtSubtotal().setText("0.00");
        vistaVentas.getTxtTotal().setText("0.00");
        vistaVentas.getTxtDescuentoTotal().setText("0.00");
        vistaVentas.getTxtCantidad().requestFocus();
        
        /** Tambien se restaura el IVA por defecto desde el servicio*/
        vistaVentas.getTxtImpuesto().setText(
            ventaService.getIvaDefault()
                .multiply(BigDecimal.valueOf(100))
                .toString()
        );
        
        detallesVentas.clear();
        actualizarTabla();
        
        if(vistaVentas.getCbxClientes().getItemCount() > 0){
            vistaVentas.getCbxClientes().setSelectedIndex(0);
        }
        if(vistaVentas.getCbxMedicamento().getItemCount() > 0){
            vistaVentas.getCbxMedicamento().setSelectedIndex(0);
        }
    }
    
    /**
     * Método que obtiene el subtotal de todos los detalles
     */
    
    private BigDecimal obtenerSubtotalActual(){
        BigDecimal subtotalActual = BigDecimal.ZERO;
        
        for(DetalleVenta dv : detallesVentas){
            subtotalActual = subtotalActual.add(dv.getSubtotal());
        }
        
        return subtotalActual;
    }
}
