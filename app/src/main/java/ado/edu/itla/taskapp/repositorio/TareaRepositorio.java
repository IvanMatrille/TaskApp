package ado.edu.itla.taskapp.repositorio;

import java.util.List;

import ado.edu.itla.taskapp.entidad.Tarea;
import ado.edu.itla.taskapp.entidad.Usuario;

public interface TareaRepositorio {
    public boolean guardar(Tarea tarea);

    public Tarea buscar(int id);

    public List<Tarea> buscarAsignaA(Usuario usuario);

    public List<Tarea> buscarCreadaPor(Usuario usuario);

    public boolean actualizarEstado(Tarea estado);

}
