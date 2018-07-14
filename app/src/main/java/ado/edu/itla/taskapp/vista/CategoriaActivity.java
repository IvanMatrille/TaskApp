package ado.edu.itla.taskapp.vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import ado.edu.itla.taskapp.R;
import ado.edu.itla.taskapp.entidad.Categoria;
import ado.edu.itla.taskapp.repositorio.CategoriaRepositorio;
import ado.edu.itla.taskapp.repositorio.db.CategoriaRepositorioDbImp;

public class CategoriaActivity extends AppCompatActivity {

        private static final String LOG_TAG = "CategoriaActivity";
        private CategoriaRepositorio categoriaRepositorio;
        private Categoria categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);

        categoriaRepositorio = new CategoriaRepositorioDbImp(this);

        final TextView txtNombre = (TextView)findViewById(R.id.txtNombreCategoria);

        Button btnGuardar = (Button) findViewById(R.id.btnGuardar);

        Log.i(LOG_TAG, categoria.toString());

        Bundle paraBunble = getIntent().getExtras();
        if (paraBunble != null && paraBunble.containsKey("categoria")) {
            categoria = (Categoria)paraBunble.getSerializable("categoria");
            txtNombre.setText(categoria.getNombre());
            btnGuardar.setText("Actualizar");
        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(categoria == null)
                {
                    categoria = new Categoria();
                }

                categoria.setNombre(txtNombre.getText().toString());

                Log.i(LOG_TAG, categoria.toString());

                categoriaRepositorio.guardar(categoria);

                Log.i(LOG_TAG, categoria.toString());


                //TODO: guarda la categoria en la base de datos.
                //Todo: 1 - Si existe actualizarla, 2 - Sino agregarla
            }
        });
    }
}