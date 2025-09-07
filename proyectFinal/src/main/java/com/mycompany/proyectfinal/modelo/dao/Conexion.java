package com.mycompany.proyectfinal.modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;

// Se crea la clase que implementa la conceccion con la DB

public class Conexion {
    private static final String URL = "jdbc:msql://localhot:3306/farmacia";
    private static final String USER = "root";
    private static final String PASS = "";
    
    public static Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        }catch(Exception e){
            System.out.println("[ALERT]: Error de coneccion: " + e.getMessage());
            return null;
        }
    }
}
