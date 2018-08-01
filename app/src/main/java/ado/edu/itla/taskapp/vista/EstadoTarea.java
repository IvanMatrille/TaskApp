package ado.edu.itla.taskapp.vista;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import ado.edu.itla.taskapp.R;
import ado.edu.itla.taskapp.entidad.Tarea;
import ado.edu.itla.taskapp.repositorio.TareaRepositorio;
import ado.edu.itla.taskapp.repositorio.db.TareaRepositorioDBImpl;

public class EstadoTarea extends AppCompatActivity {
    Tarea tarea;
    TextView txtCategoria;
    TextView txtAsignadoA;
    TextView txtEstado;
    TextView txtDescripcion;
    TextView txtFecha;
    TareaRepositorio tareaR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado_tarea);

        txtCategoria = (TextView) findViewById(R.id.txtCategoriaET);
        txtAsignadoA = (TextView) findViewById(R.id.txtAsignadoAET);
        txtEstado = (TextView) findViewById(R.id.txtEstadoET);
        txtDescripcion = (TextView) findViewById(R.id.txtDescripcionET);
        txtFecha = (TextView)findViewById(R.id.txtFechaET);

        tareaR = new TareaRepositorioDBImpl(this);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.containsKey("id")){
            int Tarea = bundle.getInt("id");
            TareaRepositorio tareaRepositorio;
            tarea = tareaR.buscar(Tarea);

            txtDescripcion.setText(tarea.getDescripcion().toString());
            txtCategoria.setText(Integer.toString(tarea.getCategoria()));
            txtAsignadoA.setText(Integer.toString(tarea.getUsuarioAsignado()));
            txtFecha.setText(new SimpleDateFormat("dd-m-yyyy").format(tarea.getFecha()));
            txtEstado.setText(tarea.getEstado().toString());
        }
    }
}
