package com.mycompany.proyectfinal.controlador;

import com.mycompany.proyectfinal.vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorMenu implements ActionListener{
    private final FrmMenu vista;
    
    public ControladorMenu(FrmMenu vista){
        this.vista = vista;
        
        this.vista.getBtnCliente().addActionListener(this);
        this.vista.getBtnInventario().addActionListener(this);
        this.vista.getBtnVentas().addActionListener(this);
        this.vista.getBtnReportes().addActionListener(this);
        this.vista.getBtnSalir().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == vista.getBtnCliente()){
            new FrmCliente().setVisible(true);
        }
        if(e.getSource() == vista.getBtnInventario()){
            new FrmMedicamento().setVisible(true);
        }
        if(e.getSource() == vista.getBtnVentas()){
            new FrmVentas().setVisible(true);
        }
        if(e.getSource() == vista.getBtnReportes()){
            new FrmReportes().setVisible(true);
        }
        if(e.getSource() == vista.getBtnSalir()){
            System.exit(0);
        }
    }
}
