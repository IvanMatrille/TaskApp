package ado.edu.itla.taskapp.repositorio.db;

public class EstructuraDb {
    public static final String TABLA_CATEGORIA = "CREATE TABLE categoria (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT)";
    public static final String TABLA_USUARIO = "CREATE TABLE usuario (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre VARCHAR, email TEXT, contrasena TEXT, tipoUsuario TEXT)";
    public static final String TABLA_TAREA = "CREATE TABLE tarea (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, nombre TEXT, descripcion TEXT, fecha NUMERIC, fecha_completado NUMERIC, estado TEXT NOT NULL DEFAULT 'pendiente', usuario_creador_id INTEGER NOT NULL, usuario_asignado_id INTEGER NOT NULL, categoria_id INTEGER)";


}
