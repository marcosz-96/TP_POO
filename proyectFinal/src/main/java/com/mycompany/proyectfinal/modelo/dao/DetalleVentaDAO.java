package com.mycompany.proyectfinal.modelo.dao;

import com.mycompany.proyectfinal.modelo.conexionDB.Conexion;
import com.mycompany.proyectfinal.modelo.DetalleVenta;
import com.mycompany.proyectfinal.modelo.excepciones.ErrorAccesoDatosExceptions;

import java.sql.*;
import java.util.*;
import com.mycompany.proyectfinal.modelo.interfaces.IDetalleVenta;

// Se implementa la interfaz

public class DetalleVentaDAO implements IDetalleVenta {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    
    @Override
    public boolean insert (DetalleVenta dv) throws ErrorAccesoDatosExceptions{
        String sql = "INSERT INTO detalle_venta (venta_id, medicamento_id, cantidad, subtotal) "
                + "VALUES (?,?,?,?)";
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, dv.getVentaId());
            ps.setInt(2, dv.getMedicamentoId());
            ps.setInt(3, dv.getCantidad());
            ps.setDouble(4, dv.getSubtotal());
            ps.executeUpdate();
            return true;
        }catch(SQLException e){
            throw new ErrorAccesoDatosExceptions("[ALERT]: Hubo un error al insertar detalles de venta.", e);
        }
    }
    
    @Override
    public List<DetalleVenta> listaDetallesDeVenta(int venta_id) throws ErrorAccesoDatosExceptions{
        List<DetalleVenta> listaDV = new ArrayList(); // listaDV = Lista de Detalles de Ventas
        String sql = "SELECT * FROM detalle_venta WHERE id=?";
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, venta_id);
            rs = ps.executeQuery();
            while(rs.next()){
                listaDV.add(new DetalleVenta(
                        rs.getInt("id"),
                        rs.getInt("venta_id"),
                        rs.getInt("medicamento_id"),
                        rs.getInt("cantidad"),
                        rs.getDouble("subtotal")
                ));
            }
        }catch(SQLException e){
            throw new ErrorAccesoDatosExceptions("[ALERT]: Hubo un error al listar los detalles de ventas.", e);
        }
        return listaDV;
    }
}
