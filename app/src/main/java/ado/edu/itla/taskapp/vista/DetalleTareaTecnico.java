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
import ado.edu.itla.taskapp.entidad.Categoria;
import ado.edu.itla.taskapp.entidad.Tarea;
import ado.edu.itla.taskapp.entidad.Usuario;
import ado.edu.itla.taskapp.repositorio.CategoriaRepositorio;
import ado.edu.itla.taskapp.repositorio.TareaRepositorio;
import ado.edu.itla.taskapp.repositorio.UsuarioRepositorio;
import ado.edu.itla.taskapp.repositorio.db.CategoriaRepositorioDbImp;
import ado.edu.itla.taskapp.repositorio.db.TareaRepositorioDBImpl;
import ado.edu.itla.taskapp.repositorio.db.UsuarioRepositorioDBImpl;

public class DetalleTareaTecnico extends AppCompatActivity {

    TareaRepositorio tareaR;
    Tarea tarea;
    TextView txtCategoria;
    TextView txtDescripcion;
    TextView txtFecha;
    TextView txtCreador;
    Button btnListo;
    int idTarea;
    UsuarioRepositorio usuarioR;
    CategoriaRepositorio categoriaR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_tecnico);

        usuarioR = new UsuarioRepositorioDBImpl(this);
        categoriaR = new CategoriaRepositorioDbImp(this);

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
            Usuario usuario = usuarioR.buscar(tarea.getUsuarioCreador());
            Categoria categoria = categoriaR.buscar(tarea.getCategoria());

            txtFecha.setText(new SimpleDateFormat("dd/mm/yyyy").format(tarea.getFecha()));
            txtCreador.setText(usuario.getNombre());
            txtCategoria.setText(categoria.getNombre());
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
