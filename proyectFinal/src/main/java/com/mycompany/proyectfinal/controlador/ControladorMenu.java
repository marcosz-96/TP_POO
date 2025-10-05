package com.mycompany.proyectfinal.controlador;

import com.mycompany.proyectfinal.modelo.dao.ClienteDAO;
import com.mycompany.proyectfinal.modelo.dao.MedicamentoDAO;
import com.mycompany.proyectfinal.modelo.excepciones.ErrorAccesoDatosExceptions;
import com.mycompany.proyectfinal.vista.FrmCliente;
import com.mycompany.proyectfinal.vista.FrmMedicamento;
import com.mycompany.proyectfinal.vista.FrmMenu;
import com.mycompany.proyectfinal.vista.FrmReportes;
import com.mycompany.proyectfinal.vista.FrmVentas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class ControladorMenu implements ActionListener{
    private FrmMenu vista;
    
    public ControladorMenu(FrmMenu vista){
        this.vista = vista;
        
        this.vista.getBtnCliente().addActionListener(this);
        this.vista.getBtnInventario().addActionListener(this);
        this.vista.getBtnVentas().addActionListener(this);
        this.vista.getBtnInformes().addActionListener(this);
        
    }

    public ControladorMenu() {}
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == vista.getBtnCliente()){
            vista.setVisible(false);
            FrmCliente vtCliente = new FrmCliente();
            ClienteDAO clienteDAO = new ClienteDAO();
            try {
                ControladorCliente ctCliente = new ControladorCliente(vtCliente, clienteDAO);
            } catch (ErrorAccesoDatosExceptions ex) {
                Logger.getLogger(ControladorMenu.class.getName());
            }
            vtCliente.setVisible(true);
        }
        if(e.getSource() == vista.getBtnInventario()){
            vista.setVisible(false);
            FrmMedicamento vtMedicamento = new FrmMedicamento();
            MedicamentoDAO medicamentoDAO = new MedicamentoDAO();
            try {
                ControladorMedicamento ctMedicamento = new ControladorMedicamento(vtMedicamento, medicamentoDAO);
            } catch (ErrorAccesoDatosExceptions ex) {
                Logger.getLogger(ControladorMenu.class.getName());
            }
            vtMedicamento.setVisible(true);
        }
        if(e.getSource() == vista.getBtnVentas()){
            //vista.setVisible(false);
            FrmVentas vtVentas = new FrmVentas();
            vtVentas.setVisible(true);
        }
        if(e.getSource() == vista.getBtnInformes()){
            //vista.setVisible(false);
            FrmReportes vtInformes = new FrmReportes();
            vtInformes.setVisible(true);
        }
    }
}
