package ado.edu.itla.taskapp.vista;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

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

public class DetalleTareaNormal extends AppCompatActivity {
    Tarea tarea;
    TextView txtCategoria;
    TextView txtAsignadoA;
    TextView txtEstado;
    TextView txtDescripcion;
    TextView txtFecha;
    TareaRepositorio tareaR;
    CategoriaRepositorio categoriaR;
    UsuarioRepositorio usuarioR;

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
        usuarioR = new UsuarioRepositorioDBImpl(this);
        categoriaR = new CategoriaRepositorioDbImp(this);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.containsKey("id")){
            int Tarea = bundle.getInt("id");
            tarea = tareaR.buscar(Tarea);
            Categoria categoria = categoriaR.buscar(tarea.getCategoria());
            Usuario usuario = usuarioR.buscar(tarea.getUsuarioAsignado());

            txtDescripcion.setText(tarea.getDescripcion().toString());
            txtCategoria.setText(categoria.getNombre());
            txtAsignadoA.setText(usuario.getNombre());
            txtFecha.setText(new SimpleDateFormat("dd-m-yyyy").format(tarea.getFecha()));
            txtEstado.setText(tarea.getEstado().toString());
        }
    }
}
