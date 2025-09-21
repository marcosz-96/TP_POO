package com.mycompany.proyectfinal.main;

import com.mycompany.proyectfinal.modelo.excepciones.ErrorAccesoDatosExceptions;
import com.mycompany.proyectfinal.vista.FrmMenu;

public class Main {

    public static void main(String[] args) throws ErrorAccesoDatosExceptions {
        //Conexion.getConnection();
        FrmMenu verMenu = new FrmMenu();
        verMenu.setVisible(true);
        
    }
}
