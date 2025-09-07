package com.mycompany.proyectfinal.modelo.excepciones;

public class ErrorAccesoDatosExceptions extends Exception{
    public ErrorAccesoDatosExceptions(String msg){
        super(msg);
    }
    
    public ErrorAccesoDatosExceptions(String msg, Throwable causa){
        super(msg, causa);
    }
}
