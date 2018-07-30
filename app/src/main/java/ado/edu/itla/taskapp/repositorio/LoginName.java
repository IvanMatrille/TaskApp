package ado.edu.itla.taskapp.repositorio;

import ado.edu.itla.taskapp.entidad.Usuario;

public class LoginName {
    private static final LoginName ourInstance = new LoginName();

    private Usuario usuario = new Usuario();

    public static LoginName getInstance() {
        return ourInstance;
    }

    private LoginName() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}