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
    private  Date fecha;
    private Date fechaterminado;
    private tareaestado estado;
    private Categoria categoria;
    private Usuario usuarioCreador;
    private Usuario usuarioAsignado;

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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Usuario getUsuarioCreador() {
        return usuarioCreador;
    }

    public void setUsuarioCreador(Usuario usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
    }

    public Usuario getUsuarioAsignado() {
        return usuarioAsignado;
    }

    public void setUsuarioAsignado(Usuario usuarioAsignado) {
        this.usuarioAsignado = usuarioAsignado;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Tarea{");
        sb.append("id=").append(id);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", descripcion='").append(descripcion).append('\'');
        sb.append(", fecha=").append(fecha);
        sb.append(", fechaterminado=").append(fechaterminado);
        sb.append(", estado=").append(estado);
        sb.append(", categoria=").append(categoria);
        sb.append(", usuarioCreador=").append(usuarioCreador);
        sb.append(", usuarioAsignado=").append(usuarioAsignado);
        sb.append('}');
        return sb.toString();
    }
}
