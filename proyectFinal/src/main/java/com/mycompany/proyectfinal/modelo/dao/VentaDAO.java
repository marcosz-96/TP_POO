package com.mycompany.proyectfinal.modelo.dao;

// Se implementa la interfaz

import com.mycompany.proyectfinal.modelo.conexionDB.Conexion;
import com.mycompany.proyectfinal.modelo.Venta;
import com.mycompany.proyectfinal.modelo.excepciones.ErrorAccesoDatosExceptions;

import java.sql.*;
import java.util.*;
import com.mycompany.proyectfinal.modelo.interfaces.IVenta;


public class VentaDAO implements IVenta{
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    
    @Override
    public int insert(Venta v) throws ErrorAccesoDatosExceptions{
        String sql = "INSERT INTO venta(fecha, cliente_id) VALUES(?,?)";
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, v.getFecha());
            ps.setInt(2, v.getClienteId());
            ps.executeUpdate();
            
            rs = ps.getGeneratedKeys();
            if(rs.next()){
                return rs.getInt(1);
            }
        }catch(SQLException e){
            throw new ErrorAccesoDatosExceptions("[ALERT]: Hubo un error al insertar datos de venta.", e);
        }
        return -1;
    }
    
    @Override
    public Venta buscarVentaPorId(int id) throws ErrorAccesoDatosExceptions{
        String sql = "SELECT * FROM venta WHERE id=?";
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                return new Venta(
                        rs.getInt("id"),
                        rs.getString("fecha"),
                        rs.getInt("cliente_id")
                );
            }
        }catch(SQLException e){
            throw new ErrorAccesoDatosExceptions("[ALERT]: Hubo un error al buscar datos de venta.", e);
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
                        rs.getString("fecha"),
                        rs.getInt("cliente_id")
                ));
            }
        }catch(SQLException e){
            throw new ErrorAccesoDatosExceptions("[ALERT]: Hubo un error al buscar lista de ventas.", e);
        }
        return listaV;
    }
}
