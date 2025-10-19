package com.mycompany.proyectfinal.modelo;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Venta {
    // Se crean los atributos de la entidad
    private int id;
    private Date fecha;
    private int clienteId;
    private BigDecimal subtotalBruto; 
    private BigDecimal impuestoTotal;
    private BigDecimal descuentoTotal; 
    private BigDecimal totalFinal; 
    
    // Forma de declarar que las ventas pueden tener varios detalles
    
    private List<DetalleVenta> detalles = new ArrayList<>();
    
    // Constructores
    
    public Venta(){
        this.subtotalBruto = BigDecimal.ZERO;
        this.impuestoTotal = BigDecimal.ZERO;
        this.descuentoTotal = BigDecimal.ZERO;
        this.totalFinal = BigDecimal.ZERO;
    }
    
    public Venta(int clienteId, Date fecha){
        this();
        this.clienteId = clienteId;
        this.fecha = fecha;
    }
    
    public Venta(int id, Date fecha, int clienteId, BigDecimal subtotalBruto, BigDecimal impuestoTotal, BigDecimal descuentoTotal, BigDecimal totalFinal){
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
    
    public Date getFecha(){
        return fecha;
    }
    
    public void setFecha(Date fecha){
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
    
    public List<DetalleVenta> getDetalles(){
        return detalles;
    }
    
    /**
     * Método que agregar un detalle a la lista de ventas y recalcual los totales
     */
    
    public void agregarDetalle(DetalleVenta detalles){
        if(detalles != null){
            this.detalles.add(detalles);
            recalcularTotales();
        }
    }
    
    /**
     * Método para recalcular los valores de subtotal bruto, impuestos, descuentos y total final
     * en base a los detalles cargados en la venta.
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
