package ado.edu.itla.taskapp.entidad;

public class Categoria {
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

        final StringBuffer sb = new StringBuffer("Categoria{");
        sb.append("id=").append(id);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
