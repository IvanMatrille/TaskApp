package ado.edu.itla.taskapp.repositorio;

import java.util.List;

import ado.edu.itla.taskapp.entidad.Usuario;

public interface UsuarioRepositorio {

    public boolean guardar(Usuario usuario);

    public boolean actualizar(Usuario usuario);

    public Usuario buscar(int id);

    public List<Usuario> buscarTecnicos();

    public List<Usuario> buscar(String nombre);
}
