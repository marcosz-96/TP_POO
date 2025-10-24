package com.mycompany.proyectfinal.modelo.dao;

import com.mycompany.proyectfinal.modelo.conexionDB.Conexion;
import com.mycompany.proyectfinal.modelo.DetalleVenta;
import com.mycompany.proyectfinal.modelo.excepciones.ErrorAccesoDatosExceptions;

import java.sql.*;
import java.util.*;
import com.mycompany.proyectfinal.modelo.interfaces.IDetalleVenta;

/**
 * Clase DAO (Data Access Object) para gestionar las operaciones de base de datos
 * relacionadas con los detalles de venta.
 * Implementa el patrón DAO para separar la lógica de acceso a datos.
 * 
 * @author Gomez Marco, Courel Brian, Lairana Rocio
 * @version 1.1
 */

public class DetalleVentaDAO implements IDetalleVenta {
    
    
    /**
     * Registra un detalle de venta en la base de datos.
     * Inserta todas las propiedades del detalle incluyendo precio unitario, subtotal,
     * impuesto, descuento y precio final.
     * 
     * @param dv Objeto DetalleVenta a registrar
     * @return true si se insertó correctamente, false en caso contrario
     * @throws ErrorAccesoDatosExceptions Si ocurre un error durante la inserción
     */
    
    @Override
    public boolean registrarVenta (DetalleVenta dv) throws ErrorAccesoDatosExceptions{
        String sql = "INSERT INTO detalle_venta (venta_id, medicamento_id, cantidad, precio_unitario, subtotal, impuesto, descuento, total) "
                + "VALUES (?,?,?,?,?,?,?,?)";
        try(Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            
            ps.setInt(1, dv.getVentaId());
            ps.setInt(2, dv.getMedicamentoId());
            ps.setInt(3, dv.getCantidad());
            ps.setBigDecimal(4, dv.getPrecioUnitario());
            ps.setBigDecimal(5, dv.getSubtotal());
            ps.setBigDecimal(6, dv.getImpuesto());
            ps.setBigDecimal(7, dv.getDescuento());
            ps.setBigDecimal(8, dv.getPrecioFinal());
            
            ps.executeUpdate();
            return true;
        
        }catch(SQLException e){
            throw new ErrorAccesoDatosExceptions("¡ATENCION! Hubo un error al insertar detalles de venta.", e);
        }
    }
    
    /**
     * Método que devuelve los detalles de venta por su ID
     * @param venta_id
     * @return 
     * @throws ErrorAccesoDatosExceptions Si aparece un error durante la consulta
     */
    
    @Override
    public List<DetalleVenta> listaDetallesDeVenta(int venta_id) throws ErrorAccesoDatosExceptions{
        List<DetalleVenta> listaDV = new ArrayList<>();
        String sql = "SELECT * FROM detalle_venta WHERE venta_id = ?";
        
        try(Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            
            ps.setInt(1, venta_id);
            
            try(ResultSet rs = ps.executeQuery()){
            
                while(rs.next()){
                listaDV.add(new DetalleVenta(
                        rs.getInt("id"),
                        rs.getInt("venta_id"),
                        rs.getInt("medicamento_id"),
                        rs.getInt("cantidad"),
                        rs.getBigDecimal("precio_unitario"),
                        rs.getBigDecimal("subtotal"),
                        rs.getBigDecimal("impuesto"),
                        rs.getBigDecimal("descuento"),
                        rs.getBigDecimal("total")
                    ));
                }
            }
        }catch(SQLException e){
            throw new ErrorAccesoDatosExceptions("¡ATENCION! Hubo un error al listar los detalles de ventas.", e);
        }
        return listaDV;
    }
}