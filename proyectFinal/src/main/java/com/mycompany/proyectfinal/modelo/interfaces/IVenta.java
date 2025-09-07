package com.mycompany.proyectfinal.modelo.interfaces;

import com.mycompany.proyectfinal.modelo.Venta;
import com.mycompany.proyectfinal.modelo.excepciones.ErrorAccesoDatosExceptions;
import java.util.List;

// Se crea la interfaz en la cual se implementa el CRUD para la entidad

public interface IVenta {
    public int insert(Venta v) throws ErrorAccesoDatosExceptions;
    public Venta buscarVentaPorId(int id) throws ErrorAccesoDatosExceptions;
    public List<Venta> listaDeVentas() throws ErrorAccesoDatosExceptions;
}
