package ado.edu.itla.taskapp.vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

import ado.edu.itla.taskapp.R;
import ado.edu.itla.taskapp.entidad.Tarea;
import ado.edu.itla.taskapp.repositorio.TareaRepositorio;
import ado.edu.itla.taskapp.repositorio.db.TareaRepositorioDBImpl;

public class DetalleTareaTecnico extends AppCompatActivity {

    TareaRepositorio tareaR;
    Tarea tarea;
    TextView txtCategoria;
    TextView txtDescripcion;
    TextView txtFecha;
    TextView txtCreador;
    Button btnListo;
    int idTarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_tecnico);

        txtCategoria = (TextView)findViewById(R.id.txtCategoriaUT);
        txtCreador = (TextView)findViewById(R.id.txtCreadorUT);
        txtDescripcion = (TextView)findViewById(R.id.txtDescripcionUT);
        txtFecha = (TextView)findViewById(R.id.txtFechaUT);
        btnListo = (Button)findViewById(R.id.btnListo);

        tareaR = new TareaRepositorioDBImpl(this);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.containsKey("tarea")){
            idTarea = bundle.getInt("tarea");
            tarea = tareaR.buscar(idTarea);

            txtFecha.setText(new SimpleDateFormat("dd/mm/yyyy").format(tarea.getFecha()));
            txtCreador.setText(Integer.toString(tarea.getUsuarioCreador()));
            txtCategoria.setText(Integer.toString(tarea.getCategoria()));
            txtDescripcion.setText(tarea.getDescripcion());
        }

        btnListo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tarea tarea = new Tarea();
                tarea.setId(idTarea);
                tarea.setEstado(Tarea.tareaestado.terminado);
                tareaR.actualizarEstado(tarea);

                Toast.makeText(getApplicationContext(), "Tarea terminada", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(DetalleTareaTecnico.this, TareaUsuarioTecnico.class);
                startActivity(intent);

            }
        });


    }
}
