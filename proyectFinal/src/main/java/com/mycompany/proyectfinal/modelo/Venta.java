package com.mycompany.proyectfinal.modelo;

public class Venta {
    // Se crean los atributos de la entidad
    private int id;
    private String fecha;
    private int clienteId;
    
    // Se crea el constructor vacío
    public Venta (){}
    
    // Se crea el constructor con los atributos
    public Venta(int id, String fecha, int clienteId){
        this.id = id;
        this.fecha = fecha;
        this.clienteId = clienteId;
    }
    
    // Se arman los Getters  y Setters
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getFecha(){
        return fecha;
    }
    
    public void setFecha(String fecha){
        this.fecha = fecha;
    }
    
    public int getClienteId(){
        return clienteId;
    }
    
    public void setClienteId(int clienteId){
        this.clienteId = clienteId;
    }
}
