package com.mycompany.proyectfinal.modelo.dao;

// Se implementa la Interfaz

import com.mycompany.proyectfinal.modelo.conexionDB.Conexion;
import com.mycompany.proyectfinal.modelo.Medicamento;
import com.mycompany.proyectfinal.modelo.excepciones.ErrorAccesoDatosExceptions;

import java.sql.*;
import java.util.*;
import com.mycompany.proyectfinal.modelo.interfaces.IMedicamento;

public class MedicamentoDAO implements IMedicamento {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    
    @Override
    public boolean insert(Medicamento m) throws ErrorAccesoDatosExceptions{
        String sql = "INSERT INTO medicamento (nombre, precio, stock) VALUES (?,?,?)";
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, m.getNombre());
            ps.setDouble(2, m.getPrecio());
            ps.setInt(3, m.getStock());
            return ps.executeUpdate() > 0;
        }catch(SQLException e){
            throw new ErrorAccesoDatosExceptions("[ALERT]: Hubo un error al insertar datos de medicamento.", e);
        }
    }
    
    @Override
    public boolean update(Medicamento m)throws ErrorAccesoDatosExceptions{
        String sql = "UPDATE medicamento SET nombre=?, precio=?, stock=? WHERE id=?";
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, m.getNombre());
            ps.setDouble(2, m.getPrecio());
            ps.setInt(3, m.getStock());
            return ps.executeUpdate() > 0;
        }catch(SQLException e){
            throw new ErrorAccesoDatosExceptions("[ALERT]: Hubo un error al actualizar datos del medicamento.", e);
        }
    }
    
    @Override
    public boolean delete(int id) throws ErrorAccesoDatosExceptions{
        String sql = "DELETE FROM medicamento WHERE id=?";
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }catch(SQLException e){
            throw new ErrorAccesoDatosExceptions("[ALERT]: Hubo un error al tratar de eliminar datos del medicamento.", e);
        }
    }
    
    @Override
    public Medicamento buscarPorId(int id) throws ErrorAccesoDatosExceptions{
        String sql = "SELECT * FROM medicamento WHERE id=?";
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                return new Medicamento(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                );
            }
        }catch(SQLException e){
            throw new ErrorAccesoDatosExceptions("[ALERT]: Hubo un error al buscar datos del medicamento.", e);
        }
        return null;
    }
    
    @Override
    public List<Medicamento> listarMedicamentos() throws ErrorAccesoDatosExceptions{
        List<Medicamento> listaM = new ArrayList<>(); // listaM = Lista de Medicamentos
        String sql = "SELECT * FROM medicamento";
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                listaM.add(new Medicamento(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                ));
            }
        }catch(SQLException e){
            throw new ErrorAccesoDatosExceptions("[ALERT]: Hubo un error al listar datos de los medicamentos.", e);
        }
        return listaM;
    }
}
