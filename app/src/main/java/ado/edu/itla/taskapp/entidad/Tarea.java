package ado.edu.itla.taskapp.entidad;

import java.util.Date;

public class Tarea {

    public enum tareaestado{
        en_proceso,
        pendiente,
        terminado
    }

    private Integer id;
    private String nombre;
    private String descripcion;
    private Date fecha;
    private Date fechaterminado;
    private tareaestado estado;
    private int categoria;
    private int usuarioCreador;
    private int usuarioAsignado;

    public Tarea(String nombre, String descripcion, Date fecha, Date fechaterminado, tareaestado estado, int categoria, int usuarioCreador, int usuarioAsignado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.fechaterminado = fechaterminado;
        this.estado = estado;
        this.categoria = categoria;
        this.usuarioCreador = usuarioCreador;
        this.usuarioAsignado = usuarioAsignado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaterminado() {
        return fechaterminado;
    }

    public void setFechaterminado(Date fechaterminado) {
        this.fechaterminado = fechaterminado;
    }

    public tareaestado getEstado() {
        return estado;
    }

    public void setEstado(tareaestado estado) {
        this.estado = estado;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public int getUsuarioCreador() {
        return usuarioCreador;
    }

    public void setUsuarioCreador(int usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
    }

    public int getUsuarioAsignado() {
        return usuarioAsignado;
    }

    public void setUsuarioAsignado(int usuarioAsignado) {
        this.usuarioAsignado = usuarioAsignado;
    }
}
