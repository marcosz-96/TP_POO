package com.mycompany.proyectfinal.modelo.interfaces;

import com.mycompany.proyectfinal.modelo.Cliente;
import com.mycompany.proyectfinal.modelo.excepciones.ErrorAccesoDatosExceptions;
import java.util.List;

// Se crea la interfaz en la cual se implementa el CRUD para la entidad

public interface ICliente {
    public boolean insert(Cliente c) throws ErrorAccesoDatosExceptions;
    public boolean update(Cliente c) throws ErrorAccesoDatosExceptions;
    public boolean delete(int id) throws ErrorAccesoDatosExceptions;
    public Cliente buscarPorId(int id) throws ErrorAccesoDatosExceptions;
    public List<Cliente> listaDeClientes() throws ErrorAccesoDatosExceptions;
}
