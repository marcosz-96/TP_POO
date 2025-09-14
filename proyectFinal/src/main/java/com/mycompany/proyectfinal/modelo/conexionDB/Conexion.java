package com.mycompany.proyectfinal.modelo.conexionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Se crea la clase que implementa la conceccion con la DB

public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/farmacia";
    private static final String USER = "root";
    private static final String PASS = "12345678";
    
      public static Connection getConnection(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("[ATENCION]: Conexion establecida con exito.");
        }catch(SQLException e){
            System.out.println("[ATENCION]: Error de conexion.");
        }
        return conn;
    }
}
