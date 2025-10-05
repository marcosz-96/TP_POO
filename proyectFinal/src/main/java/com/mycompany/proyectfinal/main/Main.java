package com.mycompany.proyectfinal.main;

import com.mycompany.proyectfinal.controlador.ControladorMenu;
import com.mycompany.proyectfinal.modelo.conexionDB.Conexion;
import com.mycompany.proyectfinal.modelo.excepciones.ErrorAccesoDatosExceptions;
import com.mycompany.proyectfinal.vista.FrmMenu;

public class Main {

    public static void main(String[] args) throws ErrorAccesoDatosExceptions {
        //Conexion.getConnection();
        FrmMenu menu = new FrmMenu();
        ControladorMenu cntMenu = new ControladorMenu(menu);
        menu.setVisible(true);
    }
}
