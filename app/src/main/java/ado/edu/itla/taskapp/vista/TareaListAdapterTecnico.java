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
import ado.edu.itla.taskapp.entidad.Tarea;

public class TareaListAdapterTecnico extends BaseAdapter {
    Context context;
    List<Tarea> tareas;

    public TareaListAdapterTecnico(Context context, List<Tarea> tareas){
        this.context = context;
        this.tareas = tareas;
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
        TextView txtCreador = convertView.findViewById(R.id.txtCreador);
        TextView txtEstado = convertView.findViewById(R.id.txtEstadoTarea);

        Tarea tarea = tareas.get(position);

        String categoria = String.valueOf(tarea.getCategoria());
        String creador = String.valueOf(tarea.getUsuarioCreador());

        txtCategoria.setText(categoria);
        txtCreador.setText(creador);
        txtDescripcion.setText(tarea.getDescripcion());
        txtEstado.setText(tarea.getEstado().toString());
        txtFecha.setText(new SimpleDateFormat("dd-mm-yyyy").format(tarea.getFecha()));

        return convertView;
    }
}
