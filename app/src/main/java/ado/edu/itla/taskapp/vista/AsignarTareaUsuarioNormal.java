package ado.edu.itla.taskapp.vista;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ado.edu.itla.taskapp.R;
import ado.edu.itla.taskapp.entidad.Categoria;
import ado.edu.itla.taskapp.entidad.Usuario;
import ado.edu.itla.taskapp.repositorio.CategoriaRepositorio;
import ado.edu.itla.taskapp.repositorio.UsuarioRepositorio;
import ado.edu.itla.taskapp.repositorio.db.CategoriaRepositorioDbImp;
import ado.edu.itla.taskapp.repositorio.db.UsuarioRepositorioDBImpl;

public class AsignarTareaUsuarioNormal extends AppCompatActivity {

    UsuarioRepositorio usuarioR;
    CategoriaRepositorio categoriaR;
    Usuario usuario;
    Categoria categoria;
    Spinner spTecnicos;
    Spinner spCategoria;
    Button btnGuardar;
    List<String> usuariosList = new ArrayList<>();
    List<String> categoriaList = new ArrayList<>();
    String usuarioNombre;
    String categoriaNombre;
    int usuarioId;
    int categoriaId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignar_tarea_usuario_normal);

        usuario = new Usuario();
        categoria = new Categoria();

        spCategoria = (Spinner)findViewById(R.id.spCategoria);
        spTecnicos = (Spinner)findViewById(R.id.spTecnicos);
        btnGuardar = (Button)findViewById(R.id.btnGuardar);

        usuarioR = new UsuarioRepositorioDBImpl(this);
        categoriaR = new CategoriaRepositorioDbImp(this);

        //BUSCA LAS CATEGORIAS Y AGREGA A LA LIST.
        for (int x = 0; x <categoriaR.buscar("").size()-1; x++){
            categoriaList.add(categoriaR.buscar("").get(x).getNombre());
        }

        //BUSCA LOS TECNICOS Y AGREGA SU NOMBRE A LA LISTA DE USUARIOS TIPO STRING.
        for (int i = 0; i < usuarioR.buscarTecnicos().size()-1; i++){
            usuariosList.add(usuarioR.buscarTecnicos().get(i).getNombre());
        }

        //ENVIO LA LISTA AL ADAPTER Y SE LA ASIGNO AL SPINNER.
        ArrayAdapter<String> categoriaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoriaList);
        spCategoria.setAdapter(categoriaAdapter);

        ArrayAdapter<String> tecnicoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, usuariosList);
        spTecnicos.setAdapter(tecnicoAdapter);

        spCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoriaNombre = categoriaR.buscar("").get(position).getNombre();
                categoriaId = categoriaR.buscar("").get(position).getId();

                Toast toast = Toast.makeText(getApplicationContext(), "id: "+ categoriaId +" nombre: " +categoriaNombre, Toast.LENGTH_SHORT);
                toast.show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spTecnicos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                usuarioId = usuarioR.buscarTecnicos().get(position).getId();
                usuarioNombre = usuarioR.buscarTecnicos().get(position).getNombre();

                Toast toast = Toast.makeText(getApplicationContext(), "id : " + usuarioId + " nombre : " +usuarioNombre, Toast.LENGTH_LONG);
                toast.show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "idCat: " +categoriaId +"cat: " +categoriaNombre+ " idUs " +usuarioId+ " usu: "+usuarioNombre, Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
}