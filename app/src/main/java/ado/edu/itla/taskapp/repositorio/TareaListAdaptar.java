package ado.edu.itla.taskapp.repositorio;

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
import ado.edu.itla.taskapp.entidad.Usuario;
import ado.edu.itla.taskapp.repositorio.db.CategoriaRepositorioDbImp;
import ado.edu.itla.taskapp.repositorio.db.UsuarioRepositorioDBImpl;
import ado.edu.itla.taskapp.entidad.Tarea;

public class TareaListAdaptar extends BaseAdapter {

    private Context context;
    private List<Tarea> tareas;
    private List<Categoria> categorias;
    private UsuarioRepositorioDBImpl usuarioRDB;
    private CategoriaRepositorioDbImp categoriaRDB;

    public TareaListAdaptar(Context context, List<Tarea> tareas){
        this.tareas = tareas;
        this.context = context;
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

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(R.layout.item_tarea, null, true);

        TextView txtDescripcion = convertView.findViewById(R.id.txtDescripcion);
        TextView txtUsuarioCreador = convertView.findViewById(R.id.txtUsuarioCreador);
        TextView txtCategoria = convertView.findViewById(R.id.txtCategoriaLv);
        TextView txtFecha = convertView.findViewById(R.id.txtFecha);
        TextView txtEstado = convertView.findViewById(R.id.txtEstado);

        Tarea tarea = tareas.get(position);
        Usuario usuario = usuarioRDB.buscar(tarea.getUsuarioCreador());
        Categoria categoria = categoriaRDB.buscar(tarea.getCategoria());

        txtDescripcion.setText(tarea.getDescripcion());
        txtUsuarioCreador.setText(usuario.getNombre());
       // txtCategoria.setText(tarea.getCategoria());
        txtEstado.setText(tarea.getEstado().toString());
        txtFecha.setText(new SimpleDateFormat("dd-mm-yyyy").format(tarea.getFecha()));

        return convertView;
    }
}