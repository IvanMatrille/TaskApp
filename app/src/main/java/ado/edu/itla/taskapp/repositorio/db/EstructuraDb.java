package ado.edu.itla.taskapp.repositorio.db;

public class EstructuraDb {
    public static final String TABLA_CATEGORIA = "CREATE TABLE categoria (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT)";
    public static final String TABLA_USUARIO = "CREATE TABLE usuario (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre VARCHAR, email TEXT, contrasena TEXT, tipoUsuario TEXT)";
}
