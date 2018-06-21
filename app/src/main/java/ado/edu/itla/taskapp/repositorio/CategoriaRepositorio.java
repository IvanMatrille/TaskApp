package ado.edu.itla.taskapp.repositorio;

import java.util.List;

import ado.edu.itla.taskapp.entidad.Categoria;

public interface CategoriaRepositorio {
    boolean guardar(Categoria categoria);

    boolean actualizar(Categoria categoria);

    Categoria buscar(int id);

    List<Categoria> buscar(String nombre);
}
