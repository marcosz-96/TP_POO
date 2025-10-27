package com.mycompany.proyectfinal.modelo.dao;

import com.mycompany.proyectfinal.modelo.conexionDB.Conexion;
import com.mycompany.proyectfinal.modelo.Venta;
import com.mycompany.proyectfinal.modelo.excepciones.ErrorAccesoDatosExceptions;

import java.sql.*;
import java.util.*;
import com.mycompany.proyectfinal.modelo.interfaces.IVenta;
import java.math.BigDecimal;

/**
 * Clase DAO (Data Access Object) para gestionar las operacaiones de la base de datos 
 * relacionadas con la entidad Venta.
 * Implementa el patron DAO para separar la lógica de acceso a datos de la lógica de negocio.
 * @author Gomez Marco, Courel Brian, Lairana Rocio
 */


public class VentaDAO implements IVenta{
    
    /**
     * Método que nserta una nueva venta en la base de datos.
     * Retorna el ID generado automáticamente por la base de datos.
     * 
     * @param v Objeto Venta a insertar
     * @return ID generado de la venta, o -1 si no se pudo obtener
     * @throws ErrorAccesoDatosExceptions Si ocurre un error durante la inserción
     */
    
    @Override
    public int insert(Venta v) throws ErrorAccesoDatosExceptions{
        String sql = "INSERT INTO venta(fecha_hora, cliente_id, subtotal, impuesto_total"
                + " descuento_total, total) VALUES(?,?,?,?,?,?)";
        try(Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            
            // Genera una fecha al momento de realizar una venta.
            if(v.getFecha() != null){
                ps.setTimestamp(1, new Timestamp(v.getFecha().getTime()));
            } else{
                ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            }
            
            // Se establencen  los parámetros
            ps.setInt(2, v.getClienteId());
            ps.setBigDecimal(3, v.getSubtotalBruto() != null ? v.getSubtotalBruto() : BigDecimal.ZERO);
            ps.setBigDecimal(4, v.getImpuestoTotal() != null ? v.getImpuestoTotal() : BigDecimal.ZERO);
            ps.setBigDecimal(5, v.getDescuentoTotal() != null ? v.getDescuentoTotal() : BigDecimal.ZERO);
            ps.setBigDecimal(6, v.getTotalFinal() != null ? v.getTotalFinal() : BigDecimal.ZERO);
            
            
            // Se ejecuta la inserción
            int filasAfectadas = ps.executeUpdate();
            if(filasAfectadas == 0){
                throw new ErrorAccesoDatosExceptions("¡ATENCION! No se registró lo venta.");
            }
            
            try(ResultSet rs = ps.getGeneratedKeys();){
                if(rs.next()){
                    return rs.getInt(1);
                }
            }
        }catch(SQLException e){
            throw new ErrorAccesoDatosExceptions("¡ATENCION! Hubo un error al registrar la venta.", e);
        }
        return -1;
    }
    
    /**
     * Método que busca una venta específica por su ID.
     * 
     * @param id ID de la venta a buscar
     * @return Objeto Venta encontrado, o null si no existe
     * @throws ErrorAccesoDatosExceptions Si ocurre un error durante la búsqueda
     */
    
    @Override
    public Venta buscarVentaPorId(int id) throws ErrorAccesoDatosExceptions{
        String sql = "SELECT * FROM venta WHERE id = ?";
        
        try(Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            
            ps.setInt(1, id);
            
            try(ResultSet rs = ps.executeQuery()){
            
                if(rs.next()){
                    return new Venta(
                        rs.getInt("id"),
                        rs.getTimestamp("fecha_hora"),
                        rs.getInt("cliente_id"),
                        rs.getBigDecimal("subtotal"),
                        rs.getBigDecimal("impuesto_total"),
                        rs.getBigDecimal("descuento_total"),
                        rs.getBigDecimal("total")
                    );
                }
            }
        }catch(SQLException e){
            throw new ErrorAccesoDatosExceptions("¡ATENCION! Hubo un error al buscar datos de venta.", e);
        }
        return null;
    }
    
    /**
     * Método que obtiene una lista de todas las ventas registradas en la base de datos.
     * 
     * @return Lista de objetos Venta
     * @throws ErrorAccesoDatosExceptions Si ocurre un error durante la consulta
     */
    
    @Override
    public List<Venta> listaDeVentas()throws ErrorAccesoDatosExceptions{
        List<Venta> listaV = new ArrayList(); // listaV = Lista de Ventas
        String sql = "SELECT * FROM venta ORDER BY fecha_hora DESC";
        
        try(Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
                
            while(rs.next()){
                listaV.add(new Venta(
                    rs.getInt("id"),
                    rs.getTimestamp("fecha_hora"),
                    rs.getInt("cliente_id"),
                    rs.getBigDecimal("subtotal"),
                    rs.getBigDecimal("impuesto_total"),
                    rs.getBigDecimal("descuento_total"),
                    rs.getBigDecimal("total")
                ));
            }
        }catch(SQLException e){
            throw new ErrorAccesoDatosExceptions("¡ATENCION! Hubo un error al buscar lista de ventas.", e);
        }
        return listaV;
    }
}
