package ado.edu.itla.taskapp.repositorio;

import android.app.Activity;
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
import ado.edu.itla.taskapp.repositorio.db.CategoriaRepositorioDbImp;
import ado.edu.itla.taskapp.repositorio.db.UsuarioRepositorioDBImpl;

public class TareaListAdaptar extends BaseAdapter {

    private Activity activity;
    private List<Tarea> tareas;
    private List<Categoria> categorias;
    private UsuarioRepositorioDBImpl usuarioRDB;
    private CategoriaRepositorioDbImp categoriaRDB;

    public TareaListAdaptar(Activity activity, List<Tarea> tareas){
        this.tareas = tareas;
        this.activity = activity;
        usuarioRDB = new UsuarioRepositorioDBImpl(activity);
        categoriaRDB = new CategoriaRepositorioDbImp(activity);
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

        if(convertView == null ){
            LayoutInflater layoutInflater = LayoutInflater.from(activity);
            convertView = layoutInflater.inflate(R.layout.item_tarea, null, true);
        }

        TextView txtDescripcion = convertView.findViewById(R.id.txtDescripcionET);
        TextView txtUsuarioCreador = convertView.findViewById(R.id.txtUsuarioCreador);
        TextView txtCategoria = convertView.findViewById(R.id.txtCategoriaLv);
        TextView txtFecha = convertView.findViewById(R.id.txtFechaET);
        TextView txtEstado = convertView.findViewById(R.id.txtEstadoET);
        TextView txtEstadoTarea = convertView.findViewById(R.id.txtEstadoTarea);

      //  Tarea tarea = tareas.get(position);
        Tarea tarea = tareas.get(position);

        String usuarioCreador = String.valueOf(tarea.getUsuarioAsignado());
        String categoriaid = String.valueOf(tarea.getCategoria());

        txtDescripcion.setText(tarea.getDescripcion());
        txtUsuarioCreador.setText(usuarioCreador);
        txtCategoria.setText(categoriaid);
        txtFecha.setText(new SimpleDateFormat("dd-m-yyyy").format(tarea.getFecha()));
        txtEstadoTarea.setText(tarea.getEstado().toString());

        return convertView;
    }
}