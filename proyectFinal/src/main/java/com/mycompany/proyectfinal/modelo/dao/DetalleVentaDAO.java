package com.mycompany.proyectfinal.modelo.dao;

import com.mycompany.proyectfinal.modelo.conexionDB.Conexion;
import com.mycompany.proyectfinal.modelo.DetalleVenta;
import com.mycompany.proyectfinal.modelo.excepciones.ErrorAccesoDatosExceptions;

import java.sql.*;
import java.util.*;
import com.mycompany.proyectfinal.modelo.interfaces.IDetalleVenta;
import java.math.BigDecimal;

// Se implementa la interfaz

public class DetalleVentaDAO implements IDetalleVenta {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    
    /**
     * Método sobreescrito desde la interfaz que registra y retorna detalles de ventas
     * @param dv
     * @return
     * @throws ErrorAccesoDatosExceptions 
     */
    
    @Override
    public boolean registrarVenta (DetalleVenta dv) throws ErrorAccesoDatosExceptions{
        String sql = "INSERT INTO detalle_venta (venta_id, medicamento_id, cantidad, precio unitario, subtotal, impuesto, descuento, total) "
                + "VALUES (?,?,?,?)";
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
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
     * @throws com.mycompany.proyectfinal.modelo.excepciones.ErrorAccesoDatosExceptions 
     */
    
    @Override
    public List<DetalleVenta> listaDetallesDeVenta(int venta_id) throws ErrorAccesoDatosExceptions{
        List<DetalleVenta> listaDV = new ArrayList<>();
        String sql = "SELECT * FROM detalle_venta WHERE venta_id = ?";
        
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
                        rs.getBigDecimal("precio unitario"),
                        rs.getBigDecimal("subtotal"),
                        rs.getBigDecimal("impuesto"),
                        rs.getBigDecimal("descuento"),
                        rs.getBigDecimal("total")
                ));
            }
        }catch(SQLException e){
            throw new ErrorAccesoDatosExceptions("¡ATENCION! Hubo un error al listar los detalles de ventas.", e);
        }
        return listaDV;
    }
    
    /**
     * Método para calcular el subtotal, impuesto, descuentos y precio final,
     * antes de guardar el regitro de los detalles de ventas,
     * aplicando un porcentaje de descuento si el total de la compra supera un cierto monto.
     * @param dV Detalles de Venta
     * @param pI Porcetaje de Impuesto
     * @param dM Descuento Minimo
     * @param mD Monto de Descuento
     * @return 
     */
    
    public DetalleVenta calcularTotales(DetalleVenta dV, BigDecimal pI, BigDecimal dM, BigDecimal mD){
        BigDecimal subtotal = dV.getPrecioUnitario().multiply(BigDecimal.valueOf(dV.getCantidad()));
        BigDecimal impuestos = subtotal.multiply(pI);
        BigDecimal totalFinal = subtotal.add(impuestos);
        
        BigDecimal descuentos = BigDecimal.ZERO;
        
        if(subtotal.compareTo(mD) >= 0){
            descuentos = subtotal.multiply(mD);
            totalFinal = totalFinal.subtract(descuentos);
        }
        
        dV.setSubtotal(subtotal);
        dV.setImpuesto(impuestos);
        dV.setDescuento(descuentos);
        dV.setPrecioFinal(totalFinal);
        
        return dV;
    }
}