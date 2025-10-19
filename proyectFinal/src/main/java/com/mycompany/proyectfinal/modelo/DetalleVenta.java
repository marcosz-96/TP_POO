package com.mycompany.proyectfinal.modelo;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Se utiliza BigDecimal para realizar con más presición los cálculos 
 * monetarios.
 * Se utilizan los id de la clase medicamento para no tener que 
 * trabajar con el objeto completo.
 * @author Marco
 */

public class DetalleVenta {
    private int id;
    private int ventaId;
    private int medicamentoId;
    private Medicamento medicamento;
    private int cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
    private BigDecimal impuesto;
    private BigDecimal descuento;
    private BigDecimal precioFinal;
    
    /**
     * Se crea contructor que recibe todos los atributos como parámetros
     * @param id
     * @param ventaId
     * @param medicamentoId
     * @param cantidad
     * @param precioUnitario
     * @param subtotal
     * @param impuesto
     * @param descuento
     * @param precioFinal 
     */
    
    public DetalleVenta(int id, int ventaId, int medicamentoId, int cantidad, BigDecimal precioUnitario, BigDecimal subtotal, BigDecimal impuesto, BigDecimal descuento, BigDecimal precioFinal){
        this.id = id;
        this.ventaId = ventaId;
        this.medicamentoId = medicamentoId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
        this.impuesto = impuesto;
        this.descuento = descuento;
        this.precioFinal = precioFinal;
    }
    
    // Se crea el constructor seteando los valores para que no sean nulos.
    public DetalleVenta(){
        this.precioUnitario = BigDecimal.ZERO;
        this.precioFinal = BigDecimal.ZERO;
    }
    
    /**
     * Se crea un contructor de conveniencia que utiliza medicamentoId 
     * @param medicamentoId
     * @param cantidad
     * @param precioUnitario
     * @param precioFinal
     */
    
    public DetalleVenta(int medicamentoId, int cantidad, BigDecimal precioUnitario, BigDecimal precioFinal){
        this.medicamentoId = medicamentoId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario != null ? precioUnitario : BigDecimal.ZERO;
        calcularSubtotal();
    }
    
    /**
     * Se crea un constructor de conveniencia que utiliza un objeto Medicamento
     * @param medicamento
     * @param cantidad
     */
    
    public DetalleVenta(Medicamento medicamento, int cantidad){
        this.medicamento = medicamento;
        if(medicamento != null){
            this.medicamentoId = medicamento.getId();
            this.precioUnitario = medicamento.getPrecio() != null ? medicamento.getPrecio() : BigDecimal.ZERO;
        } else {
            this.precioUnitario = BigDecimal.ZERO;
        }
        this.cantidad = cantidad;
        calcularSubtotal();
    }
    
    /**
     * Método que calcula y setea el subtotal (precioUnitario * cantidad), 
     * redondeando a 2 decimales.
     */
    
    public void calcularSubtotal(){
        if(precioUnitario == null) precioUnitario = BigDecimal.ZERO;
        this.precioFinal = precioUnitario.multiply(BigDecimal.valueOf(cantidad))
                .setScale(2, RoundingMode.HALF_UP);
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
    
    public Medicamento getMedicamento(){
        return medicamento;
    }
    
    public void setMedicamento(Medicamento medicamento){
        this.medicamento = medicamento;
        if(medicamento != null){
            this.medicamentoId = medicamento.getId();
            if(medicamento.getPrecio() != null) this.precioUnitario = medicamento.getPrecio();
        }
        calcularSubtotal();
    }
     
    public int getCantidad(){
        return cantidad;
    }
    
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
        calcularSubtotal();
    }
    
    public BigDecimal getPrecioUnitario(){
        return precioUnitario;
    }
    
    public void setPrecioUnitario(BigDecimal precioUnitario){
        this.precioUnitario = precioUnitario != null ? precioUnitario : BigDecimal.ZERO;
        calcularSubtotal();
    }
    
    public BigDecimal getSubtotal(){
        return subtotal;
    }
    
    public void setSubtotal(BigDecimal subtotal){
        this.subtotal = subtotal;
    }
    
    public BigDecimal getImpuesto(){
        return impuesto;
    }
    
    public void setImpuesto (BigDecimal impuesto){
        this.impuesto = impuesto;
    }
    
    public BigDecimal getDescuento(){
        return descuento;
    }
    
    public void setDescuento(BigDecimal descuento){
        this.descuento = descuento;
    }
    
    public BigDecimal getPrecioFinal(){
        return precioFinal;
    }
    
    public void setPrecioFinal(BigDecimal precioFinal){
        this.precioFinal = precioFinal;
    }
}
