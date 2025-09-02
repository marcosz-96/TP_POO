package com.mycompany.proyectfinal.modelo;

public class Medicamento {
    // Creamos los atributos de la entidad
    private int id;
    private String nombre;
    private double precio;
    private int stock;
    
    // Creamos un constructor vacío
    public Medicamento(){}
    
    // Creamos un constructor que referencia a los atributos
    public Medicamento(int id, String nombre, double precio, int stock){
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }
    
    // Creamos los Getters y Setters de los mismos
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public double getPrecio(){
        return precio;
    }
    
    public void setPrecio(double precio){
        this.precio = precio;
    }
    
    public int getStock(){
        return stock;
    }
    
    public void setStock(int stock){
        this.stock = stock;
    }
}
