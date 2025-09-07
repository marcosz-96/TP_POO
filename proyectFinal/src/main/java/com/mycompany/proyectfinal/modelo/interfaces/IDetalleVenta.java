package com.mycompany.proyectfinal.modelo.interfaces;

import com.mycompany.proyectfinal.modelo.DetalleVenta;
import com.mycompany.proyectfinal.modelo.excepciones.ErrorAccesoDatosExceptions;
import java.util.List;

// Se crea la interfaz en la cual se implementa el CRUD para la entidad

public interface IDetalleVenta {
    public boolean insert(DetalleVenta dv) throws ErrorAccesoDatosExceptions;
    public List<DetalleVenta> listaDetallesDeVenta(int venta_id) throws ErrorAccesoDatosExceptions;
}
 