package ado.edu.itla.taskapp.repositorio;

import java.util.List;

import ado.edu.itla.taskapp.entidad.Tarea;

public interface TareaRepositorio {
    public boolean guardar(Tarea tarea);

    public Tarea buscar(int id);

    public List<Tarea> buscarAsignaA(Tarea tarea);

    public List<Tarea> buscarCreadaPor(Tarea tarea);

}
