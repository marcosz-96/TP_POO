package com.mycompany.proyectfinal.controlador;

import com.mycompany.proyectfinal.modelo.Venta;
import com.mycompany.proyectfinal.modelo.DetalleVenta;
import com.mycompany.proyectfinal.modelo.Cliente;
import com.mycompany.proyectfinal.modelo.Medicamento;
import com.mycompany.proyectfinal.modelo.dao.ClienteDAO;
import com.mycompany.proyectfinal.modelo.dao.DetalleVentaDAO;
import com.mycompany.proyectfinal.modelo.dao.MedicamentoDAO;
import com.mycompany.proyectfinal.modelo.dao.VentaDAO;
import com.mycompany.proyectfinal.modelo.excepciones.ErrorAccesoDatosExceptions;
import com.mycompany.proyectfinal.vista.FrmReportes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Controlador para la gestión de reportes e historial de ventas.
 * Muestra todas las ventas realizadas con sus detalles y estadísticas generales.
 * 
 * @author Gomez Marco, Courel Brian, Lairana Rocio
 */
public class ControladorReporte implements ActionListener {
    
    private FrmReportes vista;
    private VentaDAO ventaDAO;
    private DetalleVentaDAO detalleDAO;
    private ClienteDAO clienteDAO;
    private MedicamentoDAO medicamentoDAO;
    private ControladorMenu ctMenu;
    
    private DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private DateTimeFormatter formatoFechas = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Map<String, List<Venta>> ventasPorFecha = new LinkedHashMap<>();
    private List<Venta> todasLasVentas = new ArrayList<>();
    
    /**
     * Constructor del controlador de reportes.
     * 
     * @param vista Vista de reportes
     * @param ctMenu
     * @param menuController Controlador del menú principal
     */
    public ControladorReporte(FrmReportes vista, ControladorMenu ctMenu) throws ErrorAccesoDatosExceptions{
        this.vista = vista;
        this.ventaDAO = new VentaDAO();
        this.detalleDAO = new DetalleVentaDAO();
        this.clienteDAO = new ClienteDAO();
        this.medicamentoDAO = new MedicamentoDAO();
        this.ctMenu = ctMenu;
        
        inicializarVista();
        registrarEventos();
        cargarFechasDisponibles();
        cargarHistorialCompleto();
    }

    /**
     * Configuración inicial de la vista.
     */
    private void inicializarVista() {
        // Configurar tabla como no editable
        vista.getTblHistorialVentas().setEnabled(true);
        vista.getTblHistorialVentas().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }


