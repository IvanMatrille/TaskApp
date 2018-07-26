package ado.edu.itla.taskapp.entidad;

import java.io.Serializable;

public class Categoria implements Serializable{
   private Integer id;
   private String nombre;

    public Categoria() {

    }

    public Categoria(Integer id) {
        this.id = id;
    }

    public Categoria(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Categoria setId(Integer id)
   {
       this.id = id;
       return this;
   }

    public Integer getId() {
        return id;
    }

    public Categoria setNombre(String nombre)
    {
        this.nombre = nombre;
        return this;
    }

    public String getNombre()
    {
        return nombre;
    }

    @Override
    public String toString() {

        return nombre;
    }
}
