package com.mycompany.proyectfinal.main;

import com.mycompany.proyectfinal.modelo.conexionDB.Conexion;
import com.mycompany.proyectfinal.vista.FrmVentas;

public class Main {

    public static void main(String[] args) {
        Conexion.getConnection();
        
        FrmVentas ventas = new FrmVentas();
        
        
        ventas.setVisible(true);
    }
}
