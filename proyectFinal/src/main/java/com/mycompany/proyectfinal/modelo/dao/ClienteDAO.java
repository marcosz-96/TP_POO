package com.mycompany.proyectfinal.modelo.dao;

import com.mycompany.proyectfinal.modelo.Cliente;
import com.mycompany.proyectfinal.modelo.excepciones.ErrorAccesoDatosExceptions;

import java.sql.*;
import java.util.*;
import com.mycompany.proyectfinal.modelo.interfaces.ICliente;



// Se impementa la interfaz

public class ClienteDAO implements ICliente{
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    
    @Override
    public boolean insert(Cliente c) throws ErrorAccesoDatosExceptions{
        String sql = "INSERT INTO cliente (nombre, apellido, dni, direccion, telefono) VALUES (?,?,?,?,?)";
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellido());
            ps.setInt(3, c.getDni());
            ps.setString(4, c.getDireccion());
            ps.setLong(5, c.getTelefono());
            return ps.executeUpdate() > 0;
        }catch(SQLException e){
            throw new ErrorAccesoDatosExceptions("[ALERT]: Hubo un error al insertar datos del cliente.", e);
        }
    }
    
    @Override
    public boolean update(Cliente c) throws ErrorAccesoDatosExceptions{
        String sql = "UPDATE cliente SET nombre=?, apellido=?, dni=?, direccion=?, telefono=? WHERE id=?";
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellido());
            ps.setInt(3, c.getDni());
            ps.setString(4, c.getDireccion());
            ps.setLong(5, c.getTelefono());
            ps.setInt(6, c.getId());
            return ps.executeUpdate() > 0;
        }catch(SQLException e){
            throw new ErrorAccesoDatosExceptions("[ALERT]: Hubo un error al actualizar datos del cliente.", e);
        }
    }
    
    @Override
    public boolean delete(int id) throws ErrorAccesoDatosExceptions{
        String sql = "DELETE FROM cliente WHERE id=?";
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }catch(SQLException e){
            throw new ErrorAccesoDatosExceptions("[ALERT]: Hubo un error al querer eliminar datos del cliente.", e);
        }
    }
    
    @Override
    public Cliente buscarPorId(int id) throws ErrorAccesoDatosExceptions{
        String sql = "SELECT * FROM cliente WHERE id=?";
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeQuery();
            if(rs.next()){
                return new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("dni"),
                        rs.getString("direccion"),
                        rs.getLong("telefono")
                );
            }
        }catch(SQLException e){
            throw new ErrorAccesoDatosExceptions("[ALERT]: Hubo un error al buscar datos del cliente.", e);
        }
        return null;
    }
    
    @Override
    public List<Cliente> listaDeClientes() throws ErrorAccesoDatosExceptions{
        List<Cliente> listaC = new ArrayList(); // listaC = Lista de Clientes
        String sql = "SELECT * FROM cliente";
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeQuery();
            while(rs.next()){
                listaC.add(new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("dni"),
                        rs.getString("direccion"),
                        rs.getLong("telefono")
                ));
            }
        }catch(SQLException e){
            throw new ErrorAccesoDatosExceptions("[ALERT]: Hubo un error al buscar la lista de cliente.", e);
        }
        return listaC;
    }
}
