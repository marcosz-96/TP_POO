package com.mycompany.proyectfinal.modelo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una venta realizada en el sistema.
 * Contiene información de la venta y una lista de detalles (productos vendidos).
 * Calcula automáticamente los totales cuando se agregan detalles.
 * 
 * @author Gomez Marco, Courel Brian, Lairana Rocio
 */

public class Venta {
    private int id;
    private Timestamp fecha;
    private int clienteId;
    private BigDecimal subtotalBruto; 
    private BigDecimal impuestoTotal;
    private BigDecimal descuentoTotal; 
    private BigDecimal totalFinal; 
    
    // Lista de detalles de las ventas.
    
    private List<DetalleVenta> detalles = new ArrayList<>();
    
    /**
     * Contructor por defecto que inicializa los valores en cero
     */
    
    public Venta(){
        this.subtotalBruto = BigDecimal.ZERO;
        this.impuestoTotal = BigDecimal.ZERO;
        this.descuentoTotal = BigDecimal.ZERO;
        this.totalFinal = BigDecimal.ZERO;
    }
    
    /**
     * Constructor con cliente y fecha.
     * 
     * @param clienteId ID del cliente que realiza la compra
     * @param fecha Fecha de la venta
     */
    
    public Venta(int clienteId, Timestamp fecha){
        this();
        this.clienteId = clienteId;
        this.fecha = fecha;
    }
    
    /**
     * Constructor completo.
     * Usado principalmente para cargar ventas desde la base de datos.
     * 
     * @param id Identificador único de la venta
     * @param fecha Fecha de la venta
     * @param clienteId ID del cliente
     * @param subtotalBruto Subtotal antes de impuestos y descuentos
     * @param impuestoTotal Total de impuestos
     * @param descuentoTotal Total de descuentos
     * @param totalFinal Total final de la venta
     */
    
    public Venta(int id, Timestamp fecha, int clienteId, BigDecimal subtotalBruto, BigDecimal impuestoTotal, BigDecimal descuentoTotal, BigDecimal totalFinal){
        this.id = id;
        this.fecha = fecha;
        this.clienteId = clienteId;
        this.subtotalBruto = subtotalBruto;
        this.impuestoTotal = impuestoTotal;
        this.descuentoTotal = descuentoTotal;
        this.totalFinal = totalFinal;
    }
    
    // Se arman los Getters  y Setters
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public Timestamp getFecha(){
        return fecha;
    }
    
    public void setFecha(Timestamp fecha){
        this.fecha = fecha;
    }
    
    public int getClienteId(){
        return clienteId;
    }
    
    public void setClienteId(int clienteId){
        this.clienteId = clienteId;
    }
    
    public BigDecimal getSubtotalBruto(){
        return subtotalBruto;
    }
    
    public void setSubtotalBruto(BigDecimal subtotalBruto){
        this.subtotalBruto = subtotalBruto;
    }
    
    public BigDecimal getImpuestoTotal(){
        return impuestoTotal;
    }
    
    public void setImpuestoTotal(BigDecimal impuestoTotal){
        this.impuestoTotal = impuestoTotal;
    }
    
    public BigDecimal getDescuentoTotal(){
        return descuentoTotal;
    }
    
    public void setDescuentoTotal(BigDecimal descuentoTotal){
        this.descuentoTotal = descuentoTotal;
    }
    
    public BigDecimal getTotalFinal(){
        return totalFinal;
    }
    
    public void setTotalFinal(BigDecimal totalFinal){
        this.totalFinal = totalFinal;
    }
    
    /**
     * Método que obtiene una copia de la lista de los detalles de la venta.
     * @return 
     */
    
    public List<DetalleVenta> getDetalles(){
        return detalles;
    }
    
    /**
     * Método que agrega un detalle a la lista de ventas y recalcula los totales
     * Si el detalle es null, no se agrega nada.
     * 
     * @param detalles
     */
    
    public void agregarDetalle(DetalleVenta detalles){
        if(detalles != null){
            this.detalles.add(detalles);
            recalcularTotales();
        }
    }
    
    /**
     * Método que recalcula los totales de la venta sumando los valores de todos los detalles.
     * Este método se ejecuta automáticamente al agregar detalles.
     * Actualiza: subtotalBruto, impuestoTotal, descuentoTotal y totalFinal.
     */
    
    public void recalcularTotales(){
        BigDecimal nuevoSubtotal = BigDecimal.ZERO;
        BigDecimal nuevoImpuesto = BigDecimal.ZERO;
        BigDecimal nuevoDescuento = BigDecimal.ZERO;
        BigDecimal nuevoTotal = BigDecimal.ZERO;
        
        for(DetalleVenta d : detalles){
            nuevoSubtotal = nuevoSubtotal.add(d.getSubtotal());
            nuevoImpuesto = nuevoImpuesto.add(d.getImpuesto());
            nuevoDescuento = nuevoDescuento.add(d.getDescuento());
            nuevoTotal = nuevoTotal.add(d.getPrecioFinal());
        }
        
        this.subtotalBruto = nuevoSubtotal;
        this.impuestoTotal = nuevoImpuesto;
        this.descuentoTotal = nuevoDescuento;
        this.totalFinal = nuevoTotal;
    }
}
