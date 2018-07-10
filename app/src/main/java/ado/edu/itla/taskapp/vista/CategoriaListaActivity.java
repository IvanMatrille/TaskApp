package ado.edu.itla.taskapp.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import ado.edu.itla.taskapp.R;
import ado.edu.itla.taskapp.entidad.Categoria;
import ado.edu.itla.taskapp.repositorio.CategoriaRepositorio;
import ado.edu.itla.taskapp.repositorio.db.CategoriaRepositorioDbImp;

public class CategoriaListaActivity extends AppCompatActivity {

    private CategoriaRepositorio categoriaRepositorio;
    private static final String LOG_TAG = "CategoriaListaActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_lista);

        categoriaRepositorio = new CategoriaRepositorioDbImp(this);

        List<Categoria> categorias = categoriaRepositorio.buscar(null);
        Log.i(LOG_TAG, "Total de categoria : " + categorias.size());

        ListView catListView = (ListView)findViewById(R.id.categoria_listview);
        catListView.setAdapter(new CategoriaListAdapter(this, categorias));

        catListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Categoria cat = (Categoria) parent.getItemAtPosition(position);

                Intent regCatActivity = new Intent(CategoriaListaActivity.this, CategoriaActivity.class);
                regCatActivity.putExtra("categoria", cat);
                startActivity(regCatActivity);
            }
        });
    }
}
