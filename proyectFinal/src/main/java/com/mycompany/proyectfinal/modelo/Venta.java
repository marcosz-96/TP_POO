package com.mycompany.proyectfinal.modelo;

import java.math.BigDecimal;
import java.sql.Date;

public class Venta {
    // Se crean los atributos de la entidad
    private int id;
    private Date fecha;
    private int clienteId;
    private BigDecimal subtotalBruto; // Con esto se suman los subtotales brutos
    private BigDecimal impuesto; // Agregar impuesto
    private BigDecimal impuestoTotal;
    private BigDecimal importeDescuento; // Importe del descuento
    private BigDecimal descuentoTotal; // Total descontado
    private BigDecimal totalFinal; // Total final
    
    // Se crea el constructor vacío
    public Venta (){}
    
    // Se crea el constructor con los atributos
    public Venta(int id, Date fecha, int clienteId, BigDecimal subtotalBruto,
                BigDecimal impuesto, BigDecimal impuestoTotal, BigDecimal importeDescuento, 
                BigDecimal descuentoTotal, BigDecimal totalFinal){
        this.id = id;
        this.fecha = fecha;
        this.clienteId = clienteId;
        this.subtotalBruto = subtotalBruto;
        this.impuesto = impuesto;
        this.impuestoTotal = impuestoTotal;
        this.importeDescuento = importeDescuento;
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
    
    public BigDecimal getImpuesto(){
        return impuesto;
    }
    
    public void setImpuesto(BigDecimal impuesto){
        this.impuesto = impuesto;
    }
    
    public BigDecimal getImpuestoTotal(){
        return impuestoTotal;
    }
    
    public void setImpuestoTotal(BigDecimal impuestoTotal){
        this.impuestoTotal = impuestoTotal;
    }
    
    public BigDecimal getImporteDescuento(){
        return importeDescuento;
    }
    
    public void setImporteDescuento(BigDecimal importeDescuento){
        this.importeDescuento = importeDescuento;
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
}
