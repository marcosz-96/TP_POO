package com.mycompany.proyectfinal.modelo;

public class Cliente {
    // Creamos los atributos de la entidad
    private int id;
    private String nombre;
    private String apellido;
    private int dni;
    private String direccion;
    private long telefono;
    
    // Se crea el constructor vacío
    public Cliente(){}
    
    // Se crea el constructor que referencia a los atributos
    public Cliente(int id, String nombre, String apellido, int dni, String direccion, long telefono){
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.direccion = direccion;
        this.telefono = telefono;
    }
    
    // Se crean los Getters y Setters
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
    
    public String getApellido(){
        return apellido;
    }
    
    public void setApellido(String apellido){
        this.apellido = apellido;
    }
    
    public int getDni(){
        return dni;
    }
    
    public void setDni(int dni){
        this.dni = dni;
    }
    
    public String getDireccion(){
        return direccion;
    }
    
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
    
    public long getTelefono(){
        return telefono;
    }
    
    public void setTelefono(long telefono){
        this.telefono = telefono;
    }
}