    /**
     * Registra los eventos de los componentes.
     */
    private void registrarEventos() {
        vista.getBtnVolverAlMenu().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnVolverAlMenu()) {
            ctMenu.volverAlMenu();
        }
    }

    
    /**
     * Método que carga las ventas por fecha
     */
    
    private void cargarFechasDisponibles(){
        try{
            vista.getCbxBuscarPorFecha().removeAllItems();
            ventasPorFecha.clear();
            
            todasLasVentas = ventaDAO.listaDeVentas();
            
            for (Venta venta : todasLasVentas) {
                LocalDate fecha = venta.getFecha().toLocalDateTime().toLocalDate();
                String fechaStr = fecha.format(formatoFecha);
                
                if (!ventasPorFecha.containsKey(fechaStr)) {
                    ventasPorFecha.put(fechaStr, new ArrayList<>());
                }
                ventasPorFecha.get(fechaStr).add(venta);
            }
            
            vista.getCbxBuscarPorFecha().addItem("Seleccione una fecha...");
            for (String fecha : ventasPorFecha.keySet()) {
                int cantidadVentas = ventasPorFecha.get(fecha).size();
                vista.getCbxBuscarPorFecha().addItem(fecha + " (" + cantidadVentas + " ventas)");
            }
            
            vista.getCbxBuscarPorFecha().setEnabled(true);
            
        } catch (ErrorAccesoDatosExceptions e) {
            JOptionPane.showMessageDialog(vista, 
                "Error al cargar el historial de ventas: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Método que filtra las ventas por fecha seleccionada
     */
    
    private void filtrarPorFecha(){
        try{
            String seleccionar = (String) vista.getCbxBuscarPorFecha().getSelectedItem();
            
            if(seleccionar == null || seleccionar.isEmpty()){
                JOptionPane.showMessageDialog(vista, 
                        "¡ATENCION!",
                        "Seleccione una fecha.",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String fechaSeleccionada = seleccionar.split(" \\/")[0];
            
            List<Venta> ventasFiltradas = ventasPorFecha.get(fechaSeleccionada);
            
            if(ventasFiltradas == null || ventasFiltradas.isEmpty()){
                JOptionPane.showMessageDialog(vista, "¡ATENCION!",
                        "La Fecha no tiene ventas registradas",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            cargarVentasEnTabla(ventasFiltradas);
            
        } catch(Exception e){
            JOptionPane.showMessageDialog(vista, "¡ATENCION!" + e.getMessage(),
                    "Hubo un error al filtrar por fecha.",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    /**
     * Carga el historial completo de ventas en la tabla.
     */
    private void cargarHistorialCompleto() {
        try {
            if(todasLasVentas == null || todasLasVentas.isEmpty()){
                todasLasVentas = ventaDAO.listaDeVentas();
            }
            
            if (todasLasVentas == null || todasLasVentas.isEmpty()) {
                JOptionPane.showMessageDialog(vista, 
                    "No hay ventas registradas en el sistema.", 
                    "Sin datos", 
                    JOptionPane.INFORMATION_MESSAGE);
                actualizarEstadisticas(0, BigDecimal.ZERO);
                return;
            }  
        } catch (ErrorAccesoDatosExceptions e) {
            JOptionPane.showMessageDialog(vista, 
                "Error al cargar el historial de ventas: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cargarVentasEnTabla(List<Venta> ventas){
        try{
            DefaultTableModel modelo = (DefaultTableModel) vista.getTblHistorialVentas().getModel();
            modelo.setRowCount(0);
            
            int totalVentas = 0;
            BigDecimal totalGenerado = BigDecimal.ZERO;
            
            for (Venta venta : ventas) {
                List<DetalleVenta> detalles = detalleDAO.listaDetallesDeVenta(venta.getId());
                
                if (detalles != null && !detalles.isEmpty()) {
                    Cliente cliente = clienteDAO.buscarPorId(venta.getClienteId());
                    String nombreCliente = (cliente != null) 
                        ? cliente.getApellido() + ", " + cliente.getNombre()
                        : "Cliente desconocido";
                    
                    String fechaFormateada = formatearFecha(venta.getFecha());
                    
                    for (DetalleVenta detalle : detalles) {
                        Medicamento medicamento = medicamentoDAO.buscarPorId(detalle.getMedicamentoId());
                        String nombreMedicamento = (medicamento != null) 
                            ? medicamento.getNombre()
                            : "Medicamento desconocido";
                        
                        BigDecimal porcDescuento = calcularPorcentajeDescuento(
                            detalle.getSubtotal(), 
                            detalle.getDescuento()
                        );
                        
                        modelo.addRow(new Object[]{
                            venta.getId(),
                            fechaFormateada,
                            nombreCliente,
                            nombreMedicamento,
                            formatearMoneda(detalle.getPrecioUnitario()),
                            detalle.getCantidad(),
                            formatearMoneda(detalle.getSubtotal()),
                            formatearMoneda(detalle.getImpuesto()),
                            porcDescuento.toString() + "%",
                            formatearMoneda(detalle.getDescuento()),
                            formatearMoneda(detalle.getPrecioFinal())
                        });
                    }
                    
                    totalVentas++;
                    totalGenerado = totalGenerado.add(venta.getTotalFinal());
                }
            }
            
            actualizarEstadisticas(totalVentas, totalGenerado);
            
        } catch (ErrorAccesoDatosExceptions e) {
            JOptionPane.showMessageDialog(vista, 
                "Error al cargar el historial de ventas en la tabla: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Formatea una fecha/hora para mostrar en la tabla.
     * 
     * @param fecha Fecha a formatear
     * @return String con la fecha formateada
     */
    private String formatearFecha(Timestamp fecha) {
        if (fecha == null) {
            return "Sin fecha";
        }
        return fecha.toLocalDateTime().format(formatoFecha);
    }

    /**
     * Formatea un valor monetario para mostrar.
     * 
     * @param valor Valor a formatear
     * @return String con el valor formateado
     */
    private String formatearMoneda(BigDecimal valor) {
        if (valor == null) {
            return "$0.00";
        }
        return "$" + valor.setScale(2, RoundingMode.HALF_UP).toString();
    }

    /**
     * Calcula el porcentaje de descuento aplicado.
     * 
     * @param subtotal Subtotal del detalle
     * @param descuento Monto del descuento
     * @return Porcentaje de descuento
     */
    private BigDecimal calcularPorcentajeDescuento(BigDecimal subtotal, BigDecimal descuento) {
        if (subtotal == null || descuento == null || 
            subtotal.compareTo(BigDecimal.ZERO) == 0 ||
            descuento.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        
        return descuento.divide(subtotal, 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100))
                .setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Actualiza los campos de estadísticas (cantidad de ventas y total generado).
     * 
     * @param cantidadVentas Cantidad total de ventas
     * @param totalGenerado Total generado en ventas
     */
    private void actualizarEstadisticas(int cantidadVentas, BigDecimal totalGenerado) {
        vista.getCantidadVentas().setText(String.valueOf(cantidadVentas));
        vista.getTotalGenerado().setText(formatearMoneda(totalGenerado));
    }
}