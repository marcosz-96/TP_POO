package com.mycompany.proyectfinal.main;

import com.mycompany.proyectfinal.modelo.conexionDB.Conexion;
import com.mycompany.proyectfinal.vista.FrmCliente;
import com.mycompany.proyectfinal.vista.FrmMedicamento;
import com.mycompany.proyectfinal.vista.FrmMenu;
import com.mycompany.proyectfinal.vista.FrmReportes;
import com.mycompany.proyectfinal.vista.FrmVentas;

public class Main {

    public static void main(String[] args) {
        Conexion.getConnection();
        
        /*FrmMedicamento medicamentos = new FrmMedicamento();
        medicamentos.setVisible(true);
        
        FrmVentas ventas = new FrmVentas();
        ventas.setVisible(true);
        
        FrmCliente cliente = new FrmCliente();
        cliente.setVisible(true);*/
        
        FrmMenu menu = new FrmMenu();
        menu.setVisible(true);
        
        FrmReportes reportes = new FrmReportes();
        reportes.setVisible(true);
    }
}
