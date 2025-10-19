package com.mycompany.proyectfinal.modelo.dao;

// Se implementa la interfaz

import com.mycompany.proyectfinal.modelo.conexionDB.Conexion;
import com.mycompany.proyectfinal.modelo.Venta;
import com.mycompany.proyectfinal.modelo.excepciones.ErrorAccesoDatosExceptions;

import java.sql.*;
import java.util.*;
import com.mycompany.proyectfinal.modelo.interfaces.IVenta;
import java.math.BigDecimal;


public class VentaDAO implements IVenta{
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    
    @Override
    public int insert(Venta v) throws ErrorAccesoDatosExceptions{
        String sql = "INSERT INTO venta(fecha, cliente_id, subtotal, impuesto total"
                + " descuento total, total) VALUES(?,?,?,?,?,?,?,?)";
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            if(v.getFecha() != null){
                ps.setTimestamp(1, new Timestamp(v.getFecha().getTime()));
            } else{
                ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            }
            ps.setInt(2, v.getClienteId());
            // Nos aseguramos que el valor no sea nulo
            ps.setBigDecimal(3, v.getSubtotalBruto() != null ? v.getSubtotalBruto() : BigDecimal.ZERO);
            ps.setBigDecimal(5, v.getImpuestoTotal() != null ? v.getImpuestoTotal() : BigDecimal.ZERO);
            ps.setBigDecimal(7, v.getDescuentoTotal() != null ? v.getDescuentoTotal() : BigDecimal.ZERO);
            ps.setBigDecimal(8, v.getTotalFinal() != null ? v.getTotalFinal() : BigDecimal.ZERO);
            
            int filasAfectadas = ps.executeUpdate();
            if(filasAfectadas == 0){
                throw new ErrorAccesoDatosExceptions("¡ATENCION! No se registró lo venta.");
            }
            
            rs = ps.getGeneratedKeys();
            if(rs.next()){
                return rs.getInt(1);
            }
        }catch(SQLException e){
            throw new ErrorAccesoDatosExceptions("¡ATENCION! Hubo un error al registrar la venta.", e);
        }
        return -1;
    }
    
    @Override
    public Venta buscarVentaPorId(int id) throws ErrorAccesoDatosExceptions{
        String sql = "SELECT * FROM venta WHERE id = ?";
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                return new Venta(
                    rs.getInt("id"),
                    rs.getDate("fecha"),
                    rs.getInt("cliente_id"),
                    rs.getBigDecimal("subtotal"),
                    rs.getBigDecimal("impuesto total"),
                    rs.getBigDecimal("descuento total"),
                    rs.getBigDecimal("total")
                );
            }
        }catch(SQLException e){
            throw new ErrorAccesoDatosExceptions("¡ATENCION! Hubo un error al buscar datos de venta.", e);
        }
        return null;
    }
    
    @Override
    public List<Venta> listaDeVentas()throws ErrorAccesoDatosExceptions{
        List<Venta> listaV = new ArrayList(); // listaV = Lista de Ventas
        String sql = "SELECT * FROM venta";
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                listaV.add(new Venta(
                    rs.getInt("id"),
                    rs.getDate("fecha"),
                    rs.getInt("cliente_id"),
                    rs.getBigDecimal("subtotal"),
                    rs.getBigDecimal("impuesto total"),
                    rs.getBigDecimal("descuento total"),
                    rs.getBigDecimal("total")
                ));
            }
        }catch(SQLException e){
            throw new ErrorAccesoDatosExceptions("¡ATENCION! Hubo un error al buscar lista de ventas.", e);
        }
        return listaV;
    }
}
