package com.mycompany.proyectfinal.modelo.interfaces;

import com.mycompany.proyectfinal.modelo.Medicamento;
import com.mycompany.proyectfinal.modelo.excepciones.ErrorAccesoDatosExceptions;
import java.util.List;

// Se crea la interfaz en la cual se implementa el CRUD para la entidad

public interface IMedicamento {
    public boolean insert(Medicamento m) throws ErrorAccesoDatosExceptions;
    public boolean update (Medicamento m) throws ErrorAccesoDatosExceptions;
    public boolean delete (int id) throws ErrorAccesoDatosExceptions;
    public Medicamento buscarPorId(int id) throws ErrorAccesoDatosExceptions;
    public List<Medicamento> listarMedicamentos() throws ErrorAccesoDatosExceptions;
}
