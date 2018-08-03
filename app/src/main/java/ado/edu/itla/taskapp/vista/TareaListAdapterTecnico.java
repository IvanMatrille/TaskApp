package ado.edu.itla.taskapp.vista;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import ado.edu.itla.taskapp.R;
import ado.edu.itla.taskapp.entidad.Categoria;
import ado.edu.itla.taskapp.entidad.Tarea;
import ado.edu.itla.taskapp.entidad.Usuario;
import ado.edu.itla.taskapp.repositorio.db.CategoriaRepositorioDbImp;
import ado.edu.itla.taskapp.repositorio.db.UsuarioRepositorioDBImpl;

public class TareaListAdapterTecnico extends BaseAdapter {
    Context context;
    List<Tarea> tareas;
    private UsuarioRepositorioDBImpl usuarioRDB;
    private CategoriaRepositorioDbImp categoriaRDB;

    public TareaListAdapterTecnico(Context context, List<Tarea> tareas){
        this.context = context;
        this.tareas = tareas;
        usuarioRDB = new UsuarioRepositorioDBImpl(context);
        categoriaRDB = new CategoriaRepositorioDbImp(context);
    }

    @Override
    public int getCount() {
        return tareas.size();
    }

    @Override
    public Object getItem(int position) {
        return tareas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return tareas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.item_tarea_tecnico, null, true);
        }

        TextView txtDescripcion = convertView.findViewById(R.id.txtDescripcionET);
        TextView txtCategoria = convertView.findViewById(R.id.txtCategoriaLv);
        TextView txtFecha = convertView.findViewById(R.id.txtFechaET);
        TextView txtCreador = convertView.findViewById(R.id.txtAsignado);
        TextView txtEstado = convertView.findViewById(R.id.txtEstadoTarea);

        Tarea tarea = tareas.get(position);

        Categoria categoria = categoriaRDB.buscar(tarea.getCategoria());
        Usuario usuario = usuarioRDB.buscar(tarea.getUsuarioCreador());

        txtCategoria.setText(categoria.getNombre());
        txtCreador.setText(usuario.getNombre());
        txtDescripcion.setText(tarea.getDescripcion());
        txtEstado.setText(tarea.getEstado().toString());
        txtFecha.setText(new SimpleDateFormat("dd-mm-yyyy").format(tarea.getFecha()));

        return convertView;
    }
}
