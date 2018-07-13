package ado.edu.itla.taskapp.repositorio;

import java.util.List;

import ado.edu.itla.taskapp.entidad.Usuario;

public interface UsuarioRepositorio {

    boolean guardar(Usuario usuario);

    boolean actualizar(Usuario usuario);

    Usuario buscar(int id);

    List<Usuario> buscar(String nombre);
}
