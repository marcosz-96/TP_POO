package com.mycompany.proyectfinal.modelo;

public class DetalleVenta {
    // Se crean los atributos de la entidad
    private int id;
    private int ventaId;
    private int medicamentoId;
    private int cantidad;
    private double subtotal;
    
    // Se crea el constructor vacío
    public DetalleVenta(){}
    
    // Se crea el constructor con atributos
    public DetalleVenta(int id, int ventaId, int medicamentoId,int cantidad, double subtotal){
        this.id = id;
        this.ventaId = ventaId;
        this.medicamentoId = medicamentoId;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
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
    
    public double getSubtotal(){
        return subtotal;
    }
    
    public void setSubtotal(double subtotal){
        this.subtotal = subtotal;
    }
}
