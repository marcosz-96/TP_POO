package com.mycompany.proyectfinal.modelo;

import java.math.BigDecimal;

public class DetalleVenta {
    // Se crean los atributos de la entidad
    private int id;
    private int ventaId;
    private int medicamentoId;
    private int cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal precioFinal;
    
    // Se crea el constructor vacío
    public DetalleVenta(){}
    
    // Se crea el constructor con atributos
    public DetalleVenta(int id, int ventaId, int medicamentoId,int cantidad, BigDecimal precioUnitario, BigDecimal precioFinal){
        this.id = id;
        this.ventaId = ventaId;
        this.medicamentoId = medicamentoId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.precioFinal = precioFinal;
    }
    
    // Se crean los Getters y Setters
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getVentaId(){
        return ventaId;
    }
    
    public void setVentaId(int ventaId){
        this.ventaId = ventaId;
    }
    
    public int getMedicamentoId(){
        return medicamentoId;
    }
    
    public void setMedicamentoId(int medicamentoId){
        this.medicamentoId = medicamentoId;
    }
    
    public int getCantidad(){
        return cantidad;
    }
    
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
    
    public BigDecimal getPrecioUnitario(){
        return precioUnitario;
    }
    
    public void setPrecioUnitario(BigDecimal precioUnitario){
        this.precioUnitario = precioUnitario;
    }
    
    public BigDecimal getPrecioFinal(){
        return precioFinal;
    }
    
    public void setPrecioFinal(BigDecimal precioFinal){
        this.precioFinal = precioFinal;
    }
}
