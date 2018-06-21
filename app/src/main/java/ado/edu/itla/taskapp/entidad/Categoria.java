package ado.edu.itla.taskapp.entidad;

public class Categoria {
   private Integer id;
   private String descripcion;

   public void setId(Integer id)
   {
       this.id = id;
   }

    public Integer getId() {
        return id;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    @Override
    public String toString() {

        final StringBuffer sb = new StringBuffer("Categoria{");
        sb.append("id=").append(id);
        sb.append(", descripcion='").append(descripcion).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
